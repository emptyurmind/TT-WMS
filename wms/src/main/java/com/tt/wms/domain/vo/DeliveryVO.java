package com.tt.wms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 发货记录 数据视图对象
 *
 * @auhtor wangkun
 */
@Data
public class DeliveryVO extends BaseAudit {
    /**
     * ID
     */
    private Long id;
    /**
     * 出库单主表Id
     */
    @Excel(name = "出库单主表Id")
    private Long shipmentOrderId;
    /**
     * 承运商Id
     */
    @Excel(name = "承运商Id")
    private Long carrierId;
    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发货日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryDate;
    /**
     * 快递单号
     */
    @Excel(name = "快递单号")
    private String trackingNo;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
