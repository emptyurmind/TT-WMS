package com.tt.wms.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 波次 数据视图对象
 * 
 * @author wangkun
 */
@Data
public class WaveVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 波次号 */
    @Excel(name = "波次号")
    private String waveNo;
   /** 状态 */
    @Excel(name = "状态")
    private String status;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
