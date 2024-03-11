package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.InventoryMovement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存移动Mapper接口
 *
 * @auhtor wangkun
 */
public interface InventoryMovementMapper extends BaseMapper<InventoryMovement> {
    /**
     * 查询库存移动列表
     *
     * @param inventoryMovement 库存移动
     * @return 库存移动集合
     */
    List<InventoryMovement> selectByEntity(InventoryMovement inventoryMovement);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
