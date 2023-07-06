package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    //获取所有员工账套
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);

    //根据名字查询员工
    List<Employee> selectByName(String name);


    //多条件查询员工薪资
    IPage<Employee> getSalarySearch(Page<Employee> page, @Param("ename") String ename,@Param("dname") String dname);
}
