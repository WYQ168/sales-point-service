package com.sales.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileEntityService {

    /**
     * 上传文件
     *
     * @param file       文件
     * @param fileType   文件类型 1-合同 2-banner 3-签名文件 4-印章
     * @param uploadType 上传类型 1-个人用户 2-公司用户
     * @return
     */
    Map<String, String> uploadFile(MultipartFile file, Integer fileType, Integer uploadType) throws IOException;

    Map<String, String>uploadToFDDseal();

}
