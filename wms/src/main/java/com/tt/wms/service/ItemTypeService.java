package com.tt.wms.service;

import com.tt.wms.domain.entity.ItemType;
import com.tt.wms.domain.entity.ItemTypeTreeSelect;
import com.tt.wms.domain.query.ItemTypeQuery;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 物料类型表Service业务层
 *
 * @author wangkun
 */
public interface ItemTypeService {
    /**
     * 查询物料类型表
     *
     * @param ids 物料类型表主键
     * @return 物料类型表
     */
    List<ItemType> selectByIdIn(Collection<Long> ids);

    /**
     * 查询物料类型表
     *
     * @param itemTypeId 物料类型表主键
     * @return 物料类型表
     */
    ItemType selectByItemTypeId(Long itemTypeId);

    /**
     * 查询物料类型表列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 物料类型表
     */
    List<ItemType> selectList(ItemTypeQuery query, Pageable page);

    /**
     * 插入物料类型表
     *
     * @param itemType 物料类型表
     * @return 结果
     */
    int insert(ItemType itemType);

    /**
     * 更新物料类型表
     *
     * @param itemType 物料类型表
     * @return 结果
     */
    int update(ItemType itemType);

    /**
     * 删除物料类型表
     *
     * @param ids 物料类型表主键
     * @return 结果
     */
    int deleteByItemTypeIds(Long[] ids);

    /**
     * 删除物料类型表
     *
     * @param id 物料类型表主键
     * @return 结果
     */
    int deleteByItemTypeId(Long id);

    List<ItemTypeTreeSelect> buildItemTypeTreeSelect(List<ItemType> itemTypes);

    List<ItemType> buildDeptTree(List<ItemType> itemTypes);
}
