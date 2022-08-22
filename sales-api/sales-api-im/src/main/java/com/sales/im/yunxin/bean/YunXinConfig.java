package com.sales.im.yunxin.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @desc: 云信IM配置
 * @author wuyingqiang
 * @date 2022/7/4 17:41
 */
@RefreshScope
@Component
@Configuration
@Data
@ConfigurationProperties(prefix = "im.yunxin")
public class YunXinConfig {
    private String appKey;
    private String appSecret;
    private String nonce;
    private String curTime;
    private String checkSum;
}
