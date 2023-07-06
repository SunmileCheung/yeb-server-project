package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author C
 * @Description
 * @Date create in 2023/7/6 11:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Employee对象", description="")
public class SalarySearch {
    @ApiModelProperty(value = "补助")
    private Integer subsidy;

    @ApiModelProperty(value = "基金")
    private Integer fund;

    @ApiModelProperty(value = "差值")
    private Integer dif;

    @ApiModelProperty(value = "员工")
    private Employee employee;

    @ApiModelProperty(value = "部门")
    private Department department;

    @ApiModelProperty(value = "薪资涨幅")
    private SalaryAdjust salaryAdjust;

}
