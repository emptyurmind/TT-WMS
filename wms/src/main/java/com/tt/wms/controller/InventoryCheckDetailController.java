package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.InventoryCheckDetailConvert;
import com.tt.wms.domain.entity.InventoryCheckDetail;
import com.tt.wms.domain.query.InventoryCheckDetailQuery;
import com.tt.wms.domain.vo.InventoryCheckDetailVO;
import com.tt.wms.service.InventoryCheckDetailService;
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
 * 库存盘点单据详情Controller
 *
 * @author wangkun
 * @date 2023-04-25
 */
@Api(description = "库存盘点单据详情接口列表")
@RestController
@RequestMapping("/wms/inventoryCheckDetail")
public class InventoryCheckDetailController extends BaseController {

    @Resource
    private InventoryCheckDetailService inventoryCheckDetailService;

    @Resource
    private InventoryCheckDetailConvert inventoryCheckDetailConvert;

    @ApiOperation("查询库存盘点单据详情列表")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<InventoryCheckDetail>> list(@RequestBody InventoryCheckDetailQuery query, Pageable page) {
        List<InventoryCheckDetail> list = inventoryCheckDetailService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出库存盘点单据详情列表")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:export')")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(InventoryCheckDetailQuery query) {
        List<InventoryCheckDetail> list = inventoryCheckDetailService.selectList(query, null);
        ExcelUtil<InventoryCheckDetailVO> util = new ExcelUtil<>(InventoryCheckDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(inventoryCheckDetailConvert.dos2vos(list), "库存盘点单据详情数据"));
    }

    @ApiOperation("获取库存盘点单据详情详细信息")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventoryCheckDetail> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventoryCheckDetailService.selectById(id));
    }

    @ApiOperation("新增库存盘点单据详情")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:add')")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody InventoryCheckDetail inventoryCheckDetail) {
        return ResponseEntity.ok(inventoryCheckDetailService.insert(inventoryCheckDetail));
    }

    @ApiOperation("修改库存盘点单据详情")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:edit')")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody InventoryCheckDetail inventoryCheckDetail) {
        return ResponseEntity.ok(inventoryCheckDetailService.update(inventoryCheckDetail));
    }

    @ApiOperation("删除库存盘点单据详情")
    @PreAuthorize("@ss.hasPermi('wms:inventoryCheckDetail:remove')")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(inventoryCheckDetailService.deleteByIds(ids));
    }
}
