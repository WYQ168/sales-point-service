package com.sales.common.security.auth;

import lombok.Data;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/13 15:28
 */
@Data
public class AuthTokenInfo {
    /**
     * 用户访问令牌
     */
    private String accessToken;
    /**
     * 令牌有效期
     */
    private Long expiresIn;
    /**
     * 云信ID
     */
    private String accid;
    /**
     * 是否为新用户第一次登陆
     */
    private boolean completedBaseInfo;
}
