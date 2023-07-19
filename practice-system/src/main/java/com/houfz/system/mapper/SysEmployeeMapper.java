package com.houfz.system.mapper;

import java.util.List;
import com.houfz.system.domain.SysEmployee;

/**
 * 职工列表Mapper接口
 * 
 * @author houfz
 * @date 2023-07-19
 */
public interface SysEmployeeMapper 
{
    /**
     * 查询职工列表
     * 
     * @param id 职工列表主键
     * @return 职工列表
     */
    public SysEmployee selectSysEmployeeById(Long id);

    /**
     * 查询职工列表列表
     * 
     * @param sysEmployee 职工列表
     * @return 职工列表集合
     */
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee);

    /**
     * 新增职工列表
     * 
     * @param sysEmployee 职工列表
     * @return 结果
     */
    public int insertSysEmployee(SysEmployee sysEmployee);

    /**
     * 修改职工列表
     * 
     * @param sysEmployee 职工列表
     * @return 结果
     */
    public int updateSysEmployee(SysEmployee sysEmployee);

    /**
     * 删除职工列表
     * 
     * @param id 职工列表主键
     * @return 结果
     */
    public int deleteSysEmployeeById(Long id);

    /**
     * 批量删除职工列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysEmployeeByIds(Long[] ids);
}
