package com.zzqfsy.solf.service.demo.domain.stockIn.model.entity;

import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockInEntity extends AbstractAbilityReturnParam implements Serializable {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 内容
     */
    private String content;

}
