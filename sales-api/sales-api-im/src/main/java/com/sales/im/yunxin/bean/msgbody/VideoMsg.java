package com.sales.im.yunxin.bean.msgbody;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/26 15:19
 */
@Data
public class VideoMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private   String dur;
    private String md5;
    private String url;
    private String ext;
    private Integer size;
}