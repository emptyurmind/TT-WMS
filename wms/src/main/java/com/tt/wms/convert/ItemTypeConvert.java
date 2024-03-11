package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.ItemType;
import com.tt.wms.domain.vo.ItemTypeVO;
import java.util.List;
/**
 * 物料类型表  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface ItemTypeConvert  {
    List<ItemTypeVO> dos2vos(List<ItemType> list);
}
