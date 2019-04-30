package com.lemon.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;



import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    /*
    * AspectJ使用org.aspectj.lang.JoinPoint接口表示目标类连接点对象，
    * 如果是环绕增强时，使用org.aspectj.lang.ProceedingJoinPoint表示连接点对象，该类是JoinPoint的子接口。
    * 任何一个增强方法都可以将第一个入参声明
     为JoinPoint访问到连接点上下文的信息。我们先来了解一下这两个接口的主要方法：
    1)JoinPoint
        java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
        Signature getSignature() ：获取连接点的方法签名对象；
       java.lang.Object getTarget() ：获取连接点所在的目标对象；
       java.lang.Object getThis() ：获取代理对象本身；
    2)ProceedingJoinPoint
       ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法：
      java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法；
      java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：
      通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。 */
    @Around("execution(* com.lemon.security.controller.UserController.*(..))")
    public Object handleTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("time aspect is start.+++++++++++++++++");
        for (Object object:proceedingJoinPoint.getArgs()) {
            System.out.println(object);
        }
        System.out.println(proceedingJoinPoint.getSignature());
        System.out.println(proceedingJoinPoint.getTarget());
        System.out.println(proceedingJoinPoint.getThis());
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("time aspect ："+(System.currentTimeMillis()-startTime));
        System.out.println("time aspect finish.+++++++++++++++++");
        return obj;
    }
}
