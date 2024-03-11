package com.zzqfsy.solf.service.demo.domain.stockIn.domainService;

import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.entity.StockInEntity;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface StockInDomainService {

    /**
     * 创建
     * @param StockInCreateCmd
     * @return
     */
    StockInEntity create(StockInCreateCmd StockInCreateCmd);

    StockInEntity createFlow(StockInCreateCmd build);
}
