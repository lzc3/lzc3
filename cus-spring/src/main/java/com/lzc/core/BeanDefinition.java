package com.lzc.core;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BeanDefinition {

    private Class type;
    private String scope;

}
