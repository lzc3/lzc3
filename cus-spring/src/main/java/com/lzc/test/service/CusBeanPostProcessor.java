package com.lzc.test.service;

import com.lzc.core.BeanPostProcessor;
import com.lzc.core.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class CusBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if ("userService".equals(beanName)) {
            System.out.println(this.getClass().getSimpleName() + " userService 初始化前");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if ("userService".equals(beanName)) {
            return Proxy.newProxyInstance(CusBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("===== CusBeanPostProcessor " + "代理的切面逻辑");
                    Object invoke = method.invoke(bean, args);
                    System.out.println("===== CusBeanPostProcessor 代理的切面逻辑结束");
                    return invoke;
                }
            });
        }
        return bean;
    }
}
