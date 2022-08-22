package com.sales.common.core.constant;

/**
 * 缓存的key 常量
 * 
 * @author sales
 */
public class CacheConstants
{
    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 60;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * APP权限缓存前缀
     */
    public final static String APP_LOGIN_TOKEN_KEY = "app_login_tokens:";

    /**
     * 用户收费数据缓存key
     */
    public final static String USER_SETTING_CHARGE_KEY = "user_setting:charge:";

    /**
     * 人脸认证用户信息缓存key
     */
    public final static String VERIFY_TOKEN_KEY = "verify_token:";

}
