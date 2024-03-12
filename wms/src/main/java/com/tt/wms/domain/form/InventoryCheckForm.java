package com.tt.wms.domain.form;

import com.tt.wms.domain.entity.InventoryCheck;
import com.tt.wms.domain.vo.InventoryCheckDetailVO;
import com.tt.wms.domain.vo.ItemVO;
import lombok.Data;

import java.util.List;

/**
 * 盘库单据 数据视图对象
 *
 * @author wangkun
 */
@Data
public class InventoryCheckForm extends InventoryCheck {
    /**
     * 盘库单据详情
     */
    private List<InventoryCheckDetailVO> details;

    /**
     * 所有商品
     */
    private List<ItemVO> items;
}
