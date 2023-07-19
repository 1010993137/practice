package com.houfz.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.houfz.system.mapper.SysEmployeeMapper;
import com.houfz.system.domain.SysEmployee;
import com.houfz.system.service.ISysEmployeeService;

/**
 * 职工列表Service业务层处理
 * 
 * @author houfz
 * @date 2023-07-19
 */
@Service
public class SysEmployeeServiceImpl implements ISysEmployeeService 
{
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    /**
     * 查询职工列表
     * 
     * @param id 职工列表主键
     * @return 职工列表
     */
    @Override
    public SysEmployee selectSysEmployeeById(Long id)
    {
        return sysEmployeeMapper.selectSysEmployeeById(id);
    }

    /**
     * 查询职工列表列表
     * 
     * @param sysEmployee 职工列表
     * @return 职工列表
     */
    @Override
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.selectSysEmployeeList(sysEmployee);
    }

    /**
     * 新增职工列表
     * 
     * @param sysEmployee 职工列表
     * @return 结果
     */
    @Override
    public int insertSysEmployee(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.insertSysEmployee(sysEmployee);
    }

    /**
     * 修改职工列表
     * 
     * @param sysEmployee 职工列表
     * @return 结果
     */
    @Override
    public int updateSysEmployee(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    }

    /**
     * 批量删除职工列表
     * 
     * @param ids 需要删除的职工列表主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeByIds(Long[] ids)
    {
        return sysEmployeeMapper.deleteSysEmployeeByIds(ids);
    }

    /**
     * 删除职工列表信息
     * 
     * @param id 职工列表主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeById(Long id)
    {
        return sysEmployeeMapper.deleteSysEmployeeById(id);
    }
}
