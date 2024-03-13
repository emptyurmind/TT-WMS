package com.tt.wms.service;

import com.tt.wms.domain.entity.ReceiptOrder;
import com.tt.wms.domain.entity.ReceiptOrderDetail;
import com.tt.wms.domain.query.ReceiptOrderDetailQuery;
import com.tt.wms.domain.vo.ReceiptOrderDetailVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 入库单详情Service业务层
 *
 * @author wangkun
 */
public interface ReceiptOrderDetailService {
    ReceiptOrderDetailVO toVo(ReceiptOrderDetail item);

    List<ReceiptOrderDetailVO> toVos(List<ReceiptOrderDetail> items);

    ReceiptOrderDetail selectById(Long id);

    List<ReceiptOrderDetail> selectList(ReceiptOrderDetailQuery query, Pageable page);

    int insert(ReceiptOrderDetail receiptOrderDetail);

    int update(ReceiptOrderDetail receiptOrderDetail);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    void updateDelFlag(ReceiptOrder receiptOrder);
}
