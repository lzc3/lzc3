package com.lzc.test;

import com.lzc.core.ApplicationContext;
import com.lzc.test.service.AppConfig;
import com.lzc.test.service.OrderService;
import com.lzc.test.service.UserInterface;
import com.lzc.test.service.UserService;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
        userService.test();
    }
}
