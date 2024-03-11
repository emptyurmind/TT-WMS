package com.tt.wms.convert;

import com.tt.wms.domain.entity.Wave;
import com.tt.wms.domain.vo.WaveVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 波次  DO <=> DTO <=> VO / BO / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface WaveConvert {

    List<WaveVO> dos2vos(List<Wave> list);
}
