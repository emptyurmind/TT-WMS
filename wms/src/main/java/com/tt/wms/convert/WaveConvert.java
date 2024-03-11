package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Wave;
import com.tt.wms.domain.vo.WaveVO;
import java.util.List;
/**
 * 波次  DO <=> DTO <=> VO / BO / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface WaveConvert  {

    List<WaveVO> dos2vos(List<Wave> list);
}
