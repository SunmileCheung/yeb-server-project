package com.xxxx.server.controller;

import com.xxxx.server.pojo.QuerySalaryDate;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeService;
import com.xxxx.server.service.ISalaryDateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author C
 * @Description
 * @Date create in 2023/7/4 21:58
 */
@RestController
@RequestMapping("/sal/search")
public class SalarySearchController {
    @Autowired
    private ISalaryDateService salaryDateService;

    @Autowired
    private IEmployeeService employeeService;
    /*@ApiOperation(value = "多条件查询")
    @GetMapping("/")
    public RespPageBean getSalaryDate1(@RequestParam(defaultValue = "1") Integer currentPage,
                                      @RequestParam(defaultValue = "10") Integer size, String name , String dname){
        return salaryDateService.getSalaryDate1(currentPage,size,name ,dname);
    }*/

    @ApiOperation(value = "多条件查询")
    @GetMapping("/")
    public RespPageBean getSalaryDate(@RequestParam(defaultValue = "1") Integer currentPage,
                                       @RequestParam(defaultValue = "10") Integer size, String ename , String dname){
        return employeeService.getSalarySearch(currentPage,size,ename ,dname);
    }
}
