package com.lzc.module.start;

import com.lzc.module.start.service.StartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ctx.registerShutdownHook();

        StartService startService = (StartService) ctx.getBean("startService");
        startService.start();

        // 别名
        StartService alias = (StartService) ctx.getBean("alias2");
        alias.start();

        // 工厂
        StartService factoryStartService = (StartService) ctx.getBean("factoryStartService");
        factoryStartService.start();

        StartService factoryStartServiceNoStatic = (StartService) ctx.getBean("factoryStartServiceNoStatic");
        factoryStartServiceNoStatic.start();

        StartService factoryStartServiceBean = (StartService) ctx.getBean("factoryStartServiceBean");
        factoryStartServiceBean.start();


    }

}
