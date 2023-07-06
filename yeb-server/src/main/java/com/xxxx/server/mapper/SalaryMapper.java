package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.pojo.SalaryDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    List<SalaryDto> getAllSalaryOfAllEmp();


}
