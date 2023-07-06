package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.SalaryAdjustMapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.IEmployeeEcService;
import com.xxxx.server.service.ISalaryAdjustService;
import com.xxxx.server.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@Service
public class SalaryAdjustServiceImpl extends ServiceImpl<SalaryAdjustMapper, SalaryAdjust> implements ISalaryAdjustService {

    @Autowired
    private SalaryAdjustMapper salaryAdjustMapper;
    @Autowired
    private ISalaryAdjustService salaryAdjustService;

    @Autowired
    private IEmployeeEcService employeeEcService;

    @Autowired
    private ISalaryService salaryService;
    @Override
    public RespPageBean searchByPageList(@RequestParam("currentPage'") Integer currentPage,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam("empName") String empName,
                                         @RequestParam("deptName") String deptName) {
        Page<SalaryAdjust> page = new Page<>(currentPage, pageSize);
        IPage<SalaryAdjust> ipage = salaryAdjustMapper.searchByPageList(page,empName,deptName);
        RespPageBean respPageBean = new RespPageBean(ipage.getTotal(), ipage.getRecords());
        return respPageBean;
    }

    @Transactional
    public RespBean refreshEmpSalary(){
        //获取账套中设定的应发工资
        List<SalaryDto> salaryOfAllEmp = salaryService.getAllSalaryOfAllEmp();
        //中间操作：把salary表中的allSalary填充
        for (SalaryDto salaryDto : salaryOfAllEmp) {
            Salary salary = new Salary();
            //赋值salary对象
            salary.setAllSalary(salaryDto.getAllSalary());
            salary.setEid(salaryDto.getEid());
            salary.setId(salaryDto.getId());
            System.out.println("数据写入salary表");
            //数据写入salary表
            boolean eid = salaryService.updateById(salary);
            if (eid) System.out.println("数据写入salary表成功");
        }
        //获取当月的奖惩信息
        List<EmployeeEc> currentMonthEmployeeEcInfos = employeeEcService.getCurrentMonthEmployeeEcInfos();
        Map<Integer, List<EmployeeEc>> listMap = currentMonthEmployeeEcInfos.stream().collect(Collectors.groupingBy(EmployeeEc::getEid));
        //奖惩表按照eid进行分组 并遍历
        for (Map.Entry<Integer, List<EmployeeEc>> listEntry : listMap.entrySet()) {
            System.out.println("进入map的循环");
            //拿到eid对应的奖惩信息列表
            List<EmployeeEc> employeeEcs = listEntry.getValue();
            //对所有的员工薪资进行遍历
            for (SalaryDto salaryDto : salaryOfAllEmp) {
               /* Salary salary = new Salary();
                //赋值salary对象
                salary.setAllSalary(salaryDto.getAllSalary());
                salary.setEid(salaryDto.getEid());
                salary.setId(salaryDto.getId());
                System.out.println("数据写入salary表");
                //数据写入salary表
                boolean eid = salaryService.updateById(salary);
                if (eid)System.out.println("数据写入salary表成功");*/
                //如果奖惩表对应的eid与薪资表的sid相等
                if (listEntry.getKey().equals(salaryDto.getEid())){
                    //计算出奖惩结果
                    Integer sum = employeeEcs.stream().map(EmployeeEc::getEcPoint).reduce(Integer::sum).get();
                    System.out.println("计算的分值结果为"+sum);
                    SalaryAdjust salaryAdjust = new SalaryAdjust();
                    //对奖惩对象进行赋值
                    salaryAdjust.setEid(salaryDto.getEid());
                    salaryAdjust.setBeforeSalary(salaryDto.getAllSalary());
                    salaryAdjust.setAfterSalary(salaryDto.getAllSalary() * (new Integer(100)+sum)/100);
                    salaryAdjust.setAsDate(LocalDate.now());
                    System.out.println("数据写入adjust表");
                    //最终数据写入adjust表
                    boolean eid1 = salaryAdjustService.saveOrUpdate(salaryAdjust, new UpdateWrapper<SalaryAdjust>().eq("eid", salaryAdjust.getEid()));
                    if (eid1)System.out.println("数据写入adjust表成功");
                }

            }
        }
        return RespBean.success("数据更新成功");
    }
}
