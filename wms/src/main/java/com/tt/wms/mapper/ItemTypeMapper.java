package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.ItemType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料类型表Mapper接口
 *
 * @auhtor wangkun
 */
public interface ItemTypeMapper extends BaseMapper<ItemType> {
    /**
     * 查询物料类型表列表
     *
     * @param itemType 物料类型表
     * @return 物料类型表集合
     */
    List<ItemType> selectByEntity(ItemType itemType);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
