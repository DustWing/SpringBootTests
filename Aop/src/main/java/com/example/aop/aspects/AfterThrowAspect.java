package com.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AfterThrowAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAspect.class);

    @AfterThrowing("execution(* com.example.aop.services..*(..)))")
    public void logAfterAllServiceMethodCall(JoinPoint joinPoint) throws Throwable {

        LOGGER.info("======Exception in of method "
                + joinPoint.getSignature().getName()); // Method Name

        LOGGER.info("======Exception in of method "
                + Arrays.toString(joinPoint.getArgs()));

    }
}
