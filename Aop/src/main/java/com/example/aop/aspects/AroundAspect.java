package com.example.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AroundAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAspect.class);


    @Around("PointcutAspect.requestMapping()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info(
                "=====logAround " +
                        Arrays.toString(joinPoint.getArgs())
        );

        return joinPoint.proceed();
    }
}
