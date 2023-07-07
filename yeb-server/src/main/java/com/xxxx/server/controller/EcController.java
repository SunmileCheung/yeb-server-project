package com.xxxx.server.controller;

import com.xxxx.server.pojo.Ec;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IEcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tibashiki
 * @since 2023-07-04
 */
@RestController
@RequestMapping("/system/basic/ec")
public class EcController {
    @Autowired
    private IEcService ecService;

    @ApiOperation(value = "获取所有奖惩")
    @GetMapping("/")
    public List<Ec> getAllEc(){
        return ecService.list();
    }

    @ApiOperation(value = "添加奖惩")
    @PostMapping("/")
    public RespBean addEc(@RequestBody Ec ec){
        ec.setEcDate(LocalDateTime.now());
        if (ecService.save(ec)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
   /* @ApiOperation(value = "添加奖惩")
    @PostMapping("/")
    public RespBean addRc1(@RequestBody Ec ec){
        ec.setEcDate(LocalDateTime.now());
        if(ecService.save(ec)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
*/
    @ApiOperation(value = "更新奖惩")
    @PutMapping("/")
    public RespBean updateEc(@RequestBody Ec ec){
        if (ecService.updateById(ec)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除奖惩")
    @DeleteMapping("/{id}")
    public RespBean deleteEc(@PathVariable Integer id){
        if (ecService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除奖惩")
    @DeleteMapping("/")
    public RespBean deleteEcByIds(Integer[] ids){
        if (ecService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
