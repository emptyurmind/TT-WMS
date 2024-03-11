package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.InventorySettlementDetail;
import com.tt.wms.domain.vo.InventorySettlementDetailVO;
import java.util.List;
/**
 * 库存结算明细  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventorySettlementDetailConvert  {
    List<InventorySettlementDetailVO> dos2vos(List<InventorySettlementDetail> list);

    List<InventorySettlementDetail> vos2dos(List<InventorySettlementDetailVO> details);

    List<InventorySettlementDetailVO> toVos(List<InventorySettlementDetail> inventoryCheckDetails);
}
