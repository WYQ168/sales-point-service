package com.sales.im.yunxin.bean.msgbody;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/26 15:16
 */
@Data
public class TextMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    String msg;
}
