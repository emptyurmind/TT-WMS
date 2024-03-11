package com.tt.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.wms.domain.entity.ReceiptOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 入库单Mapper接口
 *
 * @auhtor wangkun
 */
public interface ReceiptOrderMapper extends BaseMapper<ReceiptOrder> {
    /**
     * 查询入库单列表
     *
     * @param receiptOrder 入库单
     * @return 入库单集合
     */
    List<ReceiptOrder> selectByEntity(ReceiptOrder receiptOrder);

    /**
     * 批量软删除
     *
     * @param ids
     * @return
     */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
