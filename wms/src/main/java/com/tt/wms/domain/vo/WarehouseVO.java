package com.tt.wms.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;

/**
 * 仓库 数据视图对象
 *
 * @author wangkun
 */
@Data
public class WarehouseVO extends BaseAudit {
    /**
     * ID
     */
    private Long id;
    /**
     * 仓库编号
     */
    @Excel(name = "仓库编号")
    private String warehouseNo;
    /**
     * 仓库名称
     */
    @Excel(name = "仓库名称")
    private String warehouseName;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
