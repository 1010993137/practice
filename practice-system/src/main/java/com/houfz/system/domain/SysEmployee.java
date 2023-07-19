package com.houfz.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.houfz.common.annotation.Excel;
import com.houfz.common.core.domain.BaseEntity;

/**
 * 职工列表对象 sys_employee
 * 
 * @author houfz
 * @date 2023-07-19
 */
public class SysEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职工ID */
    private Long id;

    /** 职员姓名 */
    @Excel(name = "职员姓名")
    private String empName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 学历 */
    @Excel(name = "学历")
    private String empDegreeName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEmpName(String empName) 
    {
        this.empName = empName;
    }

    public String getEmpName() 
    {
        return empName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setEmpDegreeName(String empDegreeName) 
    {
        this.empDegreeName = empDegreeName;
    }

    public String getEmpDegreeName() 
    {
        return empDegreeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("empName", getEmpName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("deptName", getDeptName())
            .append("empDegreeName", getEmpDegreeName())
            .toString();
    }
}
