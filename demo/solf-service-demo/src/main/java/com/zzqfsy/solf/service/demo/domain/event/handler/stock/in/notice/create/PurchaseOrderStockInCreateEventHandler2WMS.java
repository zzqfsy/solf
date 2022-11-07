package com.zzqfsy.solf.service.demo.domain.event.handler.stock.in.notice.create;

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
@Slf4j
@DomainSpec(name = "入库领域", domain = "stockInNoticeDomain", description = "")
public class PurchaseOrderStockInCreateEventHandler2WMS {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @AbilityOrderSpec(301)
    @AbilitySpec(name = "创建", scenario = "create", description = "仓库采购入库创建", bizIdentityCode = "purchaseOrderBizId2WMS")
    public Integer handler(StockInDo stockInDo) {
        System.out.println("PurchaseOrderStockInCreateEventHandler wms handler");
        return atomicInteger.incrementAndGet();
    }

}
