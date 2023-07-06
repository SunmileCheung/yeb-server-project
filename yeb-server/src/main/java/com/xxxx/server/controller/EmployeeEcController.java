package com.xxxx.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxxx.server.pojo.EmployeeEc;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeEcService;
import com.xxxx.server.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@Api("对员工奖惩信息的操作")
@RestController
@RequestMapping("/employee-ec")
public class EmployeeEcController {
    @Autowired
    private IEmployeeEcService employeeEcService;
    @ApiOperation("分页查询所有的员工奖惩记录")
    @GetMapping("/page")
    public RespPageBean getEmployeeEcInfos(@Param("currentPage")Integer currentPage,
                                           @Param("pageSize")Integer pageSize,
                                           @Param("ename")String ename,
                                           @Param("ecType")Integer ecType){
       return employeeEcService.getEmployeeEcInfos(currentPage,pageSize,ename,ecType);
    }

    @ApiOperation("保存奖惩信息")
    @PostMapping("/save")
    public RespBean saveRewardAndPunishment(@RequestBody EmployeeEc employeeEc){
        employeeEc.setEcDate(LocalDate.now());
        if (employeeEcService.save(employeeEc)) {
            return RespBean.success("奖惩信息保存成功");
        }
        return RespBean.error("奖惩信息保存失败");
    }

    @ApiOperation("删除奖惩信息")
    @DeleteMapping("/delete")
    public RespBean deleteRewardAndPunishment(@RequestParam("id") Integer id){
        if (employeeEcService.removeById(id)){
            return RespBean.success("奖惩信息删除成功");
        }
        return RespBean.error("奖惩信息删除失败");
    }

    @ApiOperation("更新奖惩分")
    @PutMapping("/update")
    public RespBean updateRewardAndPunishment(@RequestBody EmployeeEc employeeEc){
        if (employeeEcService.updateById(employeeEc)){
            return RespBean.success("更新奖惩分成功!");
        }
        return RespBean.error("更新奖惩分失败");
    }
}
