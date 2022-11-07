package com.zzqfsy.solf.annotations.identity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务身份规格
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizIdentitySpec {
    /**
     * 编码
     */
    String code() default "";

    /**
     * 名称
     */
    String name() default "";
}
