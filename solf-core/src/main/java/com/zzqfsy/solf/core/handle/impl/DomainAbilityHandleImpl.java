package com.zzqfsy.solf.core.handle.impl;

import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.core.context.impl.DomainAbilityContext;
import com.zzqfsy.solf.core.handle.BizIdentityHandle;
import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.core.utils.BeanUtils;
import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import com.zzqfsy.solf.model.identity.BizIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Map;

/**
 * 领域能力处理实现
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@Slf4j
public class DomainAbilityHandleImpl implements DomainAbilityHandle {

    @Resource
    private DomainAbilityContext domainAbilityContext;
    @Resource
    private BizIdentityHandle bizIdentityHandle;

    /**
     * 事务处理
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param <T>
     */
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public <T, E> void transactionalHandle(String domainName, String abilityName, T t, Class<E> returnClazz) {
        handle(domainName, abilityName, t, returnClazz);
    }

    @Override
    public <T, E> E transactionalHandleReturn(String domainName, String abilityName, T t, Class<E> returnClazz) {
        return handleReturn(domainName, abilityName, t, returnClazz);
    }

    /**
     * 处理
     *
     * @param domainName
     * @param abilityName
     * @return
     */
    @Override
    public <T, E> void handle(String domainName, String abilityName, T t, Class<E> returnClazz) {
        handleReturn(domainName, abilityName, t, returnClazz);
    }

    @Override
    public <T, E> E handleReturn(String domainName, String abilityName, T t, Class<E> returnClazz) {
        Map<String, LinkedList<AbilityMethodObject>> domainBeanMethodMap = domainAbilityContext.getDomainAbilityBeanMethodMap().get(domainName);
        if (domainBeanMethodMap.isEmpty()) {
            throw new RuntimeException("不存在请求领域: " + domainName + ":" + abilityName);
        }

        LinkedList<AbilityMethodObject> abilityBeanMethods = domainBeanMethodMap.get(abilityName);
        if (abilityBeanMethods.isEmpty()) {
            throw new RuntimeException("不存在请求领域能力: " + domainName + ":" + abilityName);
        }

        Boolean isHandled = false;
        Object result = null;
        for (AbilityMethodObject abilityMethodObject : abilityBeanMethods) {
            // 请求方法不存在
            if (abilityMethodObject == null || abilityMethodObject.getMethod() == null) {
                continue;
            }
            // 请求方法参数类型不同
            if (!abilityMethodObject.getParameterType().isAssignableFrom(t.getClass())) {
                continue;
            }
            // 请求方法返回值类型不同
            if (!abilityMethodObject.getReturnType().isAssignableFrom(returnClazz)) {
                continue;
            }

            // 业务身份 match biz identity
            AbilitySpec abilitySpec = abilityMethodObject.getAbilitySpec();
            if (abilitySpec == null) {
                continue;
            }
            // 业务身份识别
            BizIdentity bizIdentity = bizIdentityHandle.bizIdentityRecognize(abilitySpec.bizIdentityCode(), t);
            if (bizIdentity == null) {
                continue;
            }

            Object handler = abilityMethodObject.getObject();
            String[] argNames = BeanUtils.getParameterNames(abilityMethodObject.getMethod());
            Class[] paramTypes = abilityMethodObject.getMethod().getParameterTypes();
            Object[] args = BeanUtils.genArgs(argNames, paramTypes, t);
            try {
                result = abilityMethodObject.getMethod().invoke(handler, args);
                if (result != null) {
                    isHandled = true;
                }
            } catch (IllegalAccessException e) {
                log.error("handleRequest IllegalAccessException: ", e);
                throw new RuntimeException(e.getCause());
            } catch (InvocationTargetException e) {
                log.error("handleRequest InvocationTargetException: ", e);
                throw new RuntimeException(e.getCause());
            }
        }

        if (!isHandled) {
            throw new RuntimeException(String.format(
                    "请求消息没有领域(%s)能力(%s)处理", domainName, abilityName)
            );
        }

        return (E) result;
    }
}
