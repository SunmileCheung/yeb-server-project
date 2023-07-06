package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.EmployeeRemoveMapper;
import com.xxxx.server.pojo.EmployeeRemove;
import com.xxxx.server.pojo.RespPageBean;
import com.xxxx.server.service.IEmployeeRemoveService;
import com.xxxx.server.vo.EmployeeRemoveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2022-06-23
 */
@Service
public class EmployeeRemoveServiceImpl extends ServiceImpl<EmployeeRemoveMapper, EmployeeRemove> implements IEmployeeRemoveService {

    @Autowired
    private EmployeeRemoveMapper employeeRemoveMapper;


    //分页查询所有的员工调动信息
    @Override
    public RespPageBean getEmployeeRemoveInfosPage(Integer currentPage, Integer pageSize, String ename) {
        Page<EmployeeRemoveVO> page = new Page<>(currentPage,pageSize);
        IPage<EmployeeRemoveVO> ipage = employeeRemoveMapper.getEmployeeRemoveInfosPage(page,ename);
        RespPageBean respPageBean = new RespPageBean(ipage.getTotal(), ipage.getRecords());
        return respPageBean;
    }
}
