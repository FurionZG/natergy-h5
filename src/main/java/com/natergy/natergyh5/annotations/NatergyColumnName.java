package com.natergy.natergyh5.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/30 13:44
 */
@Retention(RetentionPolicy.RUNTIME) // 在运行时可以获取
@Target(value = {ElementType.FIELD})  // 作用到属性上
public @interface NatergyColumnName {
    String value() default "";
}
