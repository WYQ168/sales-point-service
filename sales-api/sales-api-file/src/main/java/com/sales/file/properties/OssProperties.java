package com.sales.file.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Title:FdfsProperties
 * @Desc: Oss配置类
 * @Author: Pengwc
 * @Date: 2020-3-25 15:50
 */
@Getter
@Setter
public class OssProperties {
    /**
     * 密钥key
     */
    private String accessKey;
    /**
     * 密钥密码
     */
    private String accessKeySecret;
    /**
     * 端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 公共访问空间
     */
    private String publicPoint;
    /**
     * 说明
     */
    private String domain;
}
