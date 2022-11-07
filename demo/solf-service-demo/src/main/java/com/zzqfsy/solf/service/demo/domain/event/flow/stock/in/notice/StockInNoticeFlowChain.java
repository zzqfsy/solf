package com.zzqfsy.solf.service.demo.domain.event.flow.stock.in.notice;

import com.zzqfsy.solf.annotations.flow.FlowChainSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeOrderSpec;
import com.zzqfsy.solf.annotations.flow.FlowNodeSpec;
import com.zzqfsy.solf.service.demo.model.StockInDo;
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
    @FlowNodeSpec(name = "入库通知校验", code = "StockInNotice.valid",
        domain = "stockInNoticeDomain", ability = "valid")
    public void valid(StockInDo stockInDo){
    }

    @FlowNodeOrderSpec(2)
    @FlowNodeSpec(name = "入库通知组装", code = "StockInNotice.assembly",
            domain = "stockInNoticeDomain", ability = "assembly")
    public void assembly(StockInDo stockInDo){
    }

    @FlowNodeOrderSpec(3)
    @FlowNodeSpec(name = "入库通知创建", code = "StockInNotice.create",
            domain = "stockInNoticeDomain", ability = "create")
    public void create(StockInDo stockInDo){
    }
}