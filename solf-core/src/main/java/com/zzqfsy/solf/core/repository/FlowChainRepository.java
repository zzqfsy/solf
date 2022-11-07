package com.zzqfsy.solf.core.repository;


import com.zzqfsy.solf.view.FlowNodeVo;

import java.util.List;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface FlowChainRepository {


    /**
     * 获取所有的业务身份
     */
    List<FlowNodeVo> getAllFlowNode();
}
