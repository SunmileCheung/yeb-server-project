package com.xxxx.server.service;

import com.xxxx.server.pojo.QuerySalaryDate;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.SalaryDate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2023-07-04
 */
public interface ISalaryDateService extends IService<SalaryDate> {

    /**
     * 查询工资管理
     * @return
     */
    RespPageBean getAllSalaryDate(Integer currentPage, Integer size);

    /**
     * 添加工资
     * @param salaryDate
     * @return
     */
    RespBean addSalaryDate(SalaryDate salaryDate);

    /**
     * 多条件查询 旧
     * @param currentPage
     * @param size
     * @param name
     * @param dname
     * @return
     */
    RespPageBean getSalaryDate1(Integer currentPage, Integer size, String name, String dname);



    /**
     * 修改工资
     * @param salaryDate
     */
    RespBean updateSalaryDate(SalaryDate salaryDate);
}
