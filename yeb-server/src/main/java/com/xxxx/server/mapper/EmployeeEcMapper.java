package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.EmployeeEc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface EmployeeEcMapper extends BaseMapper<EmployeeEc> {

    IPage<EmployeeEc> getEmployeeEcInfos(Page<EmployeeEc> page, @Param("ename") String ename,@Param("ecType") Integer ecType);

    List<EmployeeEc> getCurrentMonthEmployeeEcInfos();
}
