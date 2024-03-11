package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.InventoryCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存盘点单据Mapper接口
 *
 * @auhtor wangkun
 */
public interface InventoryCheckMapper extends BaseMapper<InventoryCheck> {
    /**
     * 查询库存盘点单据列表
     *
     * @param inventoryCheck 库存盘点单据
     * @return 库存盘点单据集合
     */
    List<InventoryCheck> selectByEntity(InventoryCheck inventoryCheck);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
