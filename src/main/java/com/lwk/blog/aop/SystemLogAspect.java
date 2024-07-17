package com.lwk.blog.aop;

import com.lwk.blog.annotation.SystemLog;
import com.lwk.blog.utils.LoginUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lwk
 * @date 2024-07-17 23:03:05
 * @deprecated 描述
 */
@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Around("@annotation(com.lwk.blog.annotation.SystemLog)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String requestTime = sdf.format(new Date());
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SystemLog annotation = method.getAnnotation(SystemLog.class);
        String logMessage = annotation.value();

        // 打印请求日志
        logger.info("{} 请求Url: {}", logMessage, request.getRequestURL().toString());
        logger.info("{} 方法: {}", logMessage, request.getMethod());
        logger.info("{} 请求头: {}", logMessage, request.getHeaderNames());
        logger.info("{} 参数: {}", logMessage, Arrays.toString(joinPoint.getArgs()));

        // 执行业务逻辑
        Object result = joinPoint.proceed();

        // 打印响应日志
        logger.info("{} 响应: {}", logMessage, result);

        long end = System.currentTimeMillis();
        logger.info("{} 执行时间: {}ms", logMessage, (end - start));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into operation_logs ");
        stringBuilder.append("(timestamp, user_id, description, request_url, request_method, request_params, response_result, execution_time) ");
        stringBuilder.append("values (");
        stringBuilder.append("'").append(requestTime).append("',");
        stringBuilder.append("'").append(LoginUserInfo.getCurrentUserId()==null?"未登录" : LoginUserInfo.getCurrentUserId()).append("',");
        stringBuilder.append("'").append(logMessage).append("',");
        stringBuilder.append("'").append(request.getRequestURL().toString()).append("',");
        stringBuilder.append("'").append(request.getMethod()).append("',");
        stringBuilder.append("'").append(Arrays.toString(joinPoint.getArgs())).append("',");
        stringBuilder.append("'").append(result).append("',");
        stringBuilder.append("'").append(end - start).append("'");
        stringBuilder.append(")");
        jdbcTemplate.execute(stringBuilder.toString());
        return result;
    }
}
