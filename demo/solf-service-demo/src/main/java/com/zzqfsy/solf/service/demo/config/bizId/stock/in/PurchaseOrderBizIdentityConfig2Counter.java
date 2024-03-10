package com.zzqfsy.solf.service.demo.config.bizId.stock.in;

import com.zzqfsy.solf.annotations.identity.BizIdentitySpec;
import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.enums.StockInType;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.enums.WarehouseType;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@BizIdentitySpec(code = "purchaseOrderBizId2Counter", name = "货柜采购单")
public class PurchaseOrderBizIdentityConfig2Counter {

    /**
     * 获取货柜采购单的身份
     *
     * @return
     */
    public BizIdentity getBizIdentity(StockInCreateCmd StockInCreateCmd) {
        if (StockInCreateCmd == null) {
            return null;
        }

        if (!StockInType.PURCHASE_ORDER.getType().equals(StockInCreateCmd.getType())){
            return null;
        }

        if (!WarehouseType.COUNTER_TYPE.getType().equals(StockInCreateCmd.getWarehouseType())){
            return null;
        }

        ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>();
        configMap.put("taskModel", "2");

        return BizIdentity.Builder.aBizIdentity()
                .code("purchaseOrderBizId2Counter")
                .name("货柜采购单")
                .configMap(configMap)
                .build();
    }
}
