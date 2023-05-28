package com.example.softwareproject.stadium.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class ExceptionAspect {
    @Around("within(com.example.softwareproject.stadium.controllers.*)")
    public Object timeTracker(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            return result;

        } catch (Throwable e) {
            Map map = new HashMap<>();
            map.put("code", 404);
            map.put("message", "Oops..");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

        }

    }

}
