package com.zzqfsy.solf.service.demo.domain.service.impl;

import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.core.handle.FlowChainHandle;
import com.zzqfsy.solf.service.demo.domain.service.StockInDomainService;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Service
public class StockInDomainServiceImpl implements StockInDomainService {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    private DomainAbilityHandle domainAbilityHandle;

    @Autowired
    private FlowChainHandle flowChainHandle;

    @Override
    public Integer create(StockInDo stockInDo) {
        // 标准实现
        Integer count = atomicInteger.incrementAndGet();

//        domainAbilityHandle.handle("stockInDomain", "create", stockInDo, Integer.class);
        flowChainHandle.handle("stockInNoticeFlow", stockInDo, Integer.class);
        return count;
    }
}
