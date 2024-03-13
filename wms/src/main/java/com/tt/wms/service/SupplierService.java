package com.tt.wms.service;

import com.tt.wms.domain.entity.Supplier;
import com.tt.wms.domain.query.SupplierQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangkun
 */
public interface SupplierService {
    Supplier selectById(Long id);

    List<Supplier> selectList(SupplierQuery query, Pageable page);

    int insert(Supplier supplier);

    int update(Supplier supplier);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
