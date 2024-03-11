package com.tt.wms.convert;

import com.tt.wms.domain.form.InventoryMovementFrom;
import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.InventoryMovement;
import com.tt.wms.domain.vo.InventoryMovementVO;
import java.util.List;
/**
 * 库存移动  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryMovementConvert  {
    List<InventoryMovementVO> dos2vos(List<InventoryMovement> list);

    InventoryMovementFrom do2form(InventoryMovement order);
}
