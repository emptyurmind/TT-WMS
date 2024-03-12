package com.tt.wms.service;

import com.tt.wms.domain.entity.Carrier;
import com.tt.wms.domain.query.CarrierQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 承运商Service业务层
 *
 * @Author wangkun
 */
public interface CarrierService {
    Carrier selectById(Long id);

    List<Carrier> selectList(CarrierQuery query, Pageable page);

    int insert(Carrier carrier);

    int update(Carrier carrier);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);
}
