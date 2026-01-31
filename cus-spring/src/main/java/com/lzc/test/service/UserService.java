package com.lzc.test.service;

import com.lzc.core.*;

import java.lang.reflect.Proxy;

import static com.lzc.Constant.PROTOTYPE;

@Component("userService")
@Scope
public class UserService implements UserInterface, BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void test() {
        System.out.println(orderService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet userService 初始化");
    }
}
