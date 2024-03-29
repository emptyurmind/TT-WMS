package com.tt.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.tt.wms.domain.entity.Carrier;
import com.tt.wms.domain.query.CarrierQuery;
import com.tt.wms.mapper.CarrierMapper;
import com.tt.wms.service.CarrierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 承运商Service业务层处理
 *
 * @author wangkun
 */
@Service
public class CarrierServiceImpl implements CarrierService {

    @Resource
    private CarrierMapper carrierMapper;

    /**
     * 查询承运商
     *
     * @param id 承运商主键
     * @return 承运商
     */
    @Override
    public Carrier selectById(Long id) {
        return carrierMapper.selectById(id);
    }

    /**
     * 查询承运商列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 承运商
     */
    @Override
    public List<Carrier> selectList(CarrierQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Carrier> qw = new QueryWrapper<>();
        qw.eq("del_flag", 0);
        String carrierNo = query.getCarrierNo();
        if (!StringUtils.isEmpty(carrierNo)) {
            qw.eq("carrier_no", carrierNo);
        }
        String carrierNameLike = query.getCarrierNameLike();
        if (!StringUtils.isEmpty(carrierNameLike)) {
            qw.like("carrier_name", carrierNameLike);
        }
        String address = query.getAddress();
        if (!StringUtils.isEmpty(address)) {
            qw.eq("address", address);
        }
        String mobile = query.getMobile();
        if (!StringUtils.isEmpty(mobile)) {
            qw.eq("mobile", mobile);
        }
        String tel = query.getTel();
        if (!StringUtils.isEmpty(tel)) {
            qw.eq("tel", tel);
        }
        String contact = query.getContact();
        if (!StringUtils.isEmpty(contact)) {
            qw.eq("contact", contact);
        }
        String level = query.getLevel();
        if (!StringUtils.isEmpty(level)) {
            qw.eq("level", level);
        }
        String email = query.getEmail();
        if (!StringUtils.isEmpty(email)) {
            qw.eq("email", email);
        }
        return carrierMapper.selectList(qw);
    }

    /**
     * 新增承运商
     *
     * @param carrier 承运商
     * @return 结果
     */
    @Override
    public int insert(Carrier carrier) {
        carrier.setDelFlag(0);
        carrier.setCreateTime(LocalDateTime.now());
        return carrierMapper.insert(carrier);
    }

    /**
     * 修改承运商
     *
     * @param carrier 承运商
     * @return 结果
     */
    @Override
    public int update(Carrier carrier) {
        return carrierMapper.updateById(carrier);
    }

    /**
     * 批量删除承运商
     *
     * @param ids 需要删除的承运商主键
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return carrierMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除承运商信息
     *
     * @param id 承运商主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        Long[] ids = {id};
        return carrierMapper.updateDelFlagByIds(ids);
    }
}
