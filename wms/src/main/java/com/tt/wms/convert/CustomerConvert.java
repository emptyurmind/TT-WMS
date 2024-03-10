package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.Customer;
import com.tt.wms.domain.vo.CustomerVO;
import java.util.List;
/**
 * 客户  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface CustomerConvert  {
    List<CustomerVO> dos2vos(List<Customer> list);
}
