package com.tt.wms.service;

import com.tt.wms.domain.entity.SupplierTransaction;
import com.tt.wms.domain.query.SupplierTransactionQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 供应商账户流水Service业务层
 *
 * @author wangkun
 */
public interface SupplierTransactionService {
    SupplierTransaction selectById(Integer id);

    List<SupplierTransaction> selectList(SupplierTransactionQuery query, Pageable page);

    int insert(SupplierTransaction supplierTransaction);

    int update(SupplierTransaction supplierTransaction);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
