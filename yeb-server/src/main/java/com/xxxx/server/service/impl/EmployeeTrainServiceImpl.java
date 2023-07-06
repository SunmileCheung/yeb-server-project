package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.EmployeeTrainMapper;
import com.xxxx.server.pojo.EmployeeTrain;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeTrainService;
import com.xxxx.server.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@Service
public class EmployeeTrainServiceImpl extends ServiceImpl<EmployeeTrainMapper, EmployeeTrain> implements IEmployeeTrainService {

    @Autowired
    private EmployeeTrainMapper employeeTrainMapper;

    @Override
    public RespPageBean getEmployeeTranInfoByPage(Integer currentPage, Integer pageSize, String empName, String deptName,String startDate,String endDate) {
        //构建page对象
        Page<EmployeeTrain> page = new Page<>(currentPage, pageSize);
        //将字符串的date进行解析和转换
        Date startDate1 = null;
        Date endDate1 = null;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
            startDate1 = DateUtils.parseStringToDate(startDate);
            endDate1 = DateUtils.parseStringToDate(endDate);

        }

        //获取数据，封装到IPage中
        IPage<EmployeeTrain> empTrainList = employeeTrainMapper.getEmployeeTranInfoByPage(page,empName,deptName,startDate1,endDate1);
        //封装RespPageBean
        RespPageBean respPageBean = new RespPageBean(empTrainList.getTotal(), empTrainList.getRecords());
        return respPageBean;
    }
}
