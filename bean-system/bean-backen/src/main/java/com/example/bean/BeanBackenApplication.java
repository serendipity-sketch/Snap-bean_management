package com.example.bean;

import org.mybatis.spring.annotation.MapperScan; // 1. 务必导入这个包
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bean.mapper")
public class BeanBackenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanBackenApplication.class, args);
    }
}
