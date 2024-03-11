package com.tt.wms.convert;

import com.tt.wms.domain.entity.ShipmentOrder;
import com.tt.wms.domain.form.ShipmentOrderFrom;
import com.tt.wms.domain.vo.ShipmentOrderVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 出库单  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface ShipmentOrderConvert {
    List<ShipmentOrderVO> dos2vos(List<ShipmentOrder> list);

    ShipmentOrderFrom do2form(ShipmentOrder bean);
}
