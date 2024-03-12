package com.tt.wms.service;

import com.tt.wms.domain.entity.InventoryMovement;
import com.tt.wms.domain.form.InventoryMovementForm;
import com.tt.wms.domain.query.InventoryMovementQuery;
import com.tt.wms.domain.vo.InventoryMovementVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存移动Service业务层
 *
 * @author wangkun
 */
public interface InventoryMovementService {
    InventoryMovement selectById(Long id);

    Page<InventoryMovementVO> selectList(InventoryMovementQuery query, Pageable page);

    int insert(InventoryMovement inventoryMovement);

    int update(InventoryMovement inventoryMovement);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    int addOrUpdate(InventoryMovementForm inventoryMovementForm);
}
