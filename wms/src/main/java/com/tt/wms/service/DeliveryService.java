package com.tt.wms.service;

import com.tt.wms.domain.entity.Delivery;
import com.tt.wms.domain.query.DeliveryQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 发货记录Service业务层
 *
 * @Author wangkun
 */
public interface DeliveryService {
    Delivery selectById(Long id);

    List<Delivery> selectList(DeliveryQuery query, Pageable page);

    int insert(Delivery delivery);

    int update(Delivery delivery);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
