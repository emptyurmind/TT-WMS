package com.tt.wms.convert;

import com.tt.wms.domain.form.ReceiptOrderForm;
import org.mapstruct.Mapper;
import com.tt.wms.domain.entity.ReceiptOrder;
import com.tt.wms.domain.vo.ReceiptOrderVO;
import java.util.List;
/**
 * 入库单  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface ReceiptOrderConvert  {
    List<ReceiptOrderVO> dos2vos(List<ReceiptOrder> list);

    ReceiptOrderForm do2form(ReceiptOrder bean);
}
