package com.zzqfsy.solf.core.context.impl;

import com.zzqfsy.solf.annotations.identity.BizIdentitySpec;
import com.zzqfsy.solf.core.context.ContextRegister;
import com.zzqfsy.solf.model.identity.BizIdentityMethodObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务身份上下文
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@Slf4j
public class BizIdentityContext implements ContextRegister {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * bizIdentity code -> obj
     */
    private Map<String, Object> bizIdentityObjectMap = null;
    /**
     * bizIdentity code -> parameter name -> method
     */
    private Map<String, Map<String, BizIdentityMethodObject>> bizIdentityBeanMethodMap = null;

    /**
     * 注册
     */
    @PostConstruct
    public void register() {
        bizIdentityObjectMap = new HashMap<>();
        bizIdentityBeanMethodMap = new HashMap<>();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(BizIdentitySpec.class);

        for (String key : map.keySet()) {
            Object obj = map.get(key);
            BizIdentitySpec bizIdentitySpec = obj.getClass().getAnnotation(BizIdentitySpec.class);
            if (bizIdentitySpec == null) {
                continue;
            }

            // 不允许一个spec存在多个实现
            Assert.isNull(bizIdentityObjectMap.get(bizIdentitySpec.code()),
                    "不允许一个spec存在多个类实现: " + bizIdentitySpec.code());

            bizIdentityObjectMap.put(bizIdentitySpec.code(), obj);

            Map<String, BizIdentityMethodObject> abilityBeanMethodMap = bizIdentityBeanMethodMap.get(bizIdentitySpec.code());
            if (abilityBeanMethodMap == null) {
                abilityBeanMethodMap = new HashMap<>();
            }
            for (Method method : obj.getClass().getDeclaredMethods()) {
                if (method.getParameterTypes().length != 1) {
                    continue;
                }
                Class<?> parameterType = method.getParameterTypes()[0];
                Class<?> returnType = method.getReturnType();

                // 不允许一个spec存在多个实现
                Assert.isNull(abilityBeanMethodMap.get(parameterType.toString()),
                        "不允许一个spec存在多个方法实现: " + parameterType.toString());

                BizIdentityMethodObject bizIdentityMethodObject = BizIdentityMethodObject.builder()
                        .method(method)
                        .className(obj.getClass().getTypeName())
                        .methodName(method.getName())
                        .parameterType(parameterType)
                        .returnType(returnType)
                        .bizIdentitySpec(bizIdentitySpec)
                        .build();
                abilityBeanMethodMap.put(parameterType.toString(), bizIdentityMethodObject);
            }
            bizIdentityBeanMethodMap.put(bizIdentitySpec.code(), abilityBeanMethodMap);
        }
    }

    /**
     * 注销
     */
    public void unregister() {

    }

    public Map<String, Object> getBizIdentityObjectMap() {
        return bizIdentityObjectMap;
    }

    public void setBizIdentityObjectMap(Map<String, Object> bizIdentityObjectMap) {
        this.bizIdentityObjectMap = bizIdentityObjectMap;
    }

    public Map<String, Map<String, BizIdentityMethodObject>> getBizIdentityBeanMethodMap() {
        return bizIdentityBeanMethodMap;
    }

    public void setBizIdentityBeanMethodMap(Map<String, Map<String, BizIdentityMethodObject>> bizIdentityBeanMethodMap) {
        this.bizIdentityBeanMethodMap = bizIdentityBeanMethodMap;
    }
}
