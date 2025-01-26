package com.lzc.springbootinit.config;

import org.lzc.common.ApiUrl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitConfig {

    @PostConstruct
    public void initApiUrl() {
        ApiUrl.apiList.add("init add item");
    }
}
