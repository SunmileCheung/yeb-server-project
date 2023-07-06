package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.SalaryMapper;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.service.IEmployeeService;
import com.xxxx.server.service.ISalarySobCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author C
 * @Description
 * @Date create in 2023/7/5 17:11
 */
@Service
public class SalarySobCfgServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalarySobCfgService {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 更新员工账套
     * @param eid
     * @param sid
     * @return
     */
    @Override
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");

    }
}
