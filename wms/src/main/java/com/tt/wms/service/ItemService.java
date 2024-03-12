package com.tt.wms.service;

import com.tt.wms.domain.entity.Item;
import com.tt.wms.domain.query.ItemQuery;
import com.tt.wms.domain.vo.ItemVO;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 物料Service业务层
 *
 * @author wangkun
 */
public interface ItemService {
    Item selectById(Long id);

    List<Item> selectList(ItemQuery query, Pageable page);

    int insert(Item item);

    int update(Item item);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    List<Item> selectByIdIn(Collection<Long> ids);

    List<Item> getAllSafetyItems();

    void injectTypeName(List<Item> res);

    void injectWarehouseName(List<Item> res);

    void injectAreaName(List<Item> res);

    List<Item> queryExpiry(Pageable page);

    ItemVO toVo(Item item);

    List<ItemVO> toVos(List<Item> items);
}
