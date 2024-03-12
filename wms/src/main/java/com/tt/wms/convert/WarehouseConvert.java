package com.tt.wms.convert;

import com.tt.wms.domain.entity.Warehouse;
import com.tt.wms.domain.vo.WarehouseVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 仓库  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface WarehouseConvert {
    List<WarehouseVO> dos2vos(List<Warehouse> list);
}
