package com.tt.wms.convert;

import com.tt.wms.domain.entity.Inventory;
import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.vo.InventoryVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库存  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryConvert {
    List<InventoryVO> dos2vos(List<Inventory> list);

    Inventory inventoryHistory2invertory(InventoryHistory it);
}
