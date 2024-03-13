package com.tt.wms.service;

import com.tt.wms.domain.entity.Wave;
import com.tt.wms.domain.form.OrderWaveForm;
import com.tt.wms.domain.query.WaveQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 波次Service业务层
 *
 * @author wangkun
 */
public interface WaveService {
    Wave selectById(Long id);

    List<Wave> selectList(WaveQuery query, Pageable page);

    int insert(Wave wave);

    int creatWave(Wave wave);

    OrderWaveForm getShipmentOrders(long id);

    int update(Wave wave);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    OrderWaveForm allocatedInventory(Long id, Integer type);

    Integer confirmWave(OrderWaveForm order);

    Integer cancelAllocatedInventory(Long id);
}
