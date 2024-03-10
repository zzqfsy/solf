package com.zzqfsy.solf.service.demo.domain.stockIn.model.enums;

/**
 * 入库类型
 */
public enum WarehouseType {

    WMS_TYPE(1, "仓库"),
    COUNTER_TYPE(2, "货柜"),

    ;

    WarehouseType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
