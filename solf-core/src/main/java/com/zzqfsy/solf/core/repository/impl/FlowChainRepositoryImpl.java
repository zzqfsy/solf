package com.zzqfsy.solf.core.repository.impl;

import com.zzqfsy.solf.core.context.impl.FlowChainContext;
import com.zzqfsy.solf.core.repository.FlowChainRepository;
import com.zzqfsy.solf.model.flow.FlowChain;
import com.zzqfsy.solf.view.FlowNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
public class FlowChainRepositoryImpl implements FlowChainRepository {


    @Autowired
    private FlowChainContext flowChainContext;

    List<FlowNodeVo> list = null;

    /**
     * 初始化并获取所有
     *
     * @return
     */
    private List<FlowNodeVo> initAndGetAll() {
        if (list != null) {
            return list;
        }

        list = initAll();
        return list;
    }

    private List<FlowNodeVo> initAll() {
        Map<String, FlowChain> flowChainMap = flowChainContext.getFlowChainDomainAbilityMap();

        List<FlowNodeVo> result = new ArrayList<>();
        FlowNodeVo flowNodeVo = null;
        for (FlowChain flowChain : flowChainMap.values()) {
            flowNodeVo = new FlowNodeVo();

            flowNodeVo.setChainCode(flowChain.getCode());
            flowNodeVo.setChainName(flowChain.getName());

            flowNodeVo.setFlowNodeList(flowChain.getAllNode());

            result.add(flowNodeVo);
        }

        return result;
    }

    @Override
    public List<FlowNodeVo> getAllFlowNode() {
        return initAndGetAll();
    }

}
