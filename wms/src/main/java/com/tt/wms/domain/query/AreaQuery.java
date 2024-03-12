package com.tt.wms.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 货区 查询 对象
 *
 * @author wangkun
 */
@ApiModel(description = "货区 查询 对象")
@Data
public class AreaQuery {
    @ApiModelProperty("货区编号 精确匹配")
    private String areaNo;

    @ApiModelProperty("货区名称 精确匹配")
    private String areaNameLike;

    @ApiModelProperty("所属仓库ID 精确匹配")
    private Long warehouseId;

}
