package com.tt.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.tt.wms.domain.entity.Customer;
import com.tt.wms.domain.entity.CustomerTransaction;
import com.tt.wms.domain.query.CustomerTransactionQuery;
import com.tt.wms.mapper.CustomerTransactionMapper;
import com.tt.wms.service.CustomerService;
import com.tt.wms.service.CustomerTransactionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 客户账户流水Service业务层处理
 *
 * @author wangkun
 */
@Service
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    @Resource
    private CustomerTransactionMapper customerTransactionMapper;

    @Autowired
    private CustomerService customerService;

    /**
     * 查询客户账户流水
     *
     * @param id 客户账户流水主键
     * @return 客户账户流水
     */
    @Override
    public CustomerTransaction selectById(Integer id) {
        return customerTransactionMapper.selectById(id);
    }

    /**
     * 查询客户账户流水列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 客户账户流水
     */
    @Override
    public List<CustomerTransaction> selectList(CustomerTransactionQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), "create_time desc");
        }
        LambdaQueryWrapper<CustomerTransaction> qw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getCustomerId())) {
            qw.eq(CustomerTransaction::getCustomerId, query.getCustomerId());
        }
        if (!StringUtils.isEmpty(query.getTransactionCode())) {
            qw.eq(CustomerTransaction::getTransactionCode, query.getTransactionCode());
        }
        if (!StringUtils.isEmpty(query.getTransactionType())) {
            qw.eq(CustomerTransaction::getTransactionType, query.getTransactionType());
        }
        Optional.ofNullable(query.getStartTime()).ifPresent(
                startTime -> qw.ge(CustomerTransaction::getCreateTime, query.getStartTime())
        );
        Optional.ofNullable(query.getEndTime()).ifPresent(
                startTime -> qw.le(CustomerTransaction::getCreateTime, query.getEndTime())
        );
        return customerTransactionMapper.selectList(qw);
    }

    /**
     * 新增客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    @Override
    public int insert(CustomerTransaction customerTransaction) {
        Customer customer = customerService.selectById(Long.valueOf(customerTransaction.getCustomerId()));
        if (customer == null) {
            return 0;
        }
        customerTransaction.setCreateTime(LocalDateTime.now());
        customerTransaction.setPreviousBalance(customer.getReceivableAmount());
        BigDecimal duePay = customer.getReceivableAmount();
        BigDecimal after = customer.getReceivableAmount();
        if (CustomerTransaction.ENTER.equals(customerTransaction.getTransactionType())) {
            after = duePay.subtract(customerTransaction.getTransactionAmount());
        } else if (CustomerTransaction.EXIT.equals(customerTransaction.getTransactionType())) {
            after = duePay.add(customerTransaction.getTransactionAmount());
        } else if (CustomerTransaction.SHIPMENT.equals(customerTransaction.getTransactionType())) {
            //查询该出库单是否已经添加
            LambdaQueryWrapper<CustomerTransaction> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(CustomerTransaction::getShipmentOrderId, customerTransaction.getShipmentOrderId());
            queryWrapper.orderByDesc(CustomerTransaction::getId);
            List<CustomerTransaction> customerTransactions = customerTransactionMapper.selectList(queryWrapper);
            if (!customerTransactions.isEmpty()) {
                //更新出库单金额
                CustomerTransaction customerTransaction1 = customerTransactions.get(0);
                if (customerTransaction1.getTransactionAmount().compareTo(customerTransaction.getTransactionAmount()) != 0) {
                    //发生金额变化
                    after = duePay.add(customerTransaction.getTransactionAmount().subtract(customerTransaction1.getTransactionAmount()));
                } else {
                    //无金额变化
                    return 0;
                }
            } else {
                //新增
                after = duePay.add(customerTransaction.getTransactionAmount());
            }
        }
        customerTransaction.setCurrentBalance(after);

        //更新客户 应收款
        customer.setReceivableAmount(after);
        customerService.update(customer);

        return customerTransactionMapper.insert(customerTransaction);
    }

    /**
     * 修改客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    @Override
    public int update(CustomerTransaction customerTransaction) {
        return customerTransactionMapper.updateById(customerTransaction);
    }

    /**
     * 批量删除客户账户流水
     *
     * @param ids 需要删除的客户账户流水主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除客户账户流水信息
     *
     * @param id 客户账户流水主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }
}
