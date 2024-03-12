package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.WarehouseConvert;
import com.tt.wms.domain.entity.Warehouse;
import com.tt.wms.domain.query.WarehouseQuery;
import com.tt.wms.domain.vo.WarehouseVO;
import com.tt.wms.service.impl.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库Controller
 *
 * @author wangkun
 * @date 2022-08-05
 */
@Api(description = "仓库接口列表")
@RestController
@RequestMapping("/wms/warehouse")
public class WarehouseController extends BaseController {
    @Autowired
    private WarehouseService service;
    @Autowired
    private WarehouseConvert convert;

    @ApiOperation("查询仓库列表")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Warehouse>> list(@RequestBody WarehouseQuery query, Pageable page) {
        List<Warehouse> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出仓库列表")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:export')")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WarehouseQuery query) {
        List<Warehouse> list = service.selectList(query, null);
        ExcelUtil<WarehouseVO> util = new ExcelUtil<>(WarehouseVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "仓库数据"));
    }

    @ApiOperation("获取仓库详细信息")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增仓库")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:add')")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(service.insert(warehouse));
    }

    @ApiOperation("修改仓库")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:edit')")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(service.update(warehouse));
    }

    @ApiOperation("删除仓库")
    @PreAuthorize("@ss.hasPermi('wms:warehouse:remove')")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
