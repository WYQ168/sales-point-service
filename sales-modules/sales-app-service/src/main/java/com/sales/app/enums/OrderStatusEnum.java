package com.sales.app.enums;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatusEnum {
    NEW_ORDER(0000,"待支付"),
    CANCEL_ORDER(0001,"取消支付"),
    PAY_FAIL(999,"支付失败"),
    PAY_SUC(1000,"支付成功"),
    EXCHANGE_AUDIT(1002,"兑换审核中"),
    EXCHANGE_SUC(1001,"兑换成功"),
    WAIT_PICKUP(2000,"待发货"),
    PICKUP(3000,"待收获"),
    TO_WH(4000,"待评价"),
    ;

    private static final Map<Integer , OrderStatusEnum> lookup = new HashMap();
    static {
        for (OrderStatusEnum s : EnumSet.allOf(OrderStatusEnum.class)) {
            lookup.put(s.getState() , s);
        }
    }

    public Integer getState() {
        return state;
    }

    public static OrderStatusEnum get(Integer state) {
        return lookup.get(state);
    }

    private Integer state;
    private String stateName;

    OrderStatusEnum(Integer state, String stateName){
        this.state = state;
        this.stateName = stateName;
    }

    
}

