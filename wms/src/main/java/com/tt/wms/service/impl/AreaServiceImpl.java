package com.tt.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.tt.wms.domain.entity.Area;
import com.tt.wms.domain.query.AreaQuery;
import com.tt.wms.mapper.AreaMapper;
import com.tt.wms.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 货区Service业务层处理
 *
 * @author wangkun
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaMapper areaMapper;

    /**
     * 查询货区
     *
     * @param id 货区主键
     * @return 货区
     */
    @Override
    public Area selectById(Long id) {
        return areaMapper.selectById(id);
    }

    /**
     * 查询货区列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 货区
     */
    @Override
    public List<Area> selectList(AreaQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Area> qw = new QueryWrapper<>();
        qw.eq("del_flag", 0);
        String areaNo = query.getAreaNo();
        if (!StringUtils.isEmpty(areaNo)) {
            qw.eq("area_no", areaNo);
        }
        String areaNameLike = query.getAreaNameLike();
        if (!StringUtils.isEmpty(areaNameLike)) {
            qw.like("area_name", areaNameLike);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        qw.orderByDesc("create_time");
        return areaMapper.selectList(qw);
    }

    /**
     * 新增货区
     *
     * @param area 货区
     * @return 结果
     */
    @Override
    public int insert(Area area) {
        area.setDelFlag(0);
        area.setCreateTime(LocalDateTime.now());
        return areaMapper.insert(area);
    }

    /**
     * 修改货区
     *
     * @param area 货区
     * @return 结果
     */
    @Override
    public int update(Area area) {
        return areaMapper.updateById(area);
    }

    /**
     * 批量删除货区
     *
     * @param ids 需要删除的货区主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return areaMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除货区信息
     *
     * @param id 货区主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return areaMapper.updateDelFlagByIds(ids);
    }

    @Override
    public List<Area> selectByIdIn(Collection<Long> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        QueryWrapper<Area> qw = new QueryWrapper<>();
        qw.in("id", ids);
        return areaMapper.selectList(qw);
    }
}
