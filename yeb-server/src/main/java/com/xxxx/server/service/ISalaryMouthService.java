package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.SalaryMouth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2023-07-03
 */
public interface ISalaryMouthService extends IService<SalaryMouth> {

    /**
     * 添加处理
     * @param salaryMouth
     * @return
     */
    RespBean addSalaryMouth(SalaryMouth salaryMouth);

    /**
     * 更新处理
     * @param salaryMouth
     * @return
     */
    RespBean updateSalaryMouth(SalaryMouth salaryMouth);
}
