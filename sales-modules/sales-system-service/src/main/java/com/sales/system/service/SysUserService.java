package com.sales.system.service;

import com.sales.system.domain.entity.SysUser;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 系统用户 服务层
 * 
 * @author zhangtailong
 * @date 2022-08-24
 */
public interface SysUserService
{

	/**
	 * 获取系统用户登录信息
	 *
	 * @param userName
	 * @return
	 */
	JSONObject getSysUserInfo(String userName);

	
}
