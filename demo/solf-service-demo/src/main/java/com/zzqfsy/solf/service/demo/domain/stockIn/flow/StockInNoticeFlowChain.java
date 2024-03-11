package com.zzqfsy.solf.service.demo.domain.stockIn.flow;

import com.zzqfsy.solf.annotations.flow.FlowChainSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeOrderSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeSpec;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import org.springframework.stereotype.Component;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@FlowChainSpec(name = "入库通知", code = "stockInNoticeFlow")
public class StockInNoticeFlowChain {

    @FlowNodeOrderSpec(1)
    @FlowNodeSpec(name = "入库通知创建", code = "StockInNotice.create",
            domain = "stockInNoticeDomain", ability = "create")
    public void create(StockInCreateCmd StockInCreateCmd){
    }
}