package com.zzqfsy.solf.service.demo.config.bizId.stock.in;

import com.zzqfsy.solf.annotations.identity.BizIdentitySpec;
import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.command.StockInCreateCmd;
import com.zzqfsy.solf.service.demo.domain.stockIn.model.enums.StockInType;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
@BizIdentitySpec(code = "saleReturnOrderBizId", name = "销售退货单")
public class SaleReturnOrderBizIdentityConfig {


    /**
     * 获取销售单的身份
     * @return
     */
    public BizIdentity getBizIdentity(StockInCreateCmd StockInCreateCmd){
        if (StockInCreateCmd == null) {
            return null;
        }

        if (!StockInType.SALE_RETURN_ORDER.getType().equals(StockInCreateCmd.getType())){
            return null;
        }

        ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>();
        configMap.put("taskModel", "0");

        return BizIdentity.Builder.aBizIdentity()
                .code("saleReturnOrderBizId")
                .name("销售退货单")
                .configMap(configMap)
                .build();
    }
}
