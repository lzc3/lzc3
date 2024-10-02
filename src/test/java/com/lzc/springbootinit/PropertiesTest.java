package com.lzc.springbootinit;


import com.lzc.springbootinit.config.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
public class PropertiesTest {

    @Autowired
    TestProperties testProperties;

    @Test
    public void test() {
        String propertiesValue = testProperties.getPropertiesValue();
        List<String> propertiesValueList = testProperties.getValueList();
        Set<String> propertiesValueSet = testProperties.getValueSet();

        log.info("配置文件中的值为：{}, \n{}, \n{}", propertiesValue, propertiesValueList, propertiesValueSet);
    }

}
