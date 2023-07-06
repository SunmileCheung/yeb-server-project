package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.EmployeeRemove;
import com.xxxx.server.pojo.RespPageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface IEmployeeRemoveService extends IService<EmployeeRemove> {

    //分页获取所有的员工调动信息
    RespPageBean getEmployeeRemoveInfosPage(Integer currentPage, Integer pageSize, String ename);

}
