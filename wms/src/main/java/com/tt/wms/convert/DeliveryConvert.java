package com.tt.wms.convert;

import com.tt.wms.domain.entity.Delivery;
import com.tt.wms.domain.vo.DeliveryVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 发货记录  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface DeliveryConvert {
    List<DeliveryVO> dos2vos(List<Delivery> list);
}
