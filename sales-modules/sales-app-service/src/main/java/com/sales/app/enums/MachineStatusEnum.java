package com.sales.app.enums;

public enum MachineStatusEnum {

    NO_ACTIVATION(1,"未激活"),
    ISSUED(2,"已下发"),
    USED_ACTIVATION(3,"已激活"),
    USED_BINDING(4,"已绑定"),
    NO_BINDING(5,"未绑定"),
    HAS_CANCEL(6,"已注销"),
    USING(7,"使用中"),
    BROKEN(8,"已损坏"),
    ;

    private Integer code;

    private String status;

    public Integer getCode(){
        return code;
    }

    public String getStatus(){
        return status;
    }

    MachineStatusEnum(Integer code,String status){
        this.code = code;
        this.status = status;
    }


}
