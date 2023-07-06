package com.xxxx.server.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.SalaryMouth;
import com.xxxx.server.service.ISalaryMouthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2023-07-03
 */
@RestController
@RequestMapping("/system/salary/month")
public class SalaryMouthController {

    @Autowired
    private ISalaryMouthService salaryMouthService;

    @ApiOperation(value = "查询所有处理")
    @GetMapping("/")
    public List<SalaryMouth> getAllSalaryMouth(){
        return salaryMouthService.list();
    }

    @ApiOperation(value = "增加处理")
    @PostMapping("/")
    public RespBean addSalaryMouth(@RequestBody SalaryMouth salaryMouth){
        return salaryMouthService.addSalaryMouth(salaryMouth);
    }

    @ApiOperation(value = "更新处理")
    @PutMapping("/")
    public RespBean updateSalaryMouth(@RequestBody SalaryMouth salaryMouth){
        return salaryMouthService.updateSalaryMouth(salaryMouth);
    }

    @ApiOperation(value = "删除处理")
    @DeleteMapping("/{id}")
    public RespBean deleteSalaryMouth(@PathVariable Integer id){
        if (salaryMouthService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deleteSalaryMouths(Integer[] ids){
        if (salaryMouthService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
