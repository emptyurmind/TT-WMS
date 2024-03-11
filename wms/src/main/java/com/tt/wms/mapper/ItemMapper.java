package com.tt.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.tt.wms.domain.entity.Item;

/**
 * 物料Mapper接口
 * 
 * @auhtor wangkun
 */
public interface ItemMapper extends BaseMapper<Item> {
    /**
     * 查询物料列表
     *
     * @param item 物料
     * @return 物料集合
     */
    List<Item> selectByEntity(Item item);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    List<Item> selectExpiry();
}
