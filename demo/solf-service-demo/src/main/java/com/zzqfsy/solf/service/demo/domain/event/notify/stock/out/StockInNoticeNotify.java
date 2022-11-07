package com.zzqfsy.solf.service.demo.domain.event.notify.stock.out;

import com.zzqfsy.solf.core.handle.DomainAbilityHandle;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
public class StockInNoticeNotify {

    @Autowired
    private DomainAbilityHandle domainAbilityHandle;

    public void notify(StockInDo stockInDo){
        domainAbilityHandle.handle("stockInDomain", "create", stockInDo, Integer.class);
    }
}
