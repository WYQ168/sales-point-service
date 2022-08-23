package com.sales.app.enums;

public enum OrderTypeEnum {


    MALL("mall","1"),
    INTEGRAL("integral","2"),
    GIFT("gift","3");

    OrderTypeEnum(String orderType,String orderCode){
        this.orderType = orderType;
        this.orderCode = orderCode;
    }

    private String orderType;
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public String getOrderType() {
        return orderType;
    }

}
