package com.sales.auth.enums;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/13 16:53
 */
public enum LoginType {
    PASSWORD("password","账户+密码方式"),SMS_CODE("smsCode","手机号+短信验证码方式"),WECHAT("wechat","第三方微信方式");
    String desc;
    String code;
     LoginType(String code,String desc){
        this.desc=desc;
        this.code=code;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
    /**
     * 判断是否支持的登陆方式
     * @param code
     * @return
     */
    public static boolean isInclude(String code){
        boolean include = false;
        for (LoginType type: LoginType.values()){
            if(type.getCode().equals(code)){
                include = true;
                break;
            }
        }
        return include;
    }
}
