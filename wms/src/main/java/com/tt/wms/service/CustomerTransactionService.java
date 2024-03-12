package com.tt.wms.service;

import com.tt.wms.domain.entity.CustomerTransaction;
import com.tt.wms.domain.query.CustomerTransactionQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 客户账户流水Service业务层
 *
 * @Author wangkun
 */
public interface CustomerTransactionService {
    CustomerTransaction selectById(Integer id);

    List<CustomerTransaction> selectList(CustomerTransactionQuery query, Pageable page);

    int insert(CustomerTransaction customerTransaction);

    int update(CustomerTransaction customerTransaction);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
