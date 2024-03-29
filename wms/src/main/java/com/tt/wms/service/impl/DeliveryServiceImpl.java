package com.tt.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.tt.wms.domain.entity.Delivery;
import com.tt.wms.domain.query.DeliveryQuery;
import com.tt.wms.mapper.DeliveryMapper;
import com.tt.wms.service.DeliveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 发货记录Service业务层处理
 *
 * @author wangkun
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;

    /**
     * 查询发货记录
     *
     * @param id 发货记录主键
     * @return 发货记录
     */
    @Override
    public Delivery selectById(Long id) {
        return deliveryMapper.selectById(id);
    }

    /**
     * 查询发货记录列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 发货记录
     */
    @Override
    public List<Delivery> selectList(DeliveryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), "create_time desc");
        }
        QueryWrapper<Delivery> qw = new QueryWrapper<>();
        qw.eq("del_flag", 0);
        Long shipmentOrderId = query.getShipmentOrderId();
        if (shipmentOrderId != null) {
            qw.eq("shipment_order_id", shipmentOrderId);
        }
        Long carrierId = query.getCarrierId();
        if (carrierId != null) {
            qw.eq("carrier_id", carrierId);
        }
        LocalDateTime deliveryDate = query.getDeliveryDate();
        if (deliveryDate != null) {
            qw.eq("delivery_date", deliveryDate);
        }
        String trackingNo = query.getTrackingNo();
        if (!StringUtils.isEmpty(trackingNo)) {
            qw.eq("tracking_no", trackingNo);
        }
        return deliveryMapper.selectList(qw);
    }

    /**
     * 新增发货记录
     *
     * @param delivery 发货记录
     * @return 结果
     */
    @Override
    public int insert(Delivery delivery) {
        delivery.setDelFlag(0);
        delivery.setCreateTime(LocalDateTime.now());
        return deliveryMapper.insert(delivery);
    }

    /**
     * 修改发货记录
     *
     * @param delivery 发货记录
     * @return 结果
     */
    @Override
    public int update(Delivery delivery) {
        return deliveryMapper.updateById(delivery);
    }

    /**
     * 批量删除发货记录
     *
     * @param ids 需要删除的发货记录主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return deliveryMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除发货记录信息
     *
     * @param id 发货记录主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return deliveryMapper.updateDelFlagByIds(ids);
    }
}
