package com.tt.wms.domain.form;

import com.tt.wms.domain.entity.ReceiptOrder;
import com.tt.wms.domain.vo.ItemVO;
import com.tt.wms.domain.vo.ReceiptOrderDetailVO;
import lombok.Data;

import java.util.List;

@Data
public class ReceiptOrderForm extends ReceiptOrder {
    /**
     * 入库单详情
     */
    private List<ReceiptOrderDetailVO> details;

    /**
     * 所有商品
     */
    private List<ItemVO> items;
}
