package com.lzc;

import com.lzc.module.start.service.StartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        StartService startService = (StartService) ctx.getBean("startService");
        startService.start();

        StartService alias = (StartService) ctx.getBean("alias2");
        alias.start();

    }

}
