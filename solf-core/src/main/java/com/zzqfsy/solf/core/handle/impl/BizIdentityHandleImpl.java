package com.zzqfsy.solf.core.handle.impl;

import com.zzqfsy.solf.core.context.impl.BizIdentityContext;
import com.zzqfsy.solf.core.handle.BizIdentityHandle;
import com.zzqfsy.solf.core.utils.BeanUtils;
import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.model.identity.BizIdentityMethodObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 业务身份识别处理实现
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@Slf4j
public class BizIdentityHandleImpl implements BizIdentityHandle {
    @Resource
    private BizIdentityContext bizIdentityContext;

    @Override
    public <T> BizIdentity bizIdentityRecognize(String bizIdentityCode, T t) {
        //参数校验
        if (bizIdentityCode == null || bizIdentityCode.length() == 0 || t == null) {
            return null;
        }

        // 请求方法业务身份不存在
        Object bizHandle = bizIdentityContext.getBizIdentityObjectMap().get(bizIdentityCode);
        if (bizHandle == null) {
            return null;
        }

        Map<String, BizIdentityMethodObject> parameterObjectMap = bizIdentityContext.getBizIdentityBeanMethodMap().get(bizIdentityCode);
        if (parameterObjectMap == null) {
            return null;
        }

        BizIdentityMethodObject bizIdentityMethodObject = parameterObjectMap.get(t.getClass().toString());

        // 参数类型一致
        if (!bizIdentityMethodObject.getParameterType().isAssignableFrom(t.getClass())) {
            return null;
        }
        // 请求方法返回值类型不同
        if (!bizIdentityMethodObject.getReturnType().isAssignableFrom(BizIdentity.class)) {
            return null;
        }

        String[] argNames = BeanUtils.getParameterNames(bizIdentityMethodObject.getMethod());
        Class[] paramTypes = bizIdentityMethodObject.getMethod().getParameterTypes();
        Object[] args = BeanUtils.genArgs(argNames, paramTypes, t);
        try {
            Object obj = bizIdentityMethodObject.getMethod().invoke(bizHandle, args);
            if (obj == null) {
                return null;
            }
            if (!(obj instanceof BizIdentity)) {
                return null;
            }

            return (BizIdentity) obj;
        } catch (IllegalAccessException e) {
            log.error("bizIdentityRecognize IllegalAccessException: ", e);
            throw new RuntimeException(e.getCause());
        } catch (InvocationTargetException e) {
            log.error("bizIdentityRecognize InvocationTargetException: ", e);
            throw new RuntimeException(e.getCause());
        }
    }
}
