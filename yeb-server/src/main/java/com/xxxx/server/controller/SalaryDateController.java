package com.xxxx.server.controller;


import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.pojo.SalaryDate;
import com.xxxx.server.service.ISalaryDateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2023-07-04
 */
@RestController
@RequestMapping("/sal/table/")
public class SalaryDateController {

    @Autowired
    private ISalaryDateService salaryDateService;

    @ApiOperation(value = "查询工资管理")
    @GetMapping("/")
    public RespPageBean getAllSalaryDate(@RequestParam(defaultValue = "1") Integer currentPage,
                                         @RequestParam(defaultValue = "10") Integer size){
        return salaryDateService.getAllSalaryDate(currentPage,size);
    }

    @ApiOperation(value = "修改工资")
    @PutMapping("/")
    public RespBean updateSalaryDate(@RequestBody SalaryDate salaryDate){
        return salaryDateService.updateSalaryDate(salaryDate);
    }

    @ApiOperation(value = "添加工资")
    @PostMapping("/")
    @Transactional
    public RespBean addSalaryDate(@RequestBody SalaryDate salaryDate){
        return salaryDateService.addSalaryDate(salaryDate);
    }

    @ApiOperation(value = "删除工资")
    @DeleteMapping("/{id}")
    @Transactional
    public RespBean deleteSalaryDate(@PathVariable Integer id){
        if (salaryDateService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
