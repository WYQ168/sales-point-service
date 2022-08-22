package com.sales.im.yunxin.bean;

import lombok.Data;

@Data
public class GetToken {
    /**
     * 用户ID
     */
    private String uid;
    public GetToken(String uid){
        this.uid = uid;
    }
    public GetToken(){
    }
}
