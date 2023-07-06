package com.xxxx.server.controller;


import com.xxxx.server.pojo.EmployeeTrain;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeTrainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/employee-train")
public class EmployeeTrainController {

    @Autowired
    private IEmployeeTrainService employeeTrainService;


    @ApiOperation("查找所有的培训计划数据")
    @GetMapping("/")
    public RespPageBean getEmployeeTranInfoByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                                  @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                  @RequestParam(value = "empName",required = false)String empName,
                                                  @RequestParam(value = "deptName",required = false)String deptName,
                                                  @RequestParam(value = "startDate",required = false)String startDate,
                                                  @RequestParam(value = "endDate",required = false)String endDate){
        //分页获取所有的培训相关信息
           return employeeTrainService.getEmployeeTranInfoByPage(currentPage,pageSize,empName,deptName,startDate,endDate);
    }

    @PostMapping("/")
    public RespBean updateEmployeeTrainInfo(@RequestBody EmployeeTrain employeeTrain){
        if (employeeTrainService.saveOrUpdate(employeeTrain)) {
            return RespBean.success("更新或添加成功");
        }
        return RespBean.error("更新或添加失败");
    }

}
