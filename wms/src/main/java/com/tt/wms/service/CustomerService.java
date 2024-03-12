package com.tt.wms.service;

import com.tt.wms.domain.entity.Customer;
import com.tt.wms.domain.query.CustomerQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 客户Service业务层
 *
 * @Author wangkun
 */
public interface CustomerService {
    Customer selectById(Long id);

    List<Customer> selectList(CustomerQuery query, Pageable page);

    int insert(Customer customer);

    int update(Customer customer);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
