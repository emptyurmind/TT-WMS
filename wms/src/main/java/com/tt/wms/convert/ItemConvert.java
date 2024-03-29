package com.tt.wms.convert;

import com.tt.wms.domain.entity.Item;
import com.tt.wms.domain.vo.ItemVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 物料  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface ItemConvert {
    List<ItemVO> dos2vos(List<Item> list);

    ItemVO toVo(Item source);
}
