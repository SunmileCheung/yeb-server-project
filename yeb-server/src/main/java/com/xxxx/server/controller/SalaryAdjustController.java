package com.xxxx.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IEmployeeEcService;
import com.xxxx.server.service.ISalaryAdjustService;
import com.xxxx.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/salary-adjust")
public class SalaryAdjustController {

    @Autowired
    private ISalaryAdjustService salaryAdjustService;

    @Autowired
    private IEmployeeEcService employeeEcService;

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation("分页获取")
    @GetMapping("/")
    public RespPageBean getSalaryAdjust(Integer currentPage,Integer pageSize,String empName,String deptName){
        RespPageBean result = salaryAdjustService.searchByPageList(currentPage,pageSize,empName,deptName);
        return result;
    }


    @ApiOperation("依据应发工资和奖励处罚分核算实发工资")
    @GetMapping("/refresh")
    public RespBean refreshEmpsSalary(){
        try {
            return salaryAdjustService.refreshEmpSalary();
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("数据更新失败");
        }
    }

}
