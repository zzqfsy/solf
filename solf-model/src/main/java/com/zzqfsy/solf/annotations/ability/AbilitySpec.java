package com.zzqfsy.solf.annotations.ability;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务领域能力规格
 * <p>
 * 作用于领域服务方法DomainService.method
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AbilitySpec {
    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 场景
     *
     * @return
     */
    String scenario() default "";

    /**
     * 描述
     *
     * @return
     */
    String description() default "";

    /**
     * 业务身份编码
     *
     * @return
     */
    String bizIdentityCode() default "";
}
