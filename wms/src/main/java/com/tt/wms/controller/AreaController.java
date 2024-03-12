package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.AreaConvert;
import com.tt.wms.domain.entity.Area;
import com.tt.wms.domain.query.AreaQuery;
import com.tt.wms.domain.vo.AreaVO;
import com.tt.wms.service.AreaService;
import com.tt.wms.service.impl.AreaServiceImpl;
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
 * 货区Controller
 *
 * @auhtor wangkun
 */
@Api(description = "货区接口列表")
@RestController
@RequestMapping("/wms/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaConvert areaConvert;

    @ApiOperation("查询货区列表")
    @PreAuthorize("@ss.hasPermi('wms:area:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Area>> list(@RequestBody AreaQuery query, Pageable page) {
        List<Area> list = areaService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出货区列表")
    @PreAuthorize("@ss.hasPermi('wms:area:export')")
    @Log(title = "货区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(AreaQuery query) {
        List<Area> list = areaService.selectList(query, null);
        ExcelUtil<AreaVO> util = new ExcelUtil<>(AreaVO.class);
        return ResponseEntity.ok(util.writeExcel(areaConvert.dos2vos(list), "货区数据"));
    }

    @ApiOperation("获取货区详细信息")
    @PreAuthorize("@ss.hasPermi('wms:area:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Area> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(areaService.selectById(id));
    }

    @ApiOperation("新增货区")
    @PreAuthorize("@ss.hasPermi('wms:area:add')")
    @Log(title = "货区", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Area area) {
        return ResponseEntity.ok(areaService.insert(area));
    }

    @ApiOperation("修改货区")
    @PreAuthorize("@ss.hasPermi('wms:area:edit')")
    @Log(title = "货区", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Area area) {
        return ResponseEntity.ok(areaService.update(area));
    }

    @ApiOperation("删除货区")
    @PreAuthorize("@ss.hasPermi('wms:area:remove')")
    @Log(title = "货区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(areaService.deleteByIds(ids));
    }
}
