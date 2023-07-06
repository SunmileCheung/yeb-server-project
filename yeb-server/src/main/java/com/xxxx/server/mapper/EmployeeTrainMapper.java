package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.EmployeeTrain;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface EmployeeTrainMapper extends BaseMapper<EmployeeTrain> {

    ////获取数据，封装到IPage中
    IPage<EmployeeTrain> getEmployeeTranInfoByPage(Page<EmployeeTrain> page,
                                                   @Param("empName") String empName,
                                                   @Param("deptName") String deptName,
                                                   @Param("startDate")Date startDate,
                                                   @Param("endDate")Date endDate);

}
