package com.sales.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sales.common.core.annotation.CreateBy;
import com.sales.common.core.annotation.CreateTime;
import com.sales.common.core.annotation.UpdateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 *
 * @author sales
 */
public class CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @CreateBy
    private Long createBy;
    /**
     * 创建时间
     */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新者
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
