package com.natergy.natergyh5.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    // 操作
    String operate();

    // 模块
    String module();


    String args() default "";

}
