package com.tt.wms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.tt.wms.domain.entity.Supplier;

/**
 * 供应商Mapper接口
 * 
 * @auhtor wangkun
 */
public interface SupplierMapper extends BaseMapper<Supplier> {
    /**
     * 查询供应商列表
     *
     * @param supplier 供应商
     * @return 供应商集合
     */
    List<Supplier> selectByEntity(Supplier supplier);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
