package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.vo.InventoryHistoryVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库存记录  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryHistoryConvert {
    List<InventoryHistoryVO> dos2vos(List<InventoryHistory> list);
}
