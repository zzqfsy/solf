package com.zzqfsy.solf.service.demo.model;

import com.zzqfsy.solf.service.demo.model.enums.StockInType;
import lombok.Builder;
import lombok.Data;

/**
 * 入库单领域对象
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Builder
@Data
public class StockInDo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 类型
     *
     * @see StockInType
     */
    private Integer type;

    /**
     * 仓库类型
     *
     * @see com.zzqfsy.solf.service.demo.model.enums.WarehouseType
     */
    private Integer warehouseType;

    /**
     * 内容
     */
    private String content;

    /**
     * 扩展字段
     */
    private String extJson;
}
