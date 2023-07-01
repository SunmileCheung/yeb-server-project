package com.xxxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author C
 * @Description
 * @Date create in 2023/6/30 16:10
 */
@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
