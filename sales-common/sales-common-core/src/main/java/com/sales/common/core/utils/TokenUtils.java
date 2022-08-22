package com.sales.common.core.utils;

import com.sales.common.core.constant.TokenConstants;
import com.sales.common.core.enums.TokenStyleEnum;

import java.util.UUID;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/11 17:24
 */
public class TokenUtils {
    public static String secret = TokenConstants.WEB_TOKEN_SECRET;
    /**
     * 从数据声明生成令牌
     * @param tokenStyle 令牌生成类型
     * @return 令牌
     */
    public static String createToken(String tokenStyle)
    {
        // uuid
        if(TokenStyleEnum.UUID.getTypeName().equals(tokenStyle)) {
            return UUID.randomUUID().toString();
        }
        // 简单uuid (不带下划线)
        if(TokenStyleEnum.SIMPLE_UUID.getTypeName().equals(tokenStyle)) {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
        // 32位随机字符串
        if(TokenStyleEnum.RANDOM_32.getTypeName().equals(tokenStyle)) {
            return RandomStrUtil.getRandomString(32);
        }
        // 64位随机字符串
        if(TokenStyleEnum.RANDOM_64.getTypeName().equals(tokenStyle)) {
            return RandomStrUtil.getRandomString(64);
        }
        // 128位随机字符串
        if(TokenStyleEnum.RANDOM_128.getTypeName().equals(tokenStyle)) {
            return RandomStrUtil.getRandomString(128);
        }
        // 默认uuid
        return UUID.randomUUID().toString();
    }

}
