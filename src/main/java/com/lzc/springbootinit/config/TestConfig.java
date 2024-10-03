package com.lzc.springbootinit.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    /**
     * 将方法返回值交给IOC容器管理，成为IOC容器的对象
     * 默认名称为方法名
     */
    @Bean
    public JSONObject getOtherBean() {
        return new JSONObject();
    }
}
