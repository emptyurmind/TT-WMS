package com.tt.wms.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户账户流水 查询 对象
 *
 * @author wangkun
 */
@ApiModel(description = "客户账户流水 查询 对象")
@Data
public class CustomerTransactionQuery {
    @ApiModelProperty("交易编号 精确匹配")
    private String transactionCode;

    @ApiModelProperty("用户编号 精确匹配")
    private String customerId;

    @ApiModelProperty("交易类型  1：结款  2：应付  精确匹配")
    private String transactionType;

    @ApiModelProperty("交易金额 精确匹配")
    private BigDecimal transactionAmount;

    @ApiModelProperty("上期余额 精确匹配")
    private BigDecimal previousBalance;

    @ApiModelProperty("当前余额 精确匹配")
    private BigDecimal currentBalance;

    @ApiModelProperty("出库单号 精确匹配")
    private Long shipmentOrderId;

    @ApiModelProperty("开始时间 精确匹配")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间 精确匹配")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
