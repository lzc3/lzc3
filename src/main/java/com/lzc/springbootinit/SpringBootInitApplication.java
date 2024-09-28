package com.lzc.springbootinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lzc.springbootinit")
public class SpringBootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInitApplication.class, args);
    }

}
