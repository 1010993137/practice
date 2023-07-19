package com.houfz.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.houfz.common.annotation.Log;
import com.houfz.common.core.controller.BaseController;
import com.houfz.common.core.domain.AjaxResult;
import com.houfz.common.enums.BusinessType;
import com.houfz.system.domain.SysEmployee;
import com.houfz.system.service.ISysEmployeeService;
import com.houfz.common.utils.poi.ExcelUtil;
import com.houfz.common.core.page.TableDataInfo;

/**
 * 职工列表Controller
 * 
 * @author houfz
 * @date 2023-07-19
 */
@RestController
@RequestMapping("/system/employee")
public class SysEmployeeController extends BaseController
{
    @Autowired
    private ISysEmployeeService sysEmployeeService;

    /**
     * 查询职工列表列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysEmployee sysEmployee)
    {
        startPage();
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        return getDataTable(list);
    }

    /**
     * 导出职工列表列表
     */
    @Log(title = "职工列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEmployee sysEmployee)
    {
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        ExcelUtil<SysEmployee> util = new ExcelUtil<SysEmployee>(SysEmployee.class);
        util.exportExcel(response, list, "职工列表数据");
    }

    /**
     * 获取职工列表详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysEmployeeService.selectSysEmployeeById(id));
    }

    /**
     * 新增职工列表
     */
    @Log(title = "职工列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.insertSysEmployee(sysEmployee));
    }

    /**
     * 修改职工列表
     */
    @Log(title = "职工列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.updateSysEmployee(sysEmployee));
    }

    /**
     * 删除职工列表
     */
    @Log(title = "职工列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysEmployeeService.deleteSysEmployeeByIds(ids));
    }
}
