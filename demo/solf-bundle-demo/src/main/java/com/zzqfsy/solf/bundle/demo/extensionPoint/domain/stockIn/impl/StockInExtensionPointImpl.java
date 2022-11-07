package com.zzqfsy.solf.bundle.demo.extensionPoint.domain.stockIn.impl;

import com.zzqfsy.solf.annotations.extensionPoint.ExtensionPointSpec;
import com.zzqfsy.solf.bundle.demo.extensionPoint.domain.stockIn.StockInExtensionPoint;
import org.springframework.stereotype.Service;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Service
public class StockInExtensionPointImpl implements StockInExtensionPoint {

    @ExtensionPointSpec(domain = "stockInDomain", scenario = "create", name = "创建",  description = "",
            bizIdentityCode = "miqilin")
    @Override
    public Integer create(String extJson) {
        return null;
    }
}
