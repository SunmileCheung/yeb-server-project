package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.SalaryAdjust;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface SalaryAdjustMapper extends BaseMapper<SalaryAdjust> {

    IPage<SalaryAdjust> searchByPageList(Page<SalaryAdjust> page, @Param("empName") String empName,@Param("deptName") String deptName);
}
