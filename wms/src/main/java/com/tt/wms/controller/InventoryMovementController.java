package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.InventoryMovementConvert;
import com.tt.wms.domain.entity.InventoryMovement;
import com.tt.wms.domain.form.InventoryMovementForm;
import com.tt.wms.domain.query.InventoryMovementQuery;
import com.tt.wms.domain.vo.InventoryMovementVO;
import com.tt.wms.service.InventoryMovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 库存移动Controller
 *
 * @author wangkun
 */
@Api(description = "库存移动接口列表")
@RestController
@RequestMapping("/wms/inventoryMovement")
public class InventoryMovementController extends BaseController {

    @Autowired
    private InventoryMovementService service;

    @Autowired
    private InventoryMovementConvert convert;

    @ApiOperation("查询库存移动列表")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<InventoryMovementVO>> list(@RequestBody InventoryMovementQuery query, Pageable page) {
        return ResponseEntity.ok(service.selectList(query, page));
    }

    @ApiOperation("导出库存移动列表")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:export')")
    @Log(title = "库存移动", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(InventoryMovementQuery query) {
        Page<InventoryMovementVO> page = service.selectList(query, null);
        ExcelUtil<InventoryMovementVO> util = new ExcelUtil<>(InventoryMovementVO.class);
        return ResponseEntity.ok(util.writeExcel(page.getContent(), "库存移动数据"));
    }

    @ApiOperation("获取库存移动详细信息")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventoryMovementForm> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增库存移动")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:add')")
    @Log(title = "库存移动", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody InventoryMovement inventoryMovement) {
        return ResponseEntity.ok(service.insert(inventoryMovement));
    }

    @ApiOperation("修改库存移动")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:edit')")
    @Log(title = "库存移动", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody InventoryMovement inventoryMovement) {
        return ResponseEntity.ok(service.update(inventoryMovement));
    }

    @ApiOperation("删除库存移动")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:remove')")
    @Log(title = "库存移动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

    @ApiOperation("新增或更新库存移动")
    @PreAuthorize("@ss.hasPermi('wms:inventoryMovement:add')")
    @Log(title = "库存移动", businessType = BusinessType.INSERT)
    @PostMapping("add-or-update")
    public ResponseEntity<Integer> addOrUpdate(@RequestBody InventoryMovementForm order) {
        return ResponseEntity.ok(service.addOrUpdate(order));
    }
}
