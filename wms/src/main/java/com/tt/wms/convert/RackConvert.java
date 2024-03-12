package com.tt.wms.convert;

import com.tt.wms.domain.entity.Rack;
import com.tt.wms.domain.vo.RackVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 货架  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface RackConvert {
    List<RackVO> dos2vos(List<Rack> list);
}
