package com.tt.wms.convert;

import com.tt.wms.domain.entity.CustomerTransaction;
import com.tt.wms.domain.vo.CustomerTransactionVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 客户账户流水  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface CustomerTransactionConvert {
    List<CustomerTransactionVO> dos2vos(List<CustomerTransaction> list);
}
