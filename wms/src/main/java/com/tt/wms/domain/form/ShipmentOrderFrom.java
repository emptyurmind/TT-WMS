package com.tt.wms.domain.form;

import com.tt.wms.domain.entity.ShipmentOrder;
import com.tt.wms.domain.vo.DeliveryVO;
import com.tt.wms.domain.vo.ItemVO;
import com.tt.wms.domain.vo.ShipmentOrderDetailVO;
import lombok.Data;

import java.util.List;

@Data
public class ShipmentOrderFrom extends ShipmentOrder {
    /**
     * 出库单详情
     */
    private List<ShipmentOrderDetailVO> details;

    /**
     * 所有商品
     */
    private List<ItemVO> items;

    /**
     * 物流信息
     */
    private List<DeliveryVO> delivery;
}
