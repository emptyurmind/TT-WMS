package com.tt.wms.service;

import com.tt.wms.domain.entity.ShipmentOrder;
import com.tt.wms.domain.form.OrderWaveForm;
import com.tt.wms.domain.form.ShipmentOrderFrom;
import com.tt.wms.domain.query.ShipmentOrderQuery;
import com.tt.wms.domain.vo.ShipmentOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * @author wangkun
 */
public interface ShipmentOrderService {
    ShipmentOrderFrom selectById(Long id);

    Page<ShipmentOrderVO> selectList(ShipmentOrderQuery query, Pageable page);

    int insert(ShipmentOrder shipmentOrder);

    int update(ShipmentOrder shipmentOrder);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    int add(ShipmentOrderFrom order);

    int update(ShipmentOrderFrom order);

    void allocatedInventory(long id, Integer type);

    void updateWaveNo(Long orderId, String waveNo);

    OrderWaveForm selectDetailByWaveNo(String waveNo);

    void deleteByWaveIds(Collection<String> ids);
}
