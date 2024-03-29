package com.tt.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import com.tt.wms.constant.ReceiptOrderConstant;
import com.tt.wms.constant.ShipmentOrderConstant;
import com.tt.wms.convert.InventoryCheckConvert;
import com.tt.wms.convert.InventoryCheckDetailConvert;
import com.tt.wms.domain.entity.InventoryCheck;
import com.tt.wms.domain.entity.InventoryCheckDetail;
import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.entity.Item;
import com.tt.wms.domain.form.InventoryCheckForm;
import com.tt.wms.domain.query.InventoryCheckDetailQuery;
import com.tt.wms.domain.query.InventoryCheckQuery;
import com.tt.wms.domain.query.ItemQuery;
import com.tt.wms.domain.vo.InventoryCheckDetailVO;
import com.tt.wms.domain.vo.ItemVO;
import com.tt.wms.mapper.InventoryCheckDetailMapper;
import com.tt.wms.mapper.InventoryCheckMapper;
import com.tt.wms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存盘点单据Service业务层处理
 *
 * @author wangkun
 */
@Slf4j
@Service
public class InventoryCheckServiceImpl implements InventoryCheckService {

    @Resource
    private InventoryCheckMapper inventoryCheckMapper;

    @Resource
    private InventoryCheckDetailMapper inventoryCheckDetailMapper;

    @Autowired
    private InventoryCheckDetailConvert detailConvert;

    @Autowired
    private InventoryCheckDetailService inventoryCheckDetailService;

    @Autowired
    private InventoryHistoryService inventoryHistoryService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private InventoryCheckConvert convert;

    /**
     * 查询库存盘点单据
     *
     * @param id 库存盘点单据主键
     * @return 库存盘点单据
     */
    @Override
    public InventoryCheckForm selectById(Long id) {
        InventoryCheck inventoryCheck = inventoryCheckMapper.selectById(id);
        if (inventoryCheck == null) {
            return null;
        }
        InventoryCheckForm from = do2form(inventoryCheck);

        InventoryCheckDetailQuery inventoryCheckDetailQuery = new InventoryCheckDetailQuery();
        inventoryCheckDetailQuery.setInventoryCheckId(id);
        List<InventoryCheckDetail> inventoryCheckDetails = inventoryCheckDetailService.selectList(inventoryCheckDetailQuery, null);
        List<InventoryCheckDetailVO> inventoryCheckDetailsVos = inventoryCheckDetailService.toVos(inventoryCheckDetails);
        from.setDetails(inventoryCheckDetailsVos);
        if (!CollUtil.isEmpty(from.getDetails())) {
            List<Long> itemIds = from.getDetails().stream().map(InventoryCheckDetailVO::getItemId).distinct().collect(Collectors.toList());
            ItemQuery query1 = new ItemQuery();
            query1.setIds(itemIds);
            List<Item> list = itemService.selectList(query1, null);
            List<ItemVO> items = itemService.toVos(list);
            from.setItems(items);

        }
        return from;
    }

    private InventoryCheckForm do2form(InventoryCheck inventoryCheck) {
        InventoryCheckForm itemVO = convert.do2form(inventoryCheck);

        List<Long> place = new LinkedList<>();
        if (itemVO.getWarehouseId() != null) {
            place.add(itemVO.getWarehouseId());
        }
        if (itemVO.getAreaId() != null) {
            place.add(itemVO.getAreaId());
        }
        if (itemVO.getRackId() != null) {
            place.add(itemVO.getRackId());
        }
        itemVO.setPlace(place);

        return itemVO;
    }

    /**
     * 查询库存盘点单据列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 库存盘点单据
     */
    @Override
    public List<InventoryCheck> selectList(InventoryCheckQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), "create_time desc");
        }
        QueryWrapper<InventoryCheck> qw = new QueryWrapper<>();
        qw.eq("del_flag", 0);
        String inventoryCheckNo = query.getInventoryCheckNo();
        if (!StringUtils.isEmpty(inventoryCheckNo)) {
            qw.eq("inventory_check_no", inventoryCheckNo);
        }
        Integer inventoryCheckType = query.getInventoryCheckType();
        if (inventoryCheckType != null) {
            qw.eq("inventory_check_type", inventoryCheckType);
        }
        Integer inventoryCheckStatus = query.getInventoryCheckStatus();
        if (inventoryCheckStatus != null) {
            qw.eq("inventory_check_status", inventoryCheckStatus);
        }
        BigDecimal inventoryCheckTotal = query.getInventoryCheckTotal();
        if (inventoryCheckTotal != null) {
            qw.eq("inventory_check_total", inventoryCheckTotal);
        }
        Integer checkStatus = query.getCheckStatus();
        if (checkStatus != null) {
            qw.eq("check_status", checkStatus);
        }
        Long checkUserId = query.getCheckUserId();
        if (checkUserId != null) {
            qw.eq("check_user_id", checkUserId);
        }
        LocalDateTime checkTime = query.getCheckTime();
        if (checkTime != null) {
            qw.eq("check_time", checkTime);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        Long areaId = query.getAreaId();
        if (areaId != null) {
            qw.eq("area_id", areaId);
        }
        Long rackId = query.getRackId();
        if (rackId != null) {
            qw.eq("rack_id", rackId);
        }
        String attachment = query.getAttachment();
        if (!StringUtils.isEmpty(attachment)) {
            qw.eq("attachment", attachment);
        }
        return inventoryCheckMapper.selectList(qw);
    }

    /**
     * 新增库存盘点单据
     *
     * @param inventoryCheck 库存盘点单据
     * @return 结果
     */
    @Override
    public int insert(InventoryCheck inventoryCheck) {
        inventoryCheck.setDelFlag(0);
        inventoryCheck.setCreateTime(LocalDateTime.now());
        return inventoryCheckMapper.insert(inventoryCheck);
    }

    /**
     * 修改库存盘点单据
     *
     * @param inventoryCheck 库存盘点单据
     * @return 结果
     */
    @Override
    public int update(InventoryCheck inventoryCheck) {
        return inventoryCheckMapper.updateById(inventoryCheck);
    }

    /**
     * 批量删除库存盘点单据
     *
     * @param ids 需要删除的库存盘点单据主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return inventoryCheckMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存盘点单据信息
     *
     * @param id 库存盘点单据主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return inventoryCheckMapper.updateDelFlagByIds(ids);
    }

    /**
     * 新增或更新盘点单据以及盘点单据明细
     *
     * @param inventoryCheckForm 盘点单据
     * @return 结果
     */
    @Transactional
    @Override
    public int addOrUpdate(InventoryCheckForm inventoryCheckForm) {
        int res;
        // 1. 新增
        if (inventoryCheckForm.getId() == null) {
            inventoryCheckForm.setDelFlag(0);
            inventoryCheckForm.setCreateTime(LocalDateTime.now());
            res = inventoryCheckMapper.insert(inventoryCheckForm);
        } else {
            // 2.编辑
            // 2.1 更新盘点单
            res = inventoryCheckMapper.updateById(inventoryCheckForm);
        }

        if (InventoryCheckForm.CREATED.equals(String.valueOf(inventoryCheckForm.getInventoryCheckStatus()))) {
            // 3.暂存
            // 3.1 删除明细单
            LambdaQueryWrapper<InventoryCheckDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(InventoryCheckDetail::getInventoryCheckId, inventoryCheckForm.getId());
            inventoryCheckDetailMapper.delete(queryWrapper);

            // 3.2 保存明细单
            saveDetails(inventoryCheckForm.getId(), inventoryCheckForm.getDetails());
        } else if (InventoryCheckForm.FINISH.equals(String.valueOf(inventoryCheckForm.getInventoryCheckStatus()))) {
            // 4.盘点结束

            // 4.1 删除明细单
            LambdaQueryWrapper<InventoryCheckDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(InventoryCheckDetail::getInventoryCheckId, inventoryCheckForm.getId());
            inventoryCheckDetailMapper.delete(queryWrapper);

            // 4.2 保存明细单
            saveDetails(inventoryCheckForm.getId(), inventoryCheckForm.getDetails());

            // 4.3 操作库存流水，物料库存
            saveInventory(inventoryCheckForm.getId(), inventoryCheckForm.getDetails());
        }

        return res;

    }

    /**
     * 操作库存流水，物料库存
     *
     * @param inventoryCheckId 操作单
     * @param details          盘点单据详情
     */
    private void saveInventory(final Long inventoryCheckId, List<InventoryCheckDetailVO> details) {
        List<InventoryHistory> adds = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Long userId = SecurityUtils.getUserId();
        details.forEach(it -> {
            BigDecimal subtract = it.getCheckQuantity().subtract(it.getQuantity());
            InventoryHistory h = inventoryCheckDetailService.vo2InventoryHistory(it);
            h.setFormId(inventoryCheckId);
            if (subtract.compareTo(BigDecimal.ZERO) == 0) {
                return;
            } else if (subtract.compareTo(BigDecimal.ZERO) > 0) {
                // 盘盈入库
                h.setFormType(ReceiptOrderConstant.CHECK_IN);
            } else {
                // 盘亏出库
                h.setFormType(ShipmentOrderConstant.CHECK_OUT);
            }

            h.setQuantity(subtract);
            h.setDelFlag(0);
            h.setId(null);
            h.setCreateTime(now);
            h.setCreateBy(userId);
            h.setUpdateBy(userId);
            h.setUpdateTime(now);
            adds.add(h);
        });
        if (!adds.isEmpty()) {
            int add1 = inventoryHistoryService.batchInsert(adds);
            int update1 = inventoryService.batchUpdate(adds);
            log.info("inventoryHistory: {}, inventory: {}", add1, update1);
        }
    }

    /**
     * 保存单据明细
     *
     * @param details 盘点单据详情
     */
    private void saveDetails(Long checkId, List<InventoryCheckDetailVO> details) {
        if (!CollUtil.isEmpty(details)) {
            details.forEach(it -> it.setInventoryCheckId(checkId));
            List<InventoryCheckDetail> inventoryCheckDetails = detailConvert.vos2dos(details);
            inventoryCheckDetailMapper.batchInsert(inventoryCheckDetails);
        }
    }
}
