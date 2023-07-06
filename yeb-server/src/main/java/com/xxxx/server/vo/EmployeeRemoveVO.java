package com.xxxx.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Sunmile_Cheung 张亮
 * @Date 2023/7/6 15:44
 * @Description
 * @Modified by Sunmile
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRemoveVO {

    //员工id
    private Integer eid;
    //员工姓名
    private String ename;
    //员工所属部门
    private String dname;
    //员工职位
    private String pname;
    //账套名称
    private String salaryName;
    //账套id
    private Integer sid;
}
