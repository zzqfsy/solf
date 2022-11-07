package com.zzqfsy.solf.service.demo.controller;

import com.zzqfsy.solf.service.demo.domain.service.StockInDomainService;
import com.zzqfsy.solf.service.demo.model.StockInDo;
import com.zzqfsy.solf.service.demo.model.enums.StockInType;
import com.zzqfsy.solf.service.demo.model.enums.WarehouseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入库控制器
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@RestController
public class StockInController {

    @Autowired
    private StockInDomainService stockInDomainService;

    @RequestMapping("/stockIn")
    public Integer stockIn(@RequestParam(value="name", defaultValue="World") String name) {
        return stockInDomainService.create(
                StockInDo.builder().type(StockInType.PURCHASE_ORDER.getType())
                        .warehouseType(WarehouseType.WMS_TYPE.getType())
                        .content(name).build()
        );
    }
}