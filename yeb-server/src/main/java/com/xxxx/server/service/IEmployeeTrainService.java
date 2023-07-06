package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.EmployeeTrain;
import com.xxxx.server.pojo.RespPageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface IEmployeeTrainService extends IService<EmployeeTrain> {

    //分页获取所有的培训相关信息
    RespPageBean getEmployeeTranInfoByPage(Integer currentPage, Integer pageSize, String empName, String deptName,String startDdate,String endDate);
}
