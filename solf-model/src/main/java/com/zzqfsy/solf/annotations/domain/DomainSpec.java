package com.zzqfsy.solf.annotations.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务领域规格
 * <p>
 * 作用于领域服务DomainService
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainSpec {
    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 领域
     *
     * @return
     */
    String domain() default "";

    /**
     * 描述
     *
     * @return
     */
    String description() default "";
}
