package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Salary;
import com.xxxx.server.mapper.SalaryMapper;
import com.xxxx.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Autowired
    private ISalaryService salaryService;

    /**
     * 更新工资账套
     * @param salary
     * @return
     */
    @Override
    public RespBean updateSalaries(Salary salary) {
        if (StringUtils.isEmpty(salary.getName())){
            return RespBean.error("账套名称不能为空");
        }else if (StringUtils.isEmpty(salary.getBasicSalary())){
            return RespBean.error("基本工资不能为空");
        }else if (StringUtils.isEmpty(salary.getTrafficSalary())){
            return RespBean.error("交通补助不能为空");
        }else if (StringUtils.isEmpty(salary.getLunchSalary())){
            return RespBean.error("午餐补助不能为空");
        }else if (StringUtils.isEmpty(salary.getBonus())){
            return RespBean.error("奖金不能为空");
        }else if (StringUtils.isEmpty(salary.getPensionPer())){
            return RespBean.error("养老金比率不能为空");
        }else if (StringUtils.isEmpty(salary.getPensionBase())){
            return RespBean.error("养老金基数不能为空");
        }else if (StringUtils.isEmpty(salary.getMedicalPer())){
            return RespBean.error("医疗保险比率不能为空");
        }else if (StringUtils.isEmpty(salary.getMedicalBase())){
            return RespBean.error("医疗保险基数不能为空");
        }else if (StringUtils.isEmpty(salary.getAccumulationFundPer())){
            return RespBean.error("公积金比率不能为空");
        }else if (StringUtils.isEmpty(salary.getAccumulationFundBase())){
            return RespBean.error("公积金基数不能为空");
        }
        if (salaryService.updateById(salary)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 添加工资账套
     * @param salary
     * @return
     */
    @Override
    public RespBean addSalaries(Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
         if (StringUtils.isEmpty(salary.getName())){
            return RespBean.error("账套名称不能为空");
        }else if (StringUtils.isEmpty(salary.getBasicSalary())){
            return RespBean.error("基本工资不能为空");
        }else if (StringUtils.isEmpty(salary.getTrafficSalary())){
            return RespBean.error("交通补助不能为空");
        }else if (StringUtils.isEmpty(salary.getLunchSalary())){
            return RespBean.error("午餐补助不能为空");
        }else if (StringUtils.isEmpty(salary.getBonus())){
            return RespBean.error("奖金不能为空");
        }else if (StringUtils.isEmpty(salary.getPensionPer())){
            return RespBean.error("养老金比率不能为空");
        }else if (StringUtils.isEmpty(salary.getPensionBase())){
            return RespBean.error("养老金基数不能为空");
        }else if (StringUtils.isEmpty(salary.getMedicalPer())){
             return RespBean.error("医疗保险比率不能为空");
        }else if (StringUtils.isEmpty(salary.getMedicalBase())){
             return RespBean.error("医疗保险基数不能为空");
        }else if (StringUtils.isEmpty(salary.getAccumulationFundPer())){
             return RespBean.error("公积金比率不能为空");
        }else if (StringUtils.isEmpty(salary.getAccumulationFundBase())){
             return RespBean.error("公积金基数不能为空");
        }
        if (salaryService.save(salary)){
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除工资账套
     * @param id
     * @return
     */
    @Override
    public RespBean deleteSalaries(Integer id) {
        if (salaryService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
