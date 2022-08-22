package com.sales.system.api;

import com.sales.common.core.constant.ServiceNameConstants;
import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.domain.SysFile;
import com.sales.system.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author sales
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResult<SysFile> upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * oss文件上传
     *
     * @param file       文件信息
     * @param fileType   文件类型 1-合同 2-banner 3-签名文件 4-印章
     * @param uploadType 上传类型 1-个人用户 2-公司用户
     * @return
     */
    @PostMapping(value = "/fileApi/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResult<?> uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam("fileType") Integer fileType, @RequestParam("uploadType") Integer uploadType);

}
