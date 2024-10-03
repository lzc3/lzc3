package com.lzc.springbootinit;

import com.lzc.springbootinit.service.DeptService;
import com.lzc.springbootinit.service.impl.DeptServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeanTest {

    @Autowired
    private ApplicationContext applicationContext;




    @Test
    public void getBean() {

        // 根据name获取bean
        DeptService beanByName = (DeptService) applicationContext.getBean("deptService");
        // 根据类型获取bean
        DeptServiceImpl beanByClassType = applicationContext.getBean(DeptServiceImpl.class);
        // 根据name获取bean
        DeptService beanByNameAndClassType = applicationContext.getBean("deptService", DeptService.class);

        System.out.println(beanByName);
        System.out.println(beanByClassType);
        System.out.println(beanByNameAndClassType);
    }

}
