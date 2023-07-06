package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.pojo.QuerySalaryDate;
import com.xxxx.server.pojo.SalaryDate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.SalarySearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2023-07-04
 */
public interface SalaryDateMapper extends BaseMapper<SalaryDate> {

    //获取所有薪资
    IPage<SalaryDate> getAllSalaryDate(Page<SalaryDate> page);

    //多条件查询
    IPage<SalaryDate> getSalaryDate1(Page<SalaryDate> page, @Param("name") String name,@Param("dname") String dname);



}
