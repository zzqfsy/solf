package com.zzqfsy.solf.core.context.impl;

import com.zzqfsy.solf.annotations.flow.FlowChainSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeOrderSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeSpec;
import com.zzqfsy.solf.core.context.ContextRegister;
import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import com.zzqfsy.solf.model.flow.FlowChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 流程上下文
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@Slf4j
public class FlowChainContext implements ContextRegister {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DomainAbilityContext domainAbilityContext;


    /**
     * flow chain -> flow node domain name, ability name
     */
    private Map<String, FlowChain> flowChainDomainAbilityMap = null;


    @PostConstruct
    @Override
    public void register() {
        flowChainDomainAbilityMap = new HashMap<>();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(FlowChainSpec.class);

        for (String key : map.keySet()) {
            // 链
            Object obj = map.get(key);
            FlowChainSpec flowChainSpec = obj.getClass().getAnnotation(FlowChainSpec.class);
            if (flowChainSpec == null) {
                continue;
            }

            FlowChain flowChain = flowChainDomainAbilityMap.get(flowChainSpec.code());
            if (flowChain == null) {
                flowChain = new FlowChain(flowChainSpec.name(), flowChainSpec.code());
            }

            for (Method method : obj.getClass().getDeclaredMethods()) {
                // 节点
                FlowNodeSpec flowNodeSpec = method.getAnnotation(FlowNodeSpec.class);
                if (flowNodeSpec == null) {
                    continue;
                }
                if (method.getParameterTypes().length != 1) {
                    continue;
                }
                Class<?> parameterType = method.getParameterTypes()[0];
                Class<?> returnType = method.getReturnType();
                String domainName = flowNodeSpec.domain();
                String abilityName = flowNodeSpec.ability();

                // 校验参数匹配度
                LinkedList<AbilityMethodObject> abilityMethodObjects = domainAbilityContext.getDomainAbilityBeanMethodMap()
                        .get(domainName).get(abilityName);
                if (CollectionUtils.isEmpty(abilityMethodObjects)) {
                    log.error("启动异常，注册流程异常，能力方法注册为空");
                    throw new RuntimeException("启动异常，注册流程异常，能力方法注册为空");
                }
                if (!abilityMethodObjects.get(0).getParameterType().isAssignableFrom(parameterType) ||
                        (!(Void.TYPE.isAssignableFrom(returnType)) &&
                        !abilityMethodObjects.get(0).getReturnType().isAssignableFrom(returnType))
                ) {
                    log.error("启动异常，注册流程异常信息: \n{} {}\n\n{}", parameterType, returnType,
                            abilityMethodObjects.get(0));
                    throw new RuntimeException("启动异常，注册流程异常");

                }

                // 节点顺序
                FlowNodeOrderSpec flowNodeOrderSpec = method.getAnnotation(FlowNodeOrderSpec.class);
                int order = (flowNodeOrderSpec == null ? 0 : flowNodeOrderSpec.value());

                flowChain.insert(flowNodeSpec.name(), flowNodeSpec.code(),
                        flowNodeSpec.domain(), flowNodeSpec.ability(), order);
            }

            flowChainDomainAbilityMap.put(flowChainSpec.code(), flowChain);
        }
    }

    /**
     * 注销
     */
    @Override
    public void unregister() {

    }


    public Map<String, FlowChain> getFlowChainDomainAbilityMap() {
        return flowChainDomainAbilityMap;
    }

    public void setFlowChainDomainAbilityMap(Map<String, FlowChain> flowChainDomainAbilityMap) {
        this.flowChainDomainAbilityMap = flowChainDomainAbilityMap;
    }
}
