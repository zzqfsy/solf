package com.zzqfsy.solf.core.handle.impl;

import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.core.context.impl.DomainAbilityContext;
import com.zzqfsy.solf.core.handle.BizIdentityHandle;
import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.core.utils.BeanUtils;
import com.zzqfsy.solf.model.ability.AbstractAbilityCommandParam;
import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;
import com.zzqfsy.solf.model.identity.BizIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class AbilityHandleImpl<T extends AbstractAbilityCommandParam, E extends AbstractAbilityReturnParam>
        implements DomainAbilityHandle {

    @Autowired
    private DomainAbilityContext domainAbilityContext;
    @Autowired
    private BizIdentityHandle bizIdentityHandle;

    @Transactional
    @Override
    public AbstractAbilityReturnParam transactionalHandle(String domainName, String abilityName, AbstractAbilityCommandParam abstractAbilityCommandParam) {
        return handleReturn(domainName, abilityName, abstractAbilityCommandParam);
    }

    @Override
    public AbstractAbilityReturnParam handle(String domainName, String abilityName, AbstractAbilityCommandParam abstractAbilityCommandParam) {
        return handleReturn(domainName, abilityName, abstractAbilityCommandParam);
    }

    public AbstractAbilityReturnParam handleReturn(String domainName, String abilityName, AbstractAbilityCommandParam t) {
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
                log.warn("请求方法不存在: " + abilityName);
                continue;
            }
            // 请求方法参数类型不同
            if (!abilityMethodObject.getParameterType().isAssignableFrom(t.getClass())) {
                log.warn("请求方法参数类型不同: " + abilityMethodObject.getParameterType().getName() + "!= " + t.getClass().getName());
                continue;
            }
            // todo zzq 请求方法返回值类型不同
//            if (!abilityMethodObject.getReturnType().isAssignableFrom(AbstractAbilityReturnParam.class)) {
//                log.warn("请求方法返回值类型不同: " + abilityMethodObject.getReturnType().getName() + "!= " + AbstractAbilityReturnParam.class.getName());
//                continue;
//            }

            // 业务身份 match biz identity
            AbilitySpec abilitySpec = abilityMethodObject.getAbilitySpec();
            if (abilitySpec == null) {
                log.warn("请求方法业务身份为空: " + abilityName);
                continue;
            }
            // 业务身份识别
            BizIdentity bizIdentity = bizIdentityHandle.bizIdentityRecognize(abilitySpec.bizIdentityCode(), t);
            if (bizIdentity == null) {
                log.warn("请求方法业务身份识别失败: " + abilityName);
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
