package com.xxxx.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author C
 * @Description
 * @Date create in 2023/7/1 17:28
 */
@RestController
public class HelloController {

    @GetMapping("/employee/basic/hello")
    public String hello(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello1(){
        return "/employee/advanced/hello";
    }
}
