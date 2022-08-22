package com.sales.file.domain.vo;

import com.sales.common.core.web.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Title:QueryFileListReq
 * @Desc: 查询分页查询文件列表
 * @Author: Pengwc
 * @Date: 2020-3-25 16:13
 */
@Data
@ApiModel(value = "文件列表查询实体")
public class QueryFileListReq extends PageRequest {
    @ApiModelProperty(value = "文件名")
    private String name;
    @ApiModelProperty(value = "文件 创建 开始时间")
    private Date beginTime;
    @ApiModelProperty(value = "文件 创建 结束时间")
    private Date endTime;
    @ApiModelProperty(value = "文件名模糊搜索")
    private String searchKey;

}
