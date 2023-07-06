package com.xxxx.server.controller;


import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeRemoveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/employee-remove")
public class EmployeeRemoveController {

    @Autowired
    private IEmployeeRemoveService employeeRemoveService;

    @ApiOperation("分页获取所有的员工调动信息")
    @GetMapping("/")
    public RespPageBean getEmployeeRemoveInfosPage(@RequestParam("currentPage")Integer currentPage,
                                                   @RequestParam("pageSize")Integer pageSize,
                                                   @RequestParam(value = "ename",required = false)String ename){
        //分页获取所有的员工调动信息
        return employeeRemoveService.getEmployeeRemoveInfosPage(currentPage,pageSize,ename);
    }
}
