package com.tt.wms.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 波次 查询 对象
 *
 * @author wangkun
 */
@ApiModel(description = "波次 查询 对象")
@Data
public class WaveQuery {
    @ApiModelProperty("波次号 精确匹配")
    private String waveNo;

    @ApiModelProperty("状态 精确匹配")
    private String status;

    private Integer type;

}
