package com.tt.wms.convert;

import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.SupplierTransaction;
import com.tt.wms.domain.vo.SupplierTransactionVO;
import java.util.List;
/**
 * 供应商账户流水  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface SupplierTransactionConvert  {
    List<SupplierTransactionVO> dos2vos(List<SupplierTransaction> list);
}
