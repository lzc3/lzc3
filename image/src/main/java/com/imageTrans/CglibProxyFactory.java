package com.imageTrans;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory {
    static class CglibProxyInterceptor implements MethodInterceptor {

        /**
         * @param obj    代理对象（CGLIB生成的子类实例）
         * @param method 目标方法
         * @param args   方法参数
         * @param proxy  方法代理（用于快速调用父类方法）
         */
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // 前置增强
            System.out.println("[CGLIB代理] 方法调用前: " + method.getName());

            // 调用原始方法（推荐使用invokeSuper，性能更优）
            Object result = proxy.invokeSuper(obj, args);

            // 后置增强
            System.out.println("[CGLIB代理] 方法调用后");
            return result;
        }
    }

    public static Object createProxy(Class<?> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);          // 设置父类
        enhancer.setCallback(new CglibProxyInterceptor()); // 设置拦截器
        return enhancer.create();  // 创建代理对象
    }
}
