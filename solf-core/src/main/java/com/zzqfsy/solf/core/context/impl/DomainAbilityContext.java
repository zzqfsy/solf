package com.zzqfsy.solf.core.context.impl;

import com.zzqfsy.solf.annotations.ability.AbilityOrderSpec;
import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.annotations.domain.DomainSpec;
import com.zzqfsy.solf.core.context.ContextRegister;
import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 领域能力上下文
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@Slf4j
public class DomainAbilityContext implements ContextRegister {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * domain code -> ability scenario -> bean name, method
     */
    private Map<String, Map<String, LinkedList<AbilityMethodObject>>> domainAbilityBeanMethodMap = null;

    /**
     * 注册
     */
    @PostConstruct
    public void register() {
        domainAbilityBeanMethodMap = new HashMap<>();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(DomainSpec.class);

        for (String key : map.keySet()) {
            Object obj = map.get(key);
            DomainSpec domainSpec = obj.getClass().getAnnotation(DomainSpec.class);
            if (domainSpec == null) {
                continue;
            }

            Map<String, LinkedList<AbilityMethodObject>> abilityBeanMethodMap = domainAbilityBeanMethodMap.get(domainSpec.domain());
            if (abilityBeanMethodMap == null) {
                abilityBeanMethodMap = new HashMap<>();
            }
            LinkedList<AbilityMethodObject> abilityMethodObjects = null;
            for (Method method : obj.getClass().getDeclaredMethods()) {
                AbilitySpec abilitySpec = method.getAnnotation(AbilitySpec.class);
                if (abilitySpec == null) {
                    continue;
                }
                if (method.getParameterTypes().length != 1) {
                    continue;
                }
                Class<?> parameterType = method.getParameterTypes()[0];
                Class<?> returnType = method.getReturnType();

                abilityMethodObjects = abilityBeanMethodMap.get(abilitySpec.scenario());
                if (abilityMethodObjects == null) {
                    abilityMethodObjects = new LinkedList<>();
                }

                AbilityOrderSpec abilityOrderSpec = method.getAnnotation(AbilityOrderSpec.class);
                int order = (abilityOrderSpec == null ? 0 : abilityOrderSpec.value());

                // build method object
                AbilityMethodObject abilityMethodObject = AbilityMethodObject.builder()
                        .object(obj)
                        .beanClass(obj.getClass())
                        .method(method)
                        .methodName(method.getName())
                        .parameterType(parameterType)
                        .returnType(returnType)
                        .abilitySpec(abilitySpec)
                        .domainSpec(domainSpec)
                        .order(order)
                        .build();

                abilityMethodObjects.add(abilityMethodObject);

                abilityBeanMethodMap.put(abilitySpec.scenario(), abilityMethodObjects);
            }
            Collections.sort(abilityMethodObjects);

            domainAbilityBeanMethodMap.put(domainSpec.domain(), abilityBeanMethodMap);
        }
    }

    /**
     * 注销
     */
    public void unregister() {

    }

    public Map<String, Map<String, LinkedList<AbilityMethodObject>>> getDomainAbilityBeanMethodMap() {
        return domainAbilityBeanMethodMap;
    }

    public void setDomainAbilityBeanMethodMap(Map<String, Map<String, LinkedList<AbilityMethodObject>>> domainAbilityBeanMethodMap) {
        this.domainAbilityBeanMethodMap = domainAbilityBeanMethodMap;
    }
}
