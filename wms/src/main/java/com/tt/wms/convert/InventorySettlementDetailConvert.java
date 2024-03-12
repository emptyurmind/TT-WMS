package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventorySettlementDetail;
import com.tt.wms.domain.vo.InventorySettlementDetailVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库存结算明细  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface InventorySettlementDetailConvert {
    List<InventorySettlementDetailVO> dos2vos(List<InventorySettlementDetail> list);

    List<InventorySettlementDetail> vos2dos(List<InventorySettlementDetailVO> details);

    List<InventorySettlementDetailVO> toVos(List<InventorySettlementDetail> inventoryCheckDetails);
}
