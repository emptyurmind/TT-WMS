package com.tt.wms.convert;

import com.tt.wms.domain.entity.Area;
import com.tt.wms.domain.vo.AreaVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库区  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface AreaConvert {
    List<AreaVO> dos2vos(List<Area> list);
}
