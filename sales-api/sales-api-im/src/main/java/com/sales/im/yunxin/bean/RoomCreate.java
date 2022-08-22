package com.sales.im.yunxin.bean;

import lombok.Data;

@Data
public class RoomCreate {
    /**
     *房间名称
     */
    private String channelName;
    /**
     * 模式2
     */
    private int mode=2;
    /**
     * 用户ID
     */
    private Long uid;


    public RoomCreate(String channelName, Long uid){
        this.channelName = channelName;
        this.uid = uid;
    }
    public RoomCreate(){

    }
}
