package com.sales.file.service;


import com.sales.file.domain.vo.FileInfoVo;
import com.sales.file.domain.vo.QueryFileListReq;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuyingqiang
 * @since 2020-03-25
 */
public interface FileInfoService {
    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    FileInfoVo upload(MultipartFile file) throws Exception;

    /**
     * 文件查找
     * @param req
     * @return
     */
    List<FileInfoVo> findList(QueryFileListReq req);

    /**
     * 文件删除
     * @param id
     */
    boolean delete(String id);
    boolean deleteByUrls(List<String> urls);
    /**
     * 文件下载
     * @return
     */
    boolean download(String fileUrl, HttpServletResponse response) throws Exception;
}
