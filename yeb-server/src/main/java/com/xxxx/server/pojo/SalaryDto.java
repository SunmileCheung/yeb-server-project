package com.xxxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sunmile_Cheung 张亮
 * @Date 2023/7/5 21:56
 * @Description 应发工资
 * @Modified by Sunmile
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {

    private Integer id;
    private Integer eid;
    private String ename;
    private Integer allSalary;
}
