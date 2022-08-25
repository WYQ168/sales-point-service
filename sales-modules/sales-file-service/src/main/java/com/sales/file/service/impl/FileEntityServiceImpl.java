package com.sales.file.service.impl;

import com.sales.common.core.text.UUID;
import com.sales.common.security.utils.SecurityUtils;
import com.sales.file.domain.FileInfo;
import com.sales.file.mapper.FileInfoMapper;
import com.sales.file.service.FileEntityService;
import com.sales.file.utils.FileOssUtil;
import com.sales.system.api.domain.AppUser;
import com.sales.system.api.domain.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class FileEntityServiceImpl implements FileEntityService {

    private final FileInfoMapper fileInfoMapper;
    private final FileOssUtil fileOssUtil;

    @Override
    public Map<String, String> uploadFile(MultipartFile file, Integer fileType, Integer uploadType) throws IOException {
        String fileUrl = fileOssUtil.uploadFile(file);
        Long userId = null;
        //个人用户
        if (uploadType == 1) {
            AppUser user = SecurityUtils.getUserData();
            userId = user.getUserId();
        } else {
            SysUser sysUser = SecurityUtils.getSysUserData();
            userId = sysUser.getSysUserId();
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(UUID.randomUUID().toString().replaceAll("-", ""));
        fileInfo.setFileName(file.getOriginalFilename());
        fileInfo.setType(fileType);
        fileInfo.setFileUrl(fileUrl);
        fileInfo.setUploadPort(uploadType);
        fileInfo.setDelFlag(0);//未删除
        fileInfo.setCreateBy(userId);
        fileInfo.setCreateTime(new Date());
        fileInfoMapper.insert(fileInfo);
        Map<String, String> voMap = new HashMap<>();
        voMap.put("url", fileUrl);
        voMap.put("fileId", fileInfo.getFileId());
        return voMap;
    }

    @Override
    public Map<String, String> uploadToFDDseal() {
        // FddBaseClient baseClient = new FddBaseClient(APPID, APPKEY, V, HOST);
        // AddSignatureParams params = new AddSignatureParams();
        // params.setCustomerId("此处填入实名认证通过后的客户编号"); //客户编号
        // //下面章图片base64、签章图片、图片公网地址三选一
        // params.setSignatureImgBase64(""); //签章图片base64
        // params.setFile(new File("D:\\sign.png")); //签章图片
        // params.setImgUrl(""); //签章图片公网地址
        // String result = baseClient.invokeAddSignature(params);
        // System.out.println(result);
        return null;

    }

}
