package com.zzqfsy.solf.service.demo.domain.stockIn.domainService.impl;

import com.zzqfsy.solf.core.handle.FlowChainHandle;
import com.zzqfsy.solf.service.demo.domain.stockIn.domainService.StockInDomainService;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.entity.StockInEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Service
public class StockInDomainServiceImpl implements StockInDomainService {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Resource
    private FlowChainHandle flowChainHandle;

    @Override
    public StockInEntity create(StockInCreateCmd StockInCreateCmd) {
        // 标准实现
        Integer count = atomicInteger.incrementAndGet();

        return new StockInEntity(count, StockInCreateCmd.getContent());
    }

    @Override
    public StockInEntity createFlow(StockInCreateCmd cmd) {
        cmd.setBizCode("purchaseOrderBizId2WMS");
        StockInEntity stockInEntity = (StockInEntity) flowChainHandle.handle("stockInNoticeFlow", cmd);
        return stockInEntity;
    }
}
