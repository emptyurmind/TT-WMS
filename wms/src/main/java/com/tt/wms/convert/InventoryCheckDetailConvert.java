package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventoryCheckDetail;
import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.vo.InventoryCheckDetailVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 库存盘点单据详情  ENTITY <=> VO / Form / Query
 *
 * @auhtor wangkun
 */
@Mapper(componentModel = "spring")
public interface InventoryCheckDetailConvert {
    List<InventoryCheckDetailVO> dos2vos(List<InventoryCheckDetail> list);

    List<InventoryCheckDetail> vos2dos(List<InventoryCheckDetailVO> details);

    InventoryHistory vo2InventoryHistory(InventoryCheckDetailVO it);
}
