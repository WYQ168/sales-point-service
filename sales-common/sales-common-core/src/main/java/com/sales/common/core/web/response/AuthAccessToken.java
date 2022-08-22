package com.sales.common.core.web.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title:AuthAccessToken
 * @Desc: 令牌对象
 * @Author: wuyingqiang
 * @Date: 2020-4-26 18:27
 */
@Data
public class AuthAccessToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

}
