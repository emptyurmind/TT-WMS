package com.tt.wms.service;

import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.query.InventoryHistoryQuery;
import com.tt.wms.domain.vo.InventoryHistoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存记录Service业务层
 *
 * @author wangkun
 */
public interface InventoryHistoryService {
    InventoryHistory selectById(Long id);

    List<InventoryHistoryVO> selectList(InventoryHistoryQuery query);

    Page<InventoryHistoryVO> selectList(InventoryHistoryQuery query, Pageable page);

    int insert(InventoryHistory inventoryHistory);

    int update(InventoryHistory inventoryHistory);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    int deleteByForm(Long formId, Integer... formType);

    List<InventoryHistory> selectByForm(Long formId, Integer... formType);

    int batchInsert(List<InventoryHistory> list);
}
