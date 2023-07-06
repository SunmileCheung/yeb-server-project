package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.pojo.SalaryDto;

import java.util.List;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
public interface ISalaryService extends IService<Salary> {

    //更新工资账套
    RespBean updateSalaries(Salary salary);

    //添加工资账套
    RespBean addSalaries(Salary salary);

    //删除工资账套
    RespBean deleteSalaries(Integer id);
    public List<SalaryDto> getAllSalaryOfAllEmp();

}
