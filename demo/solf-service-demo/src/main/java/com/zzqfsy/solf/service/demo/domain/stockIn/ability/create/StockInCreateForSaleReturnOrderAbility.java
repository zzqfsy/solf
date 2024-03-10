package com.zzqfsy.solf.service.demo.domain.stockIn.ability.create;

import com.zzqfsy.solf.annotations.ability.AbilityOrderSpec;
import com.zzqfsy.solf.annotations.ability.AbilitySpec;
import com.zzqfsy.solf.annotations.domain.DomainSpec;
import com.zzqfsy.solf.core.handle.AbstractAbility;
import com.zzqfsy.solf.model.ability.AbilityReturnWrapper;
import com.zzqfsy.solf.service.demo.domain.stockIn.domainService.StockInDomainService;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.entity.StockInEntity;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.enums.StockInType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class StockInCreateForSaleReturnOrderAbility extends AbstractAbility<StockInCreateCmd, StockInEntity> {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Resource
    private StockInDomainService stockInDomainService;

    @AbilityOrderSpec(301)
    @AbilitySpec(name = "创建", scenario = "create", description = "仓库采购入库创建", bizIdentityCode = "purchaseOrderBizId2WMS")
    public StockInEntity handler(StockInCreateCmd stockInCreateCmd) {
        AbilityReturnWrapper<StockInEntity> result = execute(stockInCreateCmd);
        return result.getData();
    }

    @Override
    protected AbilityReturnWrapper<StockInEntity> checkBizIdentityMatch(StockInCreateCmd stockInCreateCmd) {
        if (stockInCreateCmd == null) {
            return null;
        }

        if (!StockInType.PURCHASE_ORDER.getType().equals(stockInCreateCmd.getType())){
            return null;
        }
        return AbilityReturnWrapper.ofSuccess(null);
    }

    @Override
    public AbilityReturnWrapper<StockInEntity> checkIdempotence(StockInCreateCmd abilityPo) {
        return AbilityReturnWrapper.ofSuccess(null);
    }

    @Override
    public AbilityReturnWrapper<StockInEntity> executeAbility(StockInCreateCmd abilityPo) {
        return AbilityReturnWrapper.ofSuccess(stockInDomainService.create(abilityPo));
    }
}
