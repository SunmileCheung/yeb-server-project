package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.EmployeeEc;
import com.xxxx.server.pojo.RespPageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface IEmployeeEcService extends IService<EmployeeEc> {

    RespPageBean getEmployeeEcInfos(Integer currentPage,Integer pageSize,String ename,Integer ecType);

    //查询当月所有的奖励惩罚信息
    List<EmployeeEc> getCurrentMonthEmployeeEcInfos();

}
