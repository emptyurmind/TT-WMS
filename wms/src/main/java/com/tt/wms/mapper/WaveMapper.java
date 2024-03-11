package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.Wave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 波次Mapper接口
 *
 * @author wangkun
 */
public interface WaveMapper extends BaseMapper<Wave> {
    /**
     * 查询波次列表
     *
     * @param wave 波次
     * @return 波次集合
     */
    List<Wave> selectByEntity(Wave wave);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
