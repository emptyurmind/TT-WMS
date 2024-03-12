package com.tt.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.wms.domain.entity.Inventory;
import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.entity.ShipmentOrderDetail;
import com.tt.wms.domain.query.InventoryQuery;
import com.tt.wms.domain.vo.AreaAndItemInfo;
import com.tt.wms.domain.vo.InventoryHistoryVO;
import com.tt.wms.domain.vo.InventoryVO;
import com.tt.wms.domain.vo.PlaceAndItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

/**
 * 库存Service业务层
 *
 * @author wangkun
 */
public interface InventoryService {
    Inventory selectById(Long id);

    List<Inventory> selectList(InventoryQuery query, Pageable page);

    int insert(Inventory inventory);

    int update(Inventory inventory);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    List<Inventory> getInventoryList(Long panelType, QueryWrapper<Inventory> qw);

    boolean canOutStock(Long itemId, Long warehouseId, Long areaId, Long rackId, BigDecimal quantity);

    Inventory queryInventoryByItemId(Long itemId, Long warehouseId, Long areaId, Long rackId);

    void checkInventory(Long itemId, Long warehouseId, Long areaId, Long rackId, BigDecimal added);

    int batchUpdate(List<InventoryHistory> list);

    int batchUpdateQuantity(List<Inventory> updates, LocalDateTime updateTime, Long userId);

    int batchInsert(List<Inventory> list);

    Page<InventoryVO> queryPage(InventoryQuery query, Pageable page);

    void injectAreaAndItemInfo(List<? extends AreaAndItemInfo> res);

    Page<InventoryVO> queryWarning(Pageable page);

    List<InventoryVO> queryAll(InventoryQuery query);

    List<InventoryVO> queryValidAll();

    void injectItemNoAndItemName(List<Inventory> res);

    void injectWarehouseName(List<Inventory> res);

    void injectAreaName(List<Inventory> res);

    void injectDictDataLabel(List<InventoryHistoryVO> res);

    Integer deleteByItemIds(Long[] itemIds);

    Integer deleteByWarehouseIds(Long[] warehouseIds);

    List<ShipmentOrderDetail> allocatedInventory(Long itemId, BigDecimal planQuantity, Integer type);

    Inventory allocatedInventoryForReceipt(Long itemId, BigDecimal planQuantity, Integer type);
}
