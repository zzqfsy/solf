package com.zzqfsy.solf.core.handle.impl;

import com.zzqfsy.solf.core.context.impl.FlowChainContext;
import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.core.handle.FlowChainHandle;
import com.zzqfsy.solf.model.ability.AbstractAbilityCommandParam;
import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;
import com.zzqfsy.solf.model.flow.FlowChain;
import com.zzqfsy.solf.model.flow.FlowNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component("flowChainHandle")
@Slf4j
public class FlowChainHandleImpl<T extends AbstractAbilityCommandParam, E extends AbstractAbilityReturnParam>
        implements FlowChainHandle {

    @Autowired
    private FlowChainContext flowChainContext;
    @Autowired
    private DomainAbilityHandle domainAbilityHandle;


    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public AbstractAbilityReturnParam transactionalHandle(String flowCode, AbstractAbilityCommandParam abstractAbilityCommandParam) {
        FlowChain flowChain = flowChainContext.getFlowChainDomainAbilityMap().get(flowCode);
        if (flowChain == null){
            throw new RuntimeException("不存在");
        }

        FlowNode flowNode = flowChain.getFirst();
        AbstractAbilityReturnParam returnParamAbstract = null;
        while (flowNode != null) {
            returnParamAbstract = domainAbilityHandle.transactionalHandle(
                    flowNode.getDomainName(), flowNode.getAbilityName(),
                    abstractAbilityCommandParam);

            flowNode = flowNode.next;
        }

        return returnParamAbstract;
    }

    @Override
    public AbstractAbilityReturnParam handle(String flowCode, AbstractAbilityCommandParam abstractAbilityCommandParam) {
        FlowChain flowChain = flowChainContext.getFlowChainDomainAbilityMap().get(flowCode);
        if (flowChain == null){
            throw new RuntimeException("不存在");
        }

        FlowNode flowNode = flowChain.getFirst();
        AbstractAbilityReturnParam returnParamAbstract = null;
        while (flowNode != null) {
            returnParamAbstract = domainAbilityHandle.handle(
                    flowNode.getDomainName(), flowNode.getAbilityName(),
                    abstractAbilityCommandParam);

            flowNode = flowNode.next;
        }

        return returnParamAbstract;
    }
}
