package com.sales.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sales.file.domain.FileInfo;
import com.sales.file.domain.vo.FileInfoVo;
import com.sales.file.domain.vo.QueryFileListReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuyingqiang
 * @since 2020-03-25
 */
@Repository
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    /**
     * 查询【请填写功能名称】列表
     *
     * @param fileInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFileInfoByIds(String[] ids);

    int deleteByPrimaryKey(String fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
    List<FileInfoVo> findList(@Param("f") QueryFileListReq f);
    List<FileInfo> selectFileInfoListByUrls(@Param("urls")List<String > urls);
}
