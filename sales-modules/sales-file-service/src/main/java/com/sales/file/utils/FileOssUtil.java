package com.sales.file.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.sales.common.core.text.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileOssUtil {

    @Value("${aliyun.AccessKey}")
    private String accessKey;

    @Value("${aliyun.AccessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        //创建OSSClient实例
        OSS ossClient = new OSSClient(endpoint, accessKey, accessKeySecret);
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + fileName;
        ossClient.putObject(bucketName, fileName, inputStream);
        String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
        ossClient.shutdown();
        return url;
    }

}
