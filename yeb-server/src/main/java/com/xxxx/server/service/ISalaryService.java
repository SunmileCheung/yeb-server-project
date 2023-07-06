package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.pojo.SalaryDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface ISalaryService extends IService<Salary> {

    public List<SalaryDto> getAllSalaryOfAllEmp();

}
