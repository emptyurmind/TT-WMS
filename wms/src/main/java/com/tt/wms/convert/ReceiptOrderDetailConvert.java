package com.tt.wms.convert;

import com.tt.wms.domain.entity.InventoryHistory;
import com.tt.wms.domain.entity.ReceiptOrderDetail;
import com.tt.wms.domain.vo.ReceiptOrderDetailVO;
import org.apache.commons.lang3.SerializationUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 入库单详情  ENTITY <=> VO / Form / Query
 *
 * @author wangkun
 */
@Mapper(componentModel = "spring")
public interface ReceiptOrderDetailConvert {
    List<ReceiptOrderDetailVO> dos2vos(List<ReceiptOrderDetail> list);

    List<ReceiptOrderDetail> vos2dos(List<ReceiptOrderDetailVO> details);

    @Mapping(target = "quantity", source = "realQuantity")
    InventoryHistory do2InventoryHistory(ReceiptOrderDetailVO it);

    default Collection<ReceiptOrderDetailVO> copyList(Collection<ReceiptOrderDetailVO> original) {
        List<ReceiptOrderDetailVO> deepCopy = new ArrayList<>();

        for (ReceiptOrderDetailVO element : original) {
            byte[] serializedObject = SerializationUtils.serialize(element);
            ReceiptOrderDetailVO clonedElement = SerializationUtils.deserialize(serializedObject);
            deepCopy.add(clonedElement);
        }

        return deepCopy;
    }

    /**
     * @param source DO
     * @return VO
     */
    ReceiptOrderDetailVO toVo(ReceiptOrderDetail source);
}
