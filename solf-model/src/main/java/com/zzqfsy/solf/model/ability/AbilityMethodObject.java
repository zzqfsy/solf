package com.zzqfsy.solf.model.ability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.annotations.domain.DomainSpec;
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
public class AbilityMethodObject implements Comparable<AbilityMethodObject> {
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
    private Class beanClass;
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
     * 域规格
     */
    @JsonIgnore
    private DomainSpec domainSpec;

    /**
     * 能力规格
     */
    @JsonIgnore
    private AbilitySpec abilitySpec;

    /**
     * 顺序
     */
    private int order;

    @Override
    public int compareTo(AbilityMethodObject abilityMethodObject) {
        if (this.getOrder() < abilityMethodObject.getOrder()) {
            return -1;
        } else if (this.getOrder() > abilityMethodObject.getOrder()) {
            return 1;
        } else {
            return 0;
        }
    }
}
