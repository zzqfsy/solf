package com.zzqfsy.solf.service.demo.domain.event.handler.stock.in.notice.valid;

import com.zzqfsy.solf.annotations.ability.AbilityOrderSpec;
import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.annotations.domain.DomainSpec;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 采购入库
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Service
@DomainSpec(name = "入库领域", domain = "stockInNoticeDomain", description = "")
@Slf4j
public class PurchaseOrderStockInValidEventHandler {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @AbilityOrderSpec(100)
    @AbilitySpec(name = "校验", scenario = "valid", description = "", bizIdentityCode = "purchaseOrderBizId")
    public Integer handler(StockInDo stockInDo) {
        System.out.println("PurchaseOrderStockInValidEventHandler handler");
        return atomicInteger.incrementAndGet();
    }

}
