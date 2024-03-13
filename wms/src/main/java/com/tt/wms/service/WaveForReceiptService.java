package com.tt.wms.service;

import com.tt.wms.domain.entity.Wave;
import com.tt.wms.domain.form.OrderWaveReceiptForm;

/**
 * @author wangkun
 */
public interface WaveForReceiptService {
    OrderWaveReceiptForm getReceiptOrders(Long id);

    int creatWaveForReceipt(Wave wave);

    OrderWaveReceiptForm allocatedInventoryForReceipt(Long id, Integer type);

    int confirmWaveForReceipt(OrderWaveReceiptForm order);

    Integer cancelAllocatedInventoryForReceipt(Long id);

    Integer deleteByIdsForReceipt(Long[] ids);


}
