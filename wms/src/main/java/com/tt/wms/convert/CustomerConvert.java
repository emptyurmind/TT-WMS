package com.tt.wms.convert;

import com.tt.wms.domain.entity.Customer;
import com.tt.wms.domain.vo.CustomerVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 客户  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface CustomerConvert {
    List<CustomerVO> dos2vos(List<Customer> list);
}
