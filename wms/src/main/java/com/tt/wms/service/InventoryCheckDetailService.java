package com.tt.wms.service;

import com.tt.wms.domain.entity.InventoryCheckDetail;
import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.query.InventoryCheckDetailQuery;
import com.tt.wms.domain.vo.InventoryCheckDetailVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 库存盘点单据详情Service业务层
 *
 * @author wangkun
 */
public interface InventoryCheckDetailService {
    InventoryCheckDetail selectById(Long id);

    List<InventoryCheckDetail> selectList(InventoryCheckDetailQuery query, Pageable page);

    int insert(InventoryCheckDetail inventoryCheckDetail);

    int update(InventoryCheckDetail inventoryCheckDetail);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    InventoryHistory vo2InventoryHistory(InventoryCheckDetailVO it);

    List<InventoryCheckDetailVO> toVos(List<InventoryCheckDetail> items);
}
