package com.zzqfsy.solf.service.demo.domain.service;


import com.zzqfsy.solf.service.demo.model.StockInDo;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface StockInDomainService {

    /**
     * 创建
     * @param stockInDo
     * @return
     */
    Integer create(StockInDo stockInDo);
}
