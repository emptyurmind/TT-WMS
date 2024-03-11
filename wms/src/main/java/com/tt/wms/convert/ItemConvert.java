package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Item;
import com.tt.wms.domain.vo.ItemVO;
import java.util.List;
/**
 * 物料  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface ItemConvert  {
    List<ItemVO> dos2vos(List<Item> list);

    ItemVO toVo(Item source);
}
