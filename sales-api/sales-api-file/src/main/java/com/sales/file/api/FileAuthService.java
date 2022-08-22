package com.sales.file.api;

import com.qiniu.util.Auth;
import com.sales.common.core.utils.StringUtils;
import com.sales.file.config.QiniuOSSAutoConfigure;
import com.sales.file.properties.FileServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/26 20:09
 */
@Service
@ConditionalOnClass(QiniuOSSAutoConfigure.class)
public class FileAuthService {

    @Autowired(required=false)
    FileServerProperties fileProperties;

    @Autowired(required=false)
    Auth auth;

    /**
     * @desc: 获取上传临时令牌
     */
    public String getUpToken(){
        return  auth.uploadToken(fileProperties.getOss().getBucketName());
    }
    /**
     * @desc: 访问的URL授权
     */
    public String getPrivateDownloadUrl(String url){
        return  auth.privateDownloadUrl(url);
    }

    /**
     * 获取公共访问空间
     */
    public String getPublicDownloadUrlByDirName(String dirName,String mediaName){
        if(StringUtils.isEmpty(mediaName)){
            return "";
        }
        String encodedFileName = "";
        try {
            encodedFileName = URLEncoder.encode(mediaName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("%s/%s/%s", "http://"+fileProperties.getOss().getPublicPoint(), dirName,encodedFileName);
    }
    /**
     * @desc: 通过文件名获取私有访问链接
     */
    public String getPrivateDownloadUrlByKey(String mediaName){
        if(StringUtils.isEmpty(mediaName)){
            return "";
        }
        String encodedFileName = "";
        try {
            encodedFileName = URLEncoder.encode(mediaName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", "http://"+fileProperties.getOss().getEndpoint(), encodedFileName);
        return  auth.privateDownloadUrl(publicUrl);
    }
}
