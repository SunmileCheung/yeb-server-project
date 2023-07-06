package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.*;
import com.xxxx.server.mapper.SalaryDateMapper;
import com.xxxx.server.service.IEmployeeService;
import com.xxxx.server.service.ISalaryDateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2023-07-04
 */
@Service
public class SalaryDateServiceImpl extends ServiceImpl<SalaryDateMapper, SalaryDate> implements ISalaryDateService {

    @Autowired
    private SalaryDateMapper salaryDateMapper;
    @Autowired
    private ISalaryDateService salaryDateService;
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 获取所有薪资
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getAllSalaryDate(Integer currentPage, Integer size) {
        Page<SalaryDate> page = new Page<>(currentPage, size);
        IPage<SalaryDate> salaryDate = salaryDateMapper.getAllSalaryDate(page);
        return new RespPageBean(salaryDate.getTotal(),salaryDate.getRecords());
    }

    /**
     * 添加薪资
     * @param salaryDate
     * @return
     */
    @Override
    public RespBean addSalaryDate(SalaryDate salaryDate) {
        //判断是否有这个员工
        if (employeeService.selectByName(salaryDate.getName())){
            return RespBean.error("咱公司都没这个员工，你瞎写什么");
        }
        if (salaryDateService.save(salaryDate)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 多条件查询 旧
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getSalaryDate1(Integer currentPage, Integer size, String name, String dname) {
        Page<SalaryDate> page = new Page<>(currentPage, size);
        IPage<SalaryDate> salaryDate = salaryDateMapper.getSalaryDate1(page,name,dname);
        return new RespPageBean(salaryDate.getTotal(),salaryDate.getRecords());
    }




    /**
     * 修改工资
     * @param salaryDate
     * @return
     */
    @Override
    public RespBean updateSalaryDate(SalaryDate salaryDate) {
        if (StringUtils.isBlank(salaryDate.getName())){
            return RespBean.error("员工姓名不能为空");
        }else if (StringUtils.isBlank(salaryDate.getSal())){
            return RespBean.error("应发工资不能为空");
        }else if (StringUtils.isBlank(salaryDate.getAward())){
            return RespBean.error("奖励不能为空");
        }else if (StringUtils.isBlank(salaryDate.getDate().toString())){
            return RespBean.error("日期工资不能为空");
        }else if (StringUtils.isBlank(salaryDate.getPunishment())){
            return RespBean.error("惩罚不能为空");
        }
        if (salaryDateService.updateById(salaryDate)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
