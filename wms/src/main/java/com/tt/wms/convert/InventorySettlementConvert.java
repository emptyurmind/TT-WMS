package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventorySettlement;
import com.tt.wms.domain.form.InventorySettlementFrom;
import com.tt.wms.domain.vo.InventorySettlementVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库存结算单  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventorySettlementConvert {
    List<InventorySettlementVO> dos2vos(List<InventorySettlement> list);

    InventorySettlementFrom do2form(InventorySettlement inventorySettlement);
}
