package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.mapper.EmployeeMapper;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.SalaryAdjust;
import com.xxxx.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取所有员工账套
     *
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        return new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
    }

    /**
     * 根据名字拆线呢员工
     *
     * @param name
     * @return
     */
    @Override
    public boolean selectByName(String name) {
        List<Employee> employees = employeeMapper.selectByName(name);
        if (employees.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public RespPageBean getSalarySearch(Integer currentPage, Integer size, String ename, String dname) {

        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> salarySearchIPage = employeeMapper.getSalarySearch(page, ename, dname);
        for (int i = 0; i < salarySearchIPage.getRecords().size(); i++) {
            SalaryAdjust salaryAdjust = salarySearchIPage.getRecords().get(i).getSalaryAdjust();
            if (salaryAdjust!=null){
                salarySearchIPage.getRecords().get(i).getSalary().setAllSalary(salaryAdjust.getAfterSalary());
            }
        }
        return new RespPageBean(salarySearchIPage.getTotal(), salarySearchIPage.getRecords());
    }


}
