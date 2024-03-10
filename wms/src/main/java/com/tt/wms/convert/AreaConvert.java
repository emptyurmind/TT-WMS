package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Area;
import com.tt.wms.domain.vo.AreaVO;
import java.util.List;
/**
 * 库区  ENTITY <=> VO / Form / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface AreaConvert  {
    List<AreaVO> dos2vos(List<Area> list);
}
