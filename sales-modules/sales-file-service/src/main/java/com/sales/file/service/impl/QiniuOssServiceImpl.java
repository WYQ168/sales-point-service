package com.sales.file.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.sales.file.domain.FileInfo;
import com.sales.file.model.UploadManager;
import com.sales.file.properties.FileServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/26 19:52
 */
@Service
public class QiniuOssServiceImpl extends AbstractFileServiceImpl {
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;
    @Autowired
    FileServerProperties fileProperties;
    @Override
    protected String fileType() {
        return fileProperties.getType();
    }

    @Override
    protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
        // 调用put方法上传
        uploadManager.put(file.getBytes(), fileInfo.getFileName(), auth.uploadToken(fileProperties.getOss().getBucketName()));
        fileInfo.setFileUrl(fileProperties.getOss().getEndpoint() + "/" + fileInfo.getFileName());
    }

    @Override
    protected boolean deleteFile(FileInfo fileInfo) {
        try {
            Response response =  bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getFileUrl());
            int retry = 0;
            while (response.needRetry() && retry++ < 3) {
                response = bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getFileUrl());
            }
        } catch (QiniuException e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean download(String fileUrl, HttpServletResponse response) throws Exception {
        return false;
    }
}
