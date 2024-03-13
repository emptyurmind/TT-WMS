package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.ReceiptOrderDetailConvert;
import com.tt.wms.domain.entity.ReceiptOrderDetail;
import com.tt.wms.domain.query.ReceiptOrderDetailQuery;
import com.tt.wms.domain.vo.ReceiptOrderDetailVO;
import com.tt.wms.service.ReceiptOrderDetailService;
import com.tt.wms.service.impl.ReceiptOrderDetailServiceImpl;
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
 * 入库单详情Controller
 *
 * @author wangkun
 * @date 2022-08-29
 */
@Api(description = "入库单详情接口列表")
@RestController
@RequestMapping("/wms/receiptOrderDetail")
public class ReceiptOrderDetailController extends BaseController {

    @Autowired
    private ReceiptOrderDetailService service;

    @Autowired
    private ReceiptOrderDetailConvert convert;

    @ApiOperation("查询入库单详情列表")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ReceiptOrderDetailVO>> list(@RequestBody ReceiptOrderDetailQuery query, Pageable page) {
        List<ReceiptOrderDetail> items = service.selectList(query, page);
        List<ReceiptOrderDetailVO> list = service.toVos(items);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) items).getTotal()));
    }

    @ApiOperation("导出入库单详情列表")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:export')")
    @Log(title = "入库单详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ReceiptOrderDetailQuery query) {
        List<ReceiptOrderDetail> list = service.selectList(query, null);
        ExcelUtil<ReceiptOrderDetailVO> util = new ExcelUtil<>(ReceiptOrderDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "入库单详情数据"));
    }

    @ApiOperation("获取入库单详情详细信息")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceiptOrderDetailVO> getInfo(@PathVariable("id") Long id) {
        ReceiptOrderDetail item = service.selectById(id);
        ReceiptOrderDetailVO itemVO = service.toVo(item);
        return ResponseEntity.ok(itemVO);
    }

    @ApiOperation("新增入库单详情")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:add')")
    @Log(title = "入库单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ReceiptOrderDetail receiptOrderDetail) {
        return ResponseEntity.ok(service.insert(receiptOrderDetail));
    }

    @ApiOperation("修改入库单详情")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:edit')")
    @Log(title = "入库单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ReceiptOrderDetail receiptOrderDetail) {
        return ResponseEntity.ok(service.update(receiptOrderDetail));
    }

    @ApiOperation("删除入库单详情")
    @PreAuthorize("@ss.hasPermi('wms:receiptOrderDetail:remove')")
    @Log(title = "入库单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
