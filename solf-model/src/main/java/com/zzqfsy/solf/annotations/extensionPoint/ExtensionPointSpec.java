package com.zzqfsy.solf.annotations.extensionPoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展能力规格
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionPointSpec {
    /**
     * 领域
     *
     * @return
     */
    String domain() default "";

    /**
     * 场景
     *
     * @return
     */
    String scenario() default "";

    /**
     * 名称
     *
     * @return
     */
    String name() default "";

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
