package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.DeliveryConvert;
import com.tt.wms.domain.entity.Delivery;
import com.tt.wms.domain.query.DeliveryQuery;
import com.tt.wms.domain.vo.DeliveryVO;
import com.tt.wms.service.impl.DeliveryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 发货记录Controller
 *
 * @auhtor wangkun
 * @date 2022-08-05
 */
@Api(description = "发货记录接口列表")
@RestController
@RequestMapping("/wms/delivery")
public class DeliveryController extends BaseController {

    @Resource
    private DeliveryServiceImpl deliveryService;

    @Resource
    private DeliveryConvert deliveryConvert;

    @ApiOperation("查询发货记录列表")
    @PreAuthorize("@ss.hasPermi('wms:delivery:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Delivery>> list(@RequestBody DeliveryQuery query, Pageable page) {
        List<Delivery> list = deliveryService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出发货记录列表")
    @PreAuthorize("@ss.hasPermi('wms:delivery:export')")
    @Log(title = "发货记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(DeliveryQuery query) {
        List<Delivery> list = deliveryService.selectList(query, null);
        ExcelUtil<DeliveryVO> util = new ExcelUtil<>(DeliveryVO.class);
        return ResponseEntity.ok(util.writeExcel(deliveryConvert.dos2vos(list), "发货记录数据"));
    }

    @ApiOperation("获取发货记录详细信息")
    @PreAuthorize("@ss.hasPermi('wms:delivery:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Delivery> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(deliveryService.selectById(id));
    }

    @ApiOperation("新增发货记录")
    @PreAuthorize("@ss.hasPermi('wms:delivery:add')")
    @Log(title = "发货记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.insert(delivery));
    }

    @ApiOperation("修改发货记录")
    @PreAuthorize("@ss.hasPermi('wms:delivery:edit')")
    @Log(title = "发货记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.update(delivery));
    }

    @ApiOperation("删除发货记录")
    @PreAuthorize("@ss.hasPermi('wms:delivery:remove')")
    @Log(title = "发货记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(deliveryService.deleteByIds(ids));
    }
}
