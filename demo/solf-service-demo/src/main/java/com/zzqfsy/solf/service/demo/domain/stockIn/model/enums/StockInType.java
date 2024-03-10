package com.zzqfsy.solf.service.demo.domain.stockIn.model.enums;

/**
 * 入库类型
 */
public enum StockInType {

    PURCHASE_ORDER(1, "采购单"),
    SALE_RETURN_ORDER(2, "销售退货单"),

    ;

    StockInType(Integer type, String desc) {
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
