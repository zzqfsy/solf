package com.zzqfsy.solf.model.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zzqfsy.solf.annotations.identity.BizIdentitySpec;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 方法对象
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Data
@Builder
public class BizIdentityMethodObject {
    /**
     * 对象
     */
    @JsonIgnore
    private Object object;
    /**
     * 方法
     */
    @JsonIgnore
    private Method method;

    /**
     * 类名
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private Class<?> parameterType;

    /**
     * 返回值
     */
    private Class<?> returnType;

    /**
     * 业务身份规格
     */
    @JsonIgnore
    private BizIdentitySpec bizIdentitySpec;

}
