package com.sales.file.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.common.core.web.controller.BaseController;
import com.sales.file.service.FileEntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件请求处理
 *
 * @author sales
 */
@RestController
@RequestMapping("/fileApi")
@AllArgsConstructor
class FileInfoController extends BaseController {

    private FileEntityService fileEntityService;

    /**
     * 上传文件到oss服务
     */
    @RequestMapping(value = "/uploadFile")
    public BaseResult<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileType") Integer fileType, @RequestParam("uploadType") Integer uploadType) throws IOException {
        return BaseResult.ok(fileEntityService.uploadFile(file, fileType, uploadType));
    }

}

