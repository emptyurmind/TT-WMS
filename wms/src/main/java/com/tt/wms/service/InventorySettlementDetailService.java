package com.tt.wms.service;

import com.tt.wms.domain.entity.InventorySettlementDetail;
import com.tt.wms.domain.query.InventorySettlementDetailQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存结算明细Service业务层
 *
 * @author wangkun
 */
public interface InventorySettlementDetailService {
    InventorySettlementDetail selectById(Long id);

    List<InventorySettlementDetail> selectList(InventorySettlementDetailQuery query, Pageable page);

    int insert(InventorySettlementDetail inventorySettlementDetail);

    int update(InventorySettlementDetail inventorySettlementDetail);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    List<InventorySettlementDetail> listByTime(InventorySettlementDetailQuery query);
}
