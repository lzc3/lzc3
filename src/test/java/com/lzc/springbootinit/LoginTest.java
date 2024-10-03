package com.lzc.springbootinit;

import com.lzc.springbootinit.controller.LoginController;
import com.lzc.springbootinit.mybatis.pojo.Emp;
import com.lzc.springbootinit.service.impl.EmpServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class LoginTest {

    @Autowired
    EmpServiceImpl empService;

    @Test
    public void test() {
        Emp build = Emp.builder()
                .username("jinyong")
                .password("123456")
                .build();
        Emp login = empService.login(build);
        System.out.println(login);
    }
}
