package com.sales.file.service.impl;

import com.sales.common.core.exception.BusinessException;
import com.sales.common.core.utils.ConvertUtils;
import com.sales.file.enums.FileCodeEnum;
import com.sales.file.mapper.FileInfoMapper;
import com.sales.file.domain.FileInfo;
import com.sales.file.domain.vo.FileInfoVo;
import com.sales.file.domain.vo.QueryFileListReq;
import com.sales.file.service.FileInfoService;
import com.sales.file.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 作者 owen
 * @version 创建时间：2022年7月12日 上午22:57:51
 * AbstractFileServiceImpl 抽取类
 * 根据filetype 实例化具体oss对象
 */
@Slf4j
public abstract class AbstractFileServiceImpl  implements FileInfoService {

    private static final String FILE_SPLIT = ".";

    @Autowired
    FileInfoMapper fileInfoMapper;

    @Override
    public FileInfoVo upload(MultipartFile file) {
        FileInfo fileInfo = null;
        try {
            fileInfo = FileUtil.getFileInfo(file);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(FileCodeEnum.ERR_FILE_ID_NOT_EXEIST);
        }
        FileInfo oldFileInfo = fileInfoMapper.selectByPrimaryKey(fileInfo.getFileId());
        if (oldFileInfo != null) {
            return ConvertUtils.convertObject(oldFileInfo, FileInfoVo.class);
        }
        if (!fileInfo.getFileName().contains(FILE_SPLIT)) {
            throw new BusinessException(FileCodeEnum.ERR_FILE_SUFFIX_NOT_EXIST);
        }
        try {
            uploadFile(file, fileInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(FileCodeEnum.ERR_FILE_UPLOAD);
        }
        // 设置文件来源
        // fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
        fileInfoMapper.insertSelective(fileInfo);

        return ConvertUtils.convertObject(fileInfo, FileInfoVo.class);
    }

    /**
     * 文件类型
     *
     * @return
     */
    protected abstract String fileType();

    /**
     * 上传文件
     *
     * @param file
     * @param fileInfo
     */
    protected abstract void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception;

    /**
     * 删除文件
     *
     * @param id 文件id
     */
    @Override
    public boolean delete(String id) {
        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
        if (fileInfo != null) {
            fileInfoMapper.deleteByPrimaryKey(fileInfo.getFileId());
            return this.deleteFile(fileInfo);
        }
        return false;
    }

    @Override
    public boolean deleteByUrls(List<String> urls) {
        List<FileInfo> fileList = fileInfoMapper.selectFileInfoListByUrls( urls);
        if (fileList != null && fileList.size() > 0) {
            for (FileInfo fileInfo : fileList) {
                fileInfoMapper.deleteByPrimaryKey(fileInfo.getFileId());
                this.deleteFile(fileInfo);
            }
        }
        return true;
    }

    /**
     * 删除文件资源
     *
     * @param fileInfo
     * @return
     */
    protected abstract boolean deleteFile(FileInfo fileInfo);

    @Override
    public List<FileInfoVo> findList(QueryFileListReq req) {
        List<FileInfoVo> list =  fileInfoMapper.findList(req);
      return list;
    }
}
