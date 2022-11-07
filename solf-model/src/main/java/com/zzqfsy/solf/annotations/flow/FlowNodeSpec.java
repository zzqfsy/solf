package com.zzqfsy.solf.annotations.flow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FlowNodeSpec {

    /**
     * 编码
     */
    String code() default "";

    /**
     * 名称
     */
    String name() default "";

    /**
     * 领域名称
     */
    String domain() default "";

    /**
     * 能力名称
     */
    String ability() default "";
}
