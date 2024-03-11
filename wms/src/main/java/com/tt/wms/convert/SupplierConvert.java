package com.tt.wms.convert;

import com.tt.wms.domain.entity.Supplier;
import com.tt.wms.domain.vo.SupplierVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 供应商  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface SupplierConvert {
    List<SupplierVO> dos2vos(List<Supplier> list);
}
