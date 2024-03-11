package com.tt.wms.domain.form;

import com.tt.wms.domain.entity.InventoryMovement;
import com.tt.wms.domain.vo.InventoryMovementDetailVO;
import com.tt.wms.domain.vo.ItemVO;
import lombok.Data;

import java.util.List;

@Data
public class InventoryMovementFrom extends InventoryMovement {
  // 详情
  private List<InventoryMovementDetailVO> details;
  // 所有商品
  private List<ItemVO> items;
}
