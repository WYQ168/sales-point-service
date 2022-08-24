package com.sales.app.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AliPayConstants {

    /**
     * AppId
     */
    @Value("${alipay.app_id}")
    private String appId;

    /**
     * 私钥
     */
    @Value("${alipay.rsa_private_key}")
    private String privateKey;

    /**
     * 公钥
     */
    @Value("${alipay.alipay_public_key}")
    private String publicKey;

    /**
     * 回调地址
     */
    @Value("${alipay.notify_url}")
    private String notifyUrl;

    /**
     * 服务url
     */
    @Value("${alipay.gateway_url}")
    private String serverUrl;

    /**
     * 加密算法
     */
    private String algorithm = "RSA2";

    /**
     * 支付白名单
     */
    private String systemPayWhiteCode = "pay_white_users_system";

}
