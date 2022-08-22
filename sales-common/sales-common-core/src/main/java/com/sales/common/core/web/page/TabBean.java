package com.sales.common.core.web.page;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * tab信息
 *
 * @author zhongxiaojian
 * @date 2020/12/14
 **/
@Data
@Accessors(chain = true)
public class TabBean {
    private Object num;
    private String label;
    private Object value;
}
