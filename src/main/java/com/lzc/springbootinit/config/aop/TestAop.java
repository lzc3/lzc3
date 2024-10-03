package com.lzc.springbootinit.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.lzc.springbootinit.config.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面类样例，对于执行顺序order
 * - 目标方法前的通知方法，比如before，数字小的先执行
 * - 目标方法后的通知方法，比如after，数字大的先执行
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class TestAop {

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 通用切入点
     */
    @Pointcut("execution(* com.lzc.springbootinit.service.*.*(..))")
    private void pointCut() {}

    @Around("execution(* com.lzc.springbootinit.servi*.*.*(..))")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable {
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer operateUser = claims.get("id", Integer.class);

        // 目标类名
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        // 目标方法签名
        Signature signature = proceedingJoinPoint.getSignature();
        // 目标方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 目标方法参数
        Object[] args = proceedingJoinPoint.getArgs();

        long begin = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + "执行耗时：{}ms", end - begin);

        String jsonString = JSONObject.toJSONString(proceed);

        return proceed;
    }

    @Before("execution(public void com.lzc.springbootinit.service.impl.EmpServiceImpl.delete(java.lang.Integer)))")
    public void before(JoinPoint joinPoint) {
        // 目标类名
        String name = joinPoint.getTarget().getClass().getName();
        // 目标方法签名
        Signature signature = joinPoint.getSignature();
        // 目标方法名
        String methodName = joinPoint.getSignature().getName();
        // 目标方法参数
        Object[] args = joinPoint.getArgs();
        log.info(">>>>>>>>>aop before>>>>>>>>>");
    }

    @After("pointCut()")
    public void after() {
        log.info(">>>>>>>>>aop after>>>>>>>>>");
    }

    @AfterReturning("@annotation(com.lzc.springbootinit.config.annotation.TestAnnotation)")
    public void afterReturning() {
        log.info(">>>>>>>>>aop afterReturning>>>>>>>>>");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        log.info(">>>>>>>>>aop afterThrowing>>>>>>>>>");
    }

    // 注：顺序 around-before部分 before afterReturning after around-after部分
    // 出现异常时：around-before部分 before afterThrowing after around-after部分(不运行)
}
