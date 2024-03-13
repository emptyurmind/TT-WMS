package com.tt.wms.service;

import com.tt.wms.domain.entity.ReceiptOrder;
import com.tt.wms.domain.form.OrderWaveReceiptForm;
import com.tt.wms.domain.form.ReceiptOrderForm;
import com.tt.wms.domain.query.ReceiptOrderQuery;
import com.tt.wms.domain.vo.ReceiptOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 入库单Service业务层
 *
 * @author wangkun
 */
public interface ReceiptOrderService {
    void deleteByWaveIds(Collection<String> ids);

    ReceiptOrderForm selectById(Long id);

    List<ReceiptOrderVO> selectList(ReceiptOrderQuery query);

    Page<ReceiptOrderVO> selectList(ReceiptOrderQuery query, Pageable page);

    int add(ReceiptOrderForm receiptOrder);

    int update(ReceiptOrderForm receiptOrder);

    int update(ReceiptOrder receiptOrder);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    OrderWaveReceiptForm selectDetailByWaveNo(String waveNo);

    void updateWaveNo(Long orderId, String waveNo);


}
