package com.sales.file.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title:FdfsProperties
 * @Desc: fdfs配置类
 * @Author: Pengwc
 * @Date: 2020-3-25 15:50
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sales.file-server")
public class FileServerProperties {
    /**
     * 为以下3个值，指定不同的自动化配置
     * qiniu：七牛oss
     * aliyun：阿里云oss
     * fastdfs：本地部署的fastDFS
     */
    private String type;
    /**
     * oss配置
     */
    OssProperties oss = new OssProperties();
    /**
     * fastDFS配置
     */
    FdfsProperties fdfs = new FdfsProperties();
}
