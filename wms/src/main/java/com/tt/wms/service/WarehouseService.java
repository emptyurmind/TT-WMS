package com.tt.wms.service;

import com.tt.wms.domain.entity.Warehouse;
import com.tt.wms.domain.query.WarehouseQuery;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 仓库Service业务层
 *
 * @author wangkun
 */
public interface WarehouseService {
    Warehouse selectById(Long id);

    List<Warehouse> selectList(WarehouseQuery query, Pageable page);

    int insert(Warehouse warehouse);

    int update(Warehouse warehouse);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    List<Warehouse> selectByIdIn(Collection<Long> ids);
}
