package com.natergy.natergyh5.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复提交注解
 */
@Retention(RetentionPolicy.RUNTIME) // 在运行时可以获取
@Target(value = {ElementType.METHOD, ElementType.TYPE})  // 作用到类，方法，接口上等
public @interface NoRepeatSubmit {
    //锁定时间
    int lockTime() default 10;
}
