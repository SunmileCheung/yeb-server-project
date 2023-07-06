package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.SalaryAdjust;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
public interface ISalaryAdjustService extends IService<SalaryAdjust> {

    RespPageBean searchByPageList(Integer currentPage,Integer pageSize,String empName,String deptName);

    RespBean refreshEmpSalary();

}
