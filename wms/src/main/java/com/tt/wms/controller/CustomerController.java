package com.tt.wms.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tt.wms.convert.CustomerConvert;
import com.tt.wms.domain.entity.Customer;
import com.tt.wms.domain.query.CustomerQuery;
import com.tt.wms.domain.vo.CustomerVO;
import com.tt.wms.service.CustomerService;
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
 * 客户Controller
 *
 * @author wangkun
 */
@Api(description = "客户接口列表")
@RestController
@RequestMapping("/wms/customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerConvert customerConvert;

    @ApiOperation("查询客户列表")
    @PreAuthorize("@ss.hasPermi('wms:customer:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Customer>> list(@RequestBody CustomerQuery query, Pageable page) {
        List<Customer> list = customerService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal()));
    }

    @ApiOperation("导出客户列表")
    @PreAuthorize("@ss.hasPermi('wms:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(CustomerQuery query) {
        List<Customer> list = customerService.selectList(query, null);
        ExcelUtil<CustomerVO> util = new ExcelUtil<>(CustomerVO.class);
        return ResponseEntity.ok(util.writeExcel(customerConvert.dos2vos(list), "客户数据"));
    }

    @ApiOperation("获取客户详细信息")
    @PreAuthorize("@ss.hasPermi('wms:customer:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.selectById(id));
    }

    @ApiOperation("新增客户")
    @PreAuthorize("@ss.hasPermi('wms:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.insert(customer));
    }

    @ApiOperation("修改客户")
    @PreAuthorize("@ss.hasPermi('wms:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(customer));
    }

    @ApiOperation("删除客户")
    @PreAuthorize("@ss.hasPermi('wms:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(customerService.deleteByIds(ids));
    }
}
