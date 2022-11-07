package com.zzqfsy.solf.service.demo.config.bizId.stock.in;

import com.zzqfsy.solf.annotations.identity.BizIdentitySpec;
import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import com.zzqfsy.solf.service.demo.model.enums.StockInType;
import com.zzqfsy.solf.service.demo.model.enums.WarehouseType;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@BizIdentitySpec(code = "purchaseOrderBizId2WMS", name = "仓库采购单")
public class PurchaseOrderBizIdentityConfig2WMS {

    /**
     * 获取仓库采购单的身份
     *
     * @return
     */
    public BizIdentity getBizIdentity(StockInDo stockInDo) {
        if (stockInDo == null) {
            return null;
        }

        if (!StockInType.PURCHASE_ORDER.getType().equals(stockInDo.getType())){
            return null;
        }

        if (!WarehouseType.WMS_TYPE.getType().equals(stockInDo.getWarehouseType())){
            return null;
        }

        ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>();
        configMap.put("taskModel", "2");

        return BizIdentity.Builder.aBizIdentity()
                .code("purchaseOrderBizId2WMS")
                .name("仓库采购单")
                .configMap(configMap)
                .build();
    }
}
