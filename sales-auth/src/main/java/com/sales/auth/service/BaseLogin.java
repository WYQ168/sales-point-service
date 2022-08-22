package com.sales.auth.service;

import com.sales.auth.form.LoginBody;
import com.sales.system.api.model.LoginUser;

/**
 * @desc: 登陆授权接口定义
 * @author wuyingqiang
 * @date 2022/7/13 16:25
 */
public interface BaseLogin {
    LoginUser login(LoginBody loginBody);
    String getUserType();
    String getLoginType();
}
