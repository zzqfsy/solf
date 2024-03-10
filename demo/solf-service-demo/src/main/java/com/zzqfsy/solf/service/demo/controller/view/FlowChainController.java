package com.zzqfsy.solf.service.demo.controller.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzqfsy.solf.core.repository.FlowChainRepository;
import com.zzqfsy.solf.model.view.FlowNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 流程链视图
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@RestController
@RequestMapping("/flowChain")
public class FlowChainController {

    @Autowired
    private FlowChainRepository flowChainRepository;

    @Autowired
    private BizIdentityController bizIdentityController;

    @RequestMapping("/config")
    public String config() {
        List<FlowNodeVo> flowNodeVoList = flowChainRepository.getAllFlowNode();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readValue(
                    objectMapper.writeValueAsString(flowNodeVoList),
                    JsonNode.class);
            return jsonNode.toPrettyString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
