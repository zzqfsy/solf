package com.zzqfsy.solf.service.demo.domain.event.handler.stock.in.notice.create;

import com.zzqfsy.solf.annotations.ability.AbilityOrderSpec;
import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.annotations.domain.DomainSpec;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 销售退货入库
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Service
@DomainSpec(name = "入库领域", domain = "stockInDomain", description = "")
public class SaleReturnOrderStockInCreateEventHandler {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @AbilityOrderSpec(300)
    @AbilitySpec(name = "创建", scenario = "create", description = "", bizIdentityCode = "saleReturnOrderBizId")
    public Integer handler(StockInDo stockInDo) {
        return atomicInteger.incrementAndGet();
    }

}
