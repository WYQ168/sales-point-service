package com.sales.common.core.aspect;

import com.sales.common.core.annotation.CreateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 基于 Spring Aop 的注解鉴权
 *
 * @author kong
 */
@Aspect
@Component
public class CommonDaoAspect2 {
    /**
     * 构建
     */
    public CommonDaoAspect2() {
    }

    /**
     * 定义AOP签名 (切入所有使用鉴权注解的方法)
     */
    public static final String POINTCUT_DAO = " @annotation(com.sales.common.core.annotation.CreateTime) || "
            + "@annotation(com.sales.common.core.annotation.UpdateTime) || "
            + "@annotation(com.sales.common.core.annotation.CreateBy)";

    /**
     * 声明AOP签名
     */
    @Pointcut(POINTCUT_DAO)
    public void pointcut2() {
    }

    /**
     * 环绕切入
     *
     * @param joinPoint 切面对象
     * @return 底层方法执行后的返回值
     * @throws Throwable 底层方法抛出的异常
     */
    @Around("pointcut2()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解鉴权
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * 对一个Method对象进行注解检查
     */
    public void checkMethodAnnotation(Method method) {
        // 校验 @RequiresLogin 注解
        CreateTime createTime = method.getAnnotation(CreateTime.class);
        if (createTime != null) {
            System.out.println("日期........");
        }

    }
}
