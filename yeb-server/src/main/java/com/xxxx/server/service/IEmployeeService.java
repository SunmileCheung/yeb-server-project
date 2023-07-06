package com.xxxx.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespPageBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);

    //根据名字查询员工
    boolean selectByName(String name);

    //多条件查询
    RespPageBean getSalarySearch(Integer currentPage, Integer size, String ename, String dname);
}
