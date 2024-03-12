package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.CarrierConvert;
import com.tt.wms.domain.entity.Carrier;
import com.tt.wms.domain.query.CarrierQuery;
import com.tt.wms.domain.vo.CarrierVO;
import com.tt.wms.service.CarrierService;
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
 * 承运商Controller
 *
 * @author wangkun
 */
@Api(description = "承运商接口列表")
@RestController
@RequestMapping("/wms/carrier")
public class CarrierController extends BaseController {

    @Resource
    private CarrierService carrierService;

    @Resource
    private CarrierConvert carrierConvert;

    @ApiOperation("查询承运商列表")
    @PreAuthorize("@ss.hasPermi('wms:carrier:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Carrier>> list(@RequestBody CarrierQuery query, Pageable page) {
        List<Carrier> list = carrierService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出承运商列表")
    @PreAuthorize("@ss.hasPermi('wms:carrier:export')")
    @Log(title = "承运商", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(CarrierQuery query) {
        List<Carrier> list = carrierService.selectList(query, null);
        ExcelUtil<CarrierVO> util = new ExcelUtil<>(CarrierVO.class);
        return ResponseEntity.ok(util.writeExcel(carrierConvert.dos2vos(list), "承运商数据"));
    }

    @ApiOperation("获取承运商详细信息")
    @PreAuthorize("@ss.hasPermi('wms:carrier:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrier> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carrierService.selectById(id));
    }

    @ApiOperation("新增承运商")
    @PreAuthorize("@ss.hasPermi('wms:carrier:add')")
    @Log(title = "承运商", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Carrier carrier) {
        return ResponseEntity.ok(carrierService.insert(carrier));
    }

    @ApiOperation("修改承运商")
    @PreAuthorize("@ss.hasPermi('wms:carrier:edit')")
    @Log(title = "承运商", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Carrier carrier) {
        return ResponseEntity.ok(carrierService.update(carrier));
    }

    @ApiOperation("删除承运商")
    @PreAuthorize("@ss.hasPermi('wms:carrier:remove')")
    @Log(title = "承运商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(carrierService.deleteByIds(ids));
    }
}
