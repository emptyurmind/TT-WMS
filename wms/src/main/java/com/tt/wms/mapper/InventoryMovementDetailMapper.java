package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.InventoryMovementDetail;
import com.tt.wms.domain.vo.InventoryMovementVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存移动详情Mapper接口
 *
 * @auhtor wangkun
 */
public interface InventoryMovementDetailMapper extends BaseMapper<InventoryMovementDetail> {
    /**
     * 查询库存移动详情列表
     *
     * @param inventoryMovementDetail 库存移动详情
     * @return 库存移动详情集合
     */
    List<InventoryMovementDetail> selectByEntity(InventoryMovementDetail inventoryMovementDetail);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    List<InventoryMovementVO> countByOrderId(List<Long> ids);

    int batchInsert(List<InventoryMovementDetail> inventoryMovementDetails);
}
