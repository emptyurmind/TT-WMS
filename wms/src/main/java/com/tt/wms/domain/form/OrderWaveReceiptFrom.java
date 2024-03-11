package com.tt.wms.domain.form;

import com.tt.wms.domain.vo.ItemVO;
import com.tt.wms.domain.vo.ReceiptOrderDetailVO;
import com.tt.wms.domain.vo.WaveVO;
import lombok.Data;

import java.util.List;

/**
 * 波次 数据视图对象
 *
 * @author wangkun
 */
@Data
public class OrderWaveReceiptFrom extends WaveVO {
  // 出库单详情
  private List<ReceiptOrderDetailVO> details;
  private List<ReceiptOrderDetailVO> allocationDetails;
  // 所有商品
  private List<ItemVO> items;
}
