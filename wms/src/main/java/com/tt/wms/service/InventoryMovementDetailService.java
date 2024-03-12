package com.tt.wms.service;

import com.tt.wms.domain.entity.InventoryMovement;
import com.tt.wms.domain.entity.InventoryMovementDetail;
import com.tt.wms.domain.query.InventoryMovementDetailQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存移动详情Service业务层
 *
 * @author wangkun
 */
public interface InventoryMovementDetailService {
    InventoryMovementDetail selectById(Long id);

    List<InventoryMovementDetail> selectList(InventoryMovementDetailQuery query, Pageable page);

    int insert(InventoryMovementDetail inventoryMovementDetail);

    int update(InventoryMovementDetail inventoryMovementDetail);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    void updateDelFlag(InventoryMovement inventoryMovement);
}
