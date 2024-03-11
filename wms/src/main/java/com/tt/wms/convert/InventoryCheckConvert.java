package com.tt.wms.convert;

import com.tt.wms.domain.form.InventoryCheckFrom;
import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.InventoryCheck;
import com.tt.wms.domain.vo.InventoryCheckVO;
import java.util.List;
/**
 * 库存盘点单据  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryCheckConvert  {
    List<InventoryCheckVO> dos2vos(List<InventoryCheck> list);

    InventoryCheckFrom do2form(InventoryCheck inventoryCheck);
}
