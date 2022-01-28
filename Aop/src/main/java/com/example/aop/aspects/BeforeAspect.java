package com.example.aop.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAspect.class);

    @Before("execution(* com.example.aop.services..*(..)))")
    public void logBeforeAllServiceMethodCall(JoinPoint joinPoint) throws Throwable {

        LOGGER.info("======Started execution of method "
                + joinPoint.getSignature().getName()); // Method Name
    }
}
