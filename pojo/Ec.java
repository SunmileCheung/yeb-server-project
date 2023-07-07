package com.xxxx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tibashiki
 * @since 2023-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ec")
@ApiModel(value="Ec对象", description="")
public class Ec implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "奖惩类别")
    @Excel(name = "奖惩类别")
    @NonNull
    private Integer ecType;

    @ApiModelProperty(value = "创建/更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDateTime ecDate;

    @ApiModelProperty(value = "奖惩名称")
    @Excel(name = "奖惩名称")
    @NonNull
    private String ecTypeName;

    @ApiModelProperty(value = "绩效变更")
    @Excel(name = "绩效变更")
    private Integer ecPoint;
}
