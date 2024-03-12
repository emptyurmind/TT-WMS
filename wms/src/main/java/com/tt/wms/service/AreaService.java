package com.tt.wms.service;

import com.tt.wms.domain.entity.Area;
import com.tt.wms.domain.query.AreaQuery;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 货区Service业务层
 *
 * @Author wangkun
 */
public interface AreaService {
    Area selectById(Long id);

    List<Area> selectList(AreaQuery query, Pageable page);

    int insert(Area area);

    int update(Area area);

    int deleteByIds(Long[] ids);

    int deleteById(Long id);

    List<Area> selectByIdIn(Collection<Long> ids);
}
