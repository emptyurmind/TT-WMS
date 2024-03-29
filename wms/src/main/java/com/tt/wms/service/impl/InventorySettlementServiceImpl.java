package com.tt.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import com.tt.wms.convert.InventorySettlementConvert;
import com.tt.wms.convert.InventorySettlementDetailConvert;
import com.tt.wms.domain.entity.InventorySettlement;
import com.tt.wms.domain.entity.InventorySettlementDetail;
import com.tt.wms.domain.form.InventoryCheckForm;
import com.tt.wms.domain.form.InventorySettlementForm;
import com.tt.wms.domain.query.InventorySettlementQuery;
import com.tt.wms.domain.vo.InventorySettlementDetailVO;
import com.tt.wms.mapper.InventorySettlementDetailMapper;
import com.tt.wms.mapper.InventorySettlementMapper;
import com.tt.wms.service.InventorySettlementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存结算单Service业务层处理
 *
 * @author wangkun
 */
@Service
public class InventorySettlementServiceImpl implements InventorySettlementService {

    @Resource
    private InventorySettlementMapper inventorySettlementMapper;

    @Resource
    private InventorySettlementDetailMapper inventorySettlementDetailMapper;

    @Autowired
    private InventorySettlementDetailConvert detailConvert;

    @Autowired
    private InventorySettlementConvert convert;

    /**
     * 查询库存结算单
     *
     * @param id 库存结算单主键
     * @return 库存结算单
     */
    @Override
    public InventorySettlementForm selectById(Long id) {
        InventorySettlement inventorySettlement = inventorySettlementMapper.selectById(id);
        if (inventorySettlement == null) {
            return null;
        }
        InventorySettlementForm from = convert.do2form(inventorySettlement);

        //注入 详情单
        LambdaQueryWrapper<InventorySettlementDetail> inventoryCheckDetailQuery = new LambdaQueryWrapper<>();
        inventoryCheckDetailQuery.eq(InventorySettlementDetail::getSettlementId, id);
        List<InventorySettlementDetail> inventoryCheckDetails = inventorySettlementDetailMapper.selectList(inventoryCheckDetailQuery);
        List<InventorySettlementDetailVO> inventoryCheckDetailsVos = detailConvert.toVos(inventoryCheckDetails);
        from.setDetails(inventoryCheckDetailsVos);
        return from;
    }

    /**
     * 查询库存结算单列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 库存结算单
     */
    @Override
    public List<InventorySettlement> selectList(InventorySettlementQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        LambdaQueryWrapper<InventorySettlement> qw = new LambdaQueryWrapper<>();
        qw.eq(InventorySettlement::getDelFlag, 0);
        qw.orderByDesc(InventorySettlement::getId);
        Integer inventorySettlementStatus = query.getInventorySettlementStatus();
        if (inventorySettlementStatus != null) {
            qw.eq(InventorySettlement::getInventorySettlementStatus, inventorySettlementStatus);
        }
        Integer settlementType = query.getSettlementType();
        if (settlementType != null) {
            qw.eq(InventorySettlement::getSettlementType, settlementType);
        }
        String inventorySettlementNo = query.getInventorySettlementNo();
        if (!StringUtils.isEmpty(inventorySettlementNo)) {
            qw.eq(InventorySettlement::getInventorySettlementNo, inventorySettlementNo);
        }
        return inventorySettlementMapper.selectList(qw);
    }

    /**
     * 新增库存结算单
     *
     * @param inventorySettlement 库存结算单
     * @return 结果
     */
    @Override
    public int insert(InventorySettlement inventorySettlement) {
        inventorySettlement.setDelFlag(0);
        inventorySettlement.setCreateTime(LocalDateTime.now());
        return inventorySettlementMapper.insert(inventorySettlement);
    }

    /**
     * 修改库存结算单
     *
     * @param inventorySettlement 库存结算单
     * @return 结果
     */
    @Override
    public int update(InventorySettlement inventorySettlement) {
        return inventorySettlementMapper.updateById(inventorySettlement);
    }

    /**
     * 批量删除库存结算单
     *
     * @param ids 需要删除的库存结算单主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return inventorySettlementMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存结算单信息
     *
     * @param id 库存结算单主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return inventorySettlementMapper.updateDelFlagByIds(ids);
    }

    /**
     * 新增或更新结算单据以及结算单据明细
     *
     * @param inventorySettlementForm 库存结算单
     * @return 结果
     */
    @Override
    public int addOrUpdate(InventorySettlementForm inventorySettlementForm) {
        int res;
        // 1. 新增
        if (inventorySettlementForm.getId() == null) {
            inventorySettlementForm.setDelFlag(0);
            inventorySettlementForm.setCreateTime(LocalDateTime.now());
            res = inventorySettlementMapper.insert(inventorySettlementForm);
        } else {
            // 2.编辑
            // 2.1 更新结算单
            res = inventorySettlementMapper.updateById(inventorySettlementForm);
        }

        if (InventoryCheckForm.CREATED.equals(String.valueOf(inventorySettlementForm.getInventorySettlementStatus())) || InventoryCheckForm.FINISH.equals(String.valueOf(inventorySettlementForm.getInventorySettlementStatus()))) {
            // 3.暂存
            // 3.1 删除明细单
            deleteDetails(inventorySettlementForm);

            // 3.2 保存明细单
            saveDetails(inventorySettlementForm);
        }

        return res;

    }

    /**
     * 删除明细单
     *
     * @param inventorySettlementForm 库存结算单
     */
    private void deleteDetails(InventorySettlementForm inventorySettlementForm) {
        LambdaQueryWrapper<InventorySettlementDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InventorySettlementDetail::getSettlementId, inventorySettlementForm.getId());
        inventorySettlementDetailMapper.delete(queryWrapper);
    }

    /**
     * 保存单据明细
     *
     * @param inventorySettlementForm 库存结算单
     */
    private void saveDetails(InventorySettlementForm inventorySettlementForm) {
        Long settlementId = inventorySettlementForm.getId();
        List<InventorySettlementDetailVO> details = inventorySettlementForm.getDetails();
        Integer settlementType = inventorySettlementForm.getSettlementType();
        if (!CollUtil.isEmpty(details)) {
            List<InventorySettlementDetail> inventoryCheckDetails = detailConvert.vos2dos(details);
            Long userId = SecurityUtils.getUserId();
            inventoryCheckDetails.forEach(it -> {
                it.setSettlementId(settlementId);
                it.setSettlementType(settlementType);
                it.setDelFlag(0);
                it.setCreateTime(LocalDateTime.now());
                it.setCreateBy(userId);
            });
            inventorySettlementDetailMapper.batchInsert(inventoryCheckDetails);
        }
    }
}
