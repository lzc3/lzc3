package com.lzc.springbootinit.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Data
@ConfigurationProperties("custom.properties")
public class TestProperties {


//    @Value("${custom.properties.valueList}")
    private List<String> valueList;

//    @Value("${custom.properties.value:lzc3}")
    private String propertiesValue;


//    @Value("${custom.properties.valueSet}")
    private Set<String> valueSet;

}
