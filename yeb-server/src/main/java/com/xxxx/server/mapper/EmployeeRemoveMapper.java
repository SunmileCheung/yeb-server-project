package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.EmployeeRemove;
import com.xxxx.server.vo.EmployeeRemoveVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface EmployeeRemoveMapper extends BaseMapper<EmployeeRemove> {

    IPage<EmployeeRemoveVO> getEmployeeRemoveInfosPage(Page<EmployeeRemoveVO> page, @Param("ename") String ename);
}
