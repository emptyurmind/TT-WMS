package com.tt.wms.convert;

import com.tt.wms.domain.entity.ItemType;
import com.tt.wms.domain.vo.ItemTypeVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 物料类型表  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface ItemTypeConvert {
    List<ItemTypeVO> dos2vos(List<ItemType> list);
}
