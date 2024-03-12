package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.entity.InventoryMovementDetail;
import com.tt.wms.domain.vo.InventoryMovementDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 库存移动详情  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryMovementDetailConvert {
    List<InventoryMovementDetailVO> dos2vos(List<InventoryMovementDetail> list);

    List<InventoryMovementDetail> vos2dos(List<InventoryMovementDetailVO> details);

    @Mapping(target = "quantity", source = "realQuantity")
    InventoryHistory do2InventoryHistory(InventoryMovementDetailVO it);
}
