package com.lzc.module.jdbc;

import com.lzc.module.jdbc.pojo.User;
import com.lzc.module.jdbc.service.JdbcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    JdbcService jdbcService;


    @Test
    public void testSelect() {
        User select = jdbcService.select("1");
        System.out.println(select);
    }


}
