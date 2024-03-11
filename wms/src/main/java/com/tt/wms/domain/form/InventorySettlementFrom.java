package com.tt.wms.domain.form;

import com.tt.wms.domain.entity.InventorySettlement;
import com.tt.wms.domain.vo.InventorySettlementDetailVO;
import com.tt.wms.domain.vo.ItemVO;
import lombok.Data;

import java.util.List;

/**
 * 盘库结算 数据视图对象
 *
 * @author wangkun
 */
@Data
public class InventorySettlementFrom extends InventorySettlement {
    // 盘库结算详情
    private List<InventorySettlementDetailVO> details;
    // 所有商品
    private List<ItemVO> items;
}
