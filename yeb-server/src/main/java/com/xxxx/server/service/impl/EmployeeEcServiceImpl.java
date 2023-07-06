package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.EmployeeEcMapper;
import com.xxxx.server.pojo.EmployeeEc;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

    @Autowired
    private EmployeeEcMapper employeeEcMapper;

    @Override
    public RespPageBean getEmployeeEcInfos(Integer currentPage,Integer pageSize,String ename,Integer ecType) {
        Page<EmployeeEc> page = new Page<EmployeeEc>(currentPage, pageSize);
        IPage<EmployeeEc> employeeEcInfos = employeeEcMapper.getEmployeeEcInfos(page,ename,ecType);
        RespPageBean respPageBean = new RespPageBean(employeeEcInfos.getTotal(), employeeEcInfos.getRecords());
        return respPageBean;
    }

    public List<EmployeeEc> getCurrentMonthEmployeeEcInfos(){
        return employeeEcMapper.getCurrentMonthEmployeeEcInfos();
    }
}
