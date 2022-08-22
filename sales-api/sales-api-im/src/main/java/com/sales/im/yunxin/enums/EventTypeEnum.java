package com.sales.im.yunxin.enums;
/**
 * @desc: 云信回调事件类型
 * @author wuyingqiang
 * @date 2022/8/3 21:11
 */
public enum EventTypeEnum {
    P2P_MSG(1, "P2P消息回调"),GROUP_MSG(2, "群组消息回调"),USER_UPDATE(3, "用户资料变更回调"),FRIEND_ADD(4, "添加好友回调"),FRIEND_DEL(5, "删除好友回调"),
    ROOM_MSG(6, "聊天室消息回调"), GROUP_ADD(7, "创建群回调"),GROUP_DEL(8, "解散群回调"),GROUP_INVITE(9, "群邀请回调"),GROUP_QUIT(10, "退群回调"),
    GROUP_ADD_MANAGE(11, "增加群管理员回调"),GROUP_CANCEL_MANAGE(12, "取消群管理员回调"), GROUP_TRANSFER(13, "转让群回调"),GROUP_KICK_OUT(14, "踢人出群回调"),GROUP_UPDATE(15, "更新群信息回调"),
    GROUP_UPDATE_USER(16, "更新群成员信息回调"), GROUP_UPDATE_OTHER_USER(17, "更新其他人的群成员信息回调"),GROUP_FORBIDDEN(18, "禁言群成员回调"),GROUP_APPLY(19, "申请入群回调"),VV_CALL(20, "音视频呼叫回调"),
    VV_MEETING_ADD(21, "音视频会议创建回调"), SUPER_GROUP_MSG(22, "超大群消息回调"),SUPER_GROUP_INVITE(23, "超大群群邀请回调"),SUPER_GROUP_KICK_OUT(24, "超大群踢人出群回调"),SUPER_GROUP_QUIT(25, "超大群退群回调"),
    SUPER_GROUP_UPDATE(26, "更新超大群群信息回调"), SUPER_GROUP_UPDATE_USER(27, "更新超大群群成员信息回调"),SUPER_GROUP_APPLY(28, "超大群申请入群回调"),SUPER_GROUP_ADD_MANAGE(29, "增加超大群管理员回调"),SUPER_GROUP_CANCEL_MANAGE(30, "取消超大群管理员回调"),
    SUPER_GROUP_FORBIDDEN(31, "禁言超大群回调"),SUPER_GROUP_FORBIDDEN_USER(32, "禁言超大群群成员回调"),SUPER_GROUP_UPDATE_OTHER_USER(33, "更新超大群里其他人的群成员信息回调"),SUPER_GROUP_TRANSFER(34, "转让超大群回调"),MSG_RECALL(35, "消息撤回回调"),
    LOGIN(36, "登录回调");

    private Integer value;
    private String desc;
    EventTypeEnum(Integer value,String desc){
        this.value=value;
        this.desc=desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
