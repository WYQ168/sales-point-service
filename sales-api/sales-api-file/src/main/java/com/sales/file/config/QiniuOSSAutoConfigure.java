package com.sales.file.config;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import com.sales.file.model.UploadManager;
import com.sales.file.properties.FileServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 作者 owen 
 * @version 创建时间：2018年1月31日 下午9:11:36 类说明 白名单
 * 七牛云配置
 */
@Configuration
@ConditionalOnProperty(name = "sales.file-server.type", havingValue = "qiniu")
public class QiniuOSSAutoConfigure {
    @Autowired
    private FileServerProperties fileProperties;
    /**
     * 华南机房
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return  new com.qiniu.storage.Configuration(Region.region2());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret());
    }
    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }

}
