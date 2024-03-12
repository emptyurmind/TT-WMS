package com.tt.wms.service;

import com.tt.wms.domain.entity.InventoryCheck;
import com.tt.wms.domain.form.InventoryCheckForm;
import com.tt.wms.domain.query.InventoryCheckQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Date 2024/3/12 13:19
 * @author wangkun
 */
public interface InventoryCheckService {
    InventoryCheckForm selectById(Long id);

    List<InventoryCheck> selectList(InventoryCheckQuery query, Pageable page);

    int insert(InventoryCheck inventoryCheck);

    int update(InventoryCheck inventoryCheck);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    int addOrUpdate(InventoryCheckForm inventoryCheckForm);
}
