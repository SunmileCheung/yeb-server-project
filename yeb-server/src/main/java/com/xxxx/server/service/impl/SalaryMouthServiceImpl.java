package com.xxxx.server.service.impl;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.SalaryMouth;
import com.xxxx.server.mapper.SalaryMouthMapper;
import com.xxxx.server.service.IEmployeeService;
import com.xxxx.server.service.ISalaryMouthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2023-07-03
 */
@Service
public class SalaryMouthServiceImpl extends ServiceImpl<SalaryMouthMapper, SalaryMouth> implements ISalaryMouthService {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISalaryMouthService salaryMouthService;
    /**
     * 添加处理
     * @param salaryMouth
     * @return
     */
    @Override
    public RespBean addSalaryMouth(SalaryMouth salaryMouth) {
        //判断是否有这个员工
        if (employeeService.selectByName(salaryMouth.getName())){
            return RespBean.error("咱公司都没这个员工，你瞎写什么");
        }
        salaryMouth.setCreateDate(LocalDateTime.now());
        if (salaryMouthService.save(salaryMouth)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public RespBean updateSalaryMouth(SalaryMouth salaryMouth) {
        //判断是否有这个员工
        if (employeeService.selectByName(salaryMouth.getName())){
            return RespBean.error("咱公司都没这个员工，你瞎写什么");
        }
        if (salaryMouthService.updateById(salaryMouth)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
