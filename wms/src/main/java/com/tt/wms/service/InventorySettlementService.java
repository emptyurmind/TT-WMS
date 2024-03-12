package com.tt.wms.service;

import com.tt.wms.domain.entity.InventorySettlement;
import com.tt.wms.domain.form.InventorySettlementForm;
import com.tt.wms.domain.query.InventorySettlementQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存结算单Service业务层
 *
 * @author wangkun
 */
public interface InventorySettlementService {
    InventorySettlementForm selectById(Long id);

    List<InventorySettlement> selectList(InventorySettlementQuery query, Pageable page);

    int insert(InventorySettlement inventorySettlement);

    int update(InventorySettlement inventorySettlement);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    int addOrUpdate(InventorySettlementForm inventorySettlementForm);
}
