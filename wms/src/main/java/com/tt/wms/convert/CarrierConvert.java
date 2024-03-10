package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Carrier;
import com.tt.wms.domain.vo.CarrierVO;
import java.util.List;
/**
 * 承运商  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface CarrierConvert  {
    List<CarrierVO> dos2vos(List<Carrier> list);
}
