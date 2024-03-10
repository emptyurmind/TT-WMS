package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Delivery;
import com.tt.wms.domain.vo.DeliveryVO;
import java.util.List;
/**
 * 发货记录  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface DeliveryConvert  {
    List<DeliveryVO> dos2vos(List<Delivery> list);
}
