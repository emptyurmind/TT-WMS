package com.tt.wms.service;

import com.tt.wms.domain.entity.ShipmentOrder;
import com.tt.wms.domain.entity.ShipmentOrderDetail;
import com.tt.wms.domain.query.ShipmentOrderDetailQuery;
import com.tt.wms.domain.vo.ShipmentOrderDetailVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 出库单详情Service业务层
 *
 * @author wangkun
 */
public interface ShipmentOrderDetailService {
    ShipmentOrderDetail selectById(Long id);

    List<ShipmentOrderDetail> selectList(ShipmentOrderDetailQuery query, Pageable page);

    List<ShipmentOrderDetailVO> toVos(List<ShipmentOrderDetail> items);

    int insert(ShipmentOrderDetail shipmentOrderDetail);

    int update(ShipmentOrderDetail shipmentOrderDetail);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    void updateDelFlag(ShipmentOrder shipmentOrder);

    int deleteByOrderId(Long orderId);
}
