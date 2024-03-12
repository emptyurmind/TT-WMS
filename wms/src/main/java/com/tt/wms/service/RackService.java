package com.tt.wms.service;

import com.tt.wms.domain.entity.Rack;
import com.tt.wms.domain.query.RackQuery;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 货架Service业务层
 *
 * @author wangkun
 */
public interface RackService {
    Rack selectById(Long id);

    List<Rack> selectList(RackQuery query, Pageable page);

    int insert(Rack rack);

    int update(Rack rack);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    List<Rack> selectByIdIn(Collection<Long> ids);
}
