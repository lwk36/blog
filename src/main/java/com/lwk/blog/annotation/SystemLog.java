package com.lwk.blog.annotation;

/**
 * @author lwk
 * @date 2024-07-17 23:02:23
 * @deprecated 日志注解
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String value() default "";
}

