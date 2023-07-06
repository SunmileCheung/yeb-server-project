package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

/**
 * @Author C
 * @Description
 * @Date create in 2023/7/5 17:09
 */
public interface ISalarySobCfgService {
    //更新工资账套
    RespBean updateEmployeeSalary(Integer eid, Integer sid);
}
