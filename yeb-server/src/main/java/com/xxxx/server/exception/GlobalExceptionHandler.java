package com.xxxx.server.exception;

import com.xxxx.server.pojo.RespBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author C
 * @Description 全局异常 ControllerAdvice当控制器发生异常时，且符合类定义的拦截异常，会被拦截
 * @Date create in 2023/7/3 8:32
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)//定义拦截的异常类
    public RespBean muSQLException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败！");
    }
}
