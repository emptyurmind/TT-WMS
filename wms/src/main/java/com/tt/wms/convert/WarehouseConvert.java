package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Warehouse;
import com.tt.wms.domain.vo.WarehouseVO;
import java.util.List;
/**
 * 仓库  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface WarehouseConvert  {
    List<WarehouseVO> dos2vos(List<Warehouse> list);
}
