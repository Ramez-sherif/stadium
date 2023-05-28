package com.example.softwareproject.stadium.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.softwareproject.stadium.controllers.*.add*(..))")
    public void beforeAddTournament(JoinPoint joinPoint) {
        System.out.println("adding " + joinPoint.getSignature() + joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "execution(*comm.example.softwareproject.stadium.controllers.*.add*(..))", returning = "result")
    public void afterGetPostsReturning(JoinPoint joinPoint, Object result) {
        System.out.println("added " + joinPoint.getSignature().getName());
    }

    /*
     * @AfterThrowing(pointcut =
     * "execution(*comm.example.softwareproject.stadium.controllers.*.add*(..))",
     * throwing = "ex")
     * public void afterAddTournamentthrowing(JoinPoint joinPoint, Exception ex) {
     * System.out.println(
     * "exception thrown while adding tournament: " + joinPoint.getSignature() +
     * ",ex =" + ex.getMessage());
     * }
     */
}
