package com.example.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class ApiLog {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLog.class);

    private static final String UNKNOWN = "unknown";

    //all classes that end with Controller in their name
    @Pointcut("execution(public * com.example.aop.controllers.*Controller.*(..))")
    public void log() {

    }

    /**
     * @param point ProceedingJoinPoint exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @return the result of proceeding
     * @throws Throwable if the invoked proceed throws anything
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String userAgentStr = request.getHeader("User-Agent");

        final Log l = Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(
                        String.format(
                                "%s.%s", point.getSignature().getDeclaringTypeName(),
                                point.getSignature().getName()
                        )
                )
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(result)
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(userAgentStr)
                .build();

        LOGGER.info("Request Log Info : {}", OBJECT_MAPPER.writeValueAsString(l));

        return result;
    }

    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (names == null || names.length == 0 || args == null || args.length == 0) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            LOGGER.warn("{}method", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }


    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Log {
        private String threadId;
        private String threadName;
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private Object result;
        private Long timeCost;
        private String userAgent;
    }
}
