package com.example.aop.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AfterAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAspect.class);

    @After("execution(* com.example.aop.services..*(..)))")
    public void logAfterAllServiceMethodCall(JoinPoint joinPoint) throws Throwable {

        LOGGER.info("======Completed execution of method "
                + joinPoint.getSignature().getName() + Arrays.toString(joinPoint.getArgs())); // Method Name

    }
}
