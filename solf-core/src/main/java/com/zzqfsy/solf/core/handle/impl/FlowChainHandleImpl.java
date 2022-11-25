package com.zzqfsy.solf.core.handle.impl;

import com.zzqfsy.solf.core.context.impl.FlowChainContext;
import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.core.handle.FlowChainHandle;
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
public class FlowChainHandleImpl implements FlowChainHandle {

    @Autowired
    private FlowChainContext flowChainContext;
    @Autowired
    private DomainAbilityHandle domainAbilityHandle;


    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public <T, E> void transactionalHandle(String flowCode, T t, Class<E> returnClazz) {
        handle(flowCode, t, returnClazz);
        return;
    }

    @Override
    public <T, E> void handleByNodeTransactional(String flowCode, T t, Class<E> returnClazz) {
        FlowChain flowChain = flowChainContext.getFlowChainDomainAbilityMap().get(flowCode);
        if (flowChain == null){
            return;
        }

        FlowNode flowNode = flowChain.getFirst();
        while (flowNode != null) {
            domainAbilityHandle.transactionalHandle(
                    flowNode.getDomainName(), flowNode.getAbilityName(),
                    t, returnClazz);

            flowNode = flowNode.next;
        }
    }

    @Override
    public <T, E> void handle(String flowCode, T t, Class<E> returnClazz) {
        FlowChain flowChain = flowChainContext.getFlowChainDomainAbilityMap().get(flowCode);
        if (flowChain == null){
            return;
        }

        FlowNode flowNode = flowChain.getFirst();
        while (flowNode != null) {
            domainAbilityHandle.handle(
                    flowNode.getDomainName(), flowNode.getAbilityName(),
                    t, returnClazz);

            flowNode = flowNode.next;
        }
    }
}
