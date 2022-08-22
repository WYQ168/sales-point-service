package com.sales.common.core.constant;

/**
 * Token的Key常量
 * 
 * @author sales
 */
public class TokenConstants
{
    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * web端令牌秘钥
     */
    public final static String WEB_TOKEN_SECRET = "ENATUXIwZZy3rezfzdqO";
    /**
     * 移动端令牌秘钥
     */
    public final static String APP_TOKEN_SECRET = "VtHeFvucfLnmbQPnHIHo";
    /**
     * Token风格: uuid
     */
    public static final String TOKEN_STYLE_UUID = "uuid";

    /**
     * Token风格: 简单uuid (不带下划线)
     */
    public static final String TOKEN_STYLE_SIMPLE_UUID = "simple-uuid";

    /**
     * Token风格: 32位随机字符串
     */
    public static final String TOKEN_STYLE_RANDOM_32 = "random-32";

    /**
     * Token风格: 64位随机字符串
     */
    public static final String TOKEN_STYLE_RANDOM_64 = "random-64";

    /**
     * Token风格: 128位随机字符串
     */
    public static final String TOKEN_STYLE_RANDOM_128 = "random-128";


}
