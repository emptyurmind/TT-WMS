package com.tt.wms.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 库存记录 查询 对象
 *
 * @auhtor wangkun
 */
@ApiModel(description = "库存记录 查询 对象")
@Data
public class InventoryHistoryQuery {
    @ApiModelProperty("操作id（出库、入库、库存移动表单id） 精确匹配")
    private Long formId;

    @ApiModelProperty("操作类型 精确匹配")
    private Integer formType;

    @ApiModelProperty("物料ID 精确匹配")
    private Long itemId;

    @ApiModelProperty("货架id 精确匹配")
    private Long warehouseId;
    private Long areaId;
    private Long rackId;

    @ApiModelProperty("库存变化 精确匹配")
    private BigDecimal quantity;

}
