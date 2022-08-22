package com.sales.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.sales.app.api.RemoteAppUserService;
import com.sales.system.api.domain.AppUser;
import com.sales.auth.enums.LoginType;
import com.sales.auth.form.LoginBody;
import com.sales.auth.form.RegisterBody;
import com.sales.common.core.constant.Constants;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.domain.BaseResult;
import com.sales.common.core.enums.TokenStyleEnum;
import com.sales.common.core.enums.UserStatus;
import com.sales.common.core.enums.UserType;
import com.sales.common.core.exception.ServiceException;
import com.sales.common.core.utils.DateUtils;
import com.sales.common.core.utils.ServletUtils;
import com.sales.common.core.utils.StringUtils;
import com.sales.common.core.utils.TokenUtils;
import com.sales.common.core.utils.ip.IpUtils;
import com.sales.common.redis.service.RedisService;
import com.sales.common.security.auth.AuthUserType;
import com.sales.common.security.utils.SecurityUtils;
import com.sales.im.yunxin.api.AuthApi;
import com.sales.im.yunxin.bean.UpdateToken;
import com.sales.im.yunxin.bean.YunXinConfig;
import com.sales.im.yunxin.bean.YunXinUser;
import com.sales.system.api.RemoteLogService;
import com.sales.system.api.domain.SysLoginInfo;
import com.sales.system.api.domain.request.PassWordModifyReq;
import com.sales.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * APP用户登录注册方法
 *
 * @author sales
 */
@Slf4j
@Component
public class AppLoginService implements BaseLogin {
	/**
	 * 登陆用户类型
	 */
	private static final String userType = AuthUserType.MOBILE.getType();

	@Autowired
	private RemoteAppUserService remoteAppUserService;

	@Autowired
	YunXinConfig yunXinConfig;

	@Autowired
	private RedisService redisService;

	@Autowired
	private RemoteLogService remoteLogService;

	@Override
	public LoginUser login(LoginBody loginBody) {
		String username = loginBody.getUsername();
		LoginUser loginUser = new LoginUser();
		// 查询用户信息
		BaseResult<com.sales.app.api.domain.AppUser> userResult = remoteAppUserService.getAppUserInfo(loginUser.getUsername(), SecurityConstants.INNER);
		if (BaseResult.FAIL == userResult.getCode()) {
			throw new ServiceException(userResult.getMsg());
		}
		// 生成token
		String token = TokenUtils.createToken(TokenStyleEnum.RANDOM_128.getTypeName());
		// 返回空内容说明该用户未注册
		if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
			throw new ServiceException("登录用户：" + username + " 不存在");
		}
		com.sales.app.api.domain.AppUser userInfo = userResult.getData();
		// 新用户注册
		if (BaseResult.SUCCESS == userResult.getCode() && userInfo == null) {
			throw new ServiceException("该用户未注册，请注册后登录!");
		}
		// 登陆用户信息转换
		com.sales.app.api.domain.AppUser user = userInfo;

		convertAppLoginUser(loginUser, user, token);
		if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
			throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
		}
		if (!SecurityUtils.matchesPassword(loginBody.getPassword(), user.getPassword())) {
			throw new ServiceException("用户不存在/密码错误");
		}
		recordLoginInfo(username, Constants.LOGIN_SUCCESS, "登录成功");

		return loginUser;
	}

	@Override
	public String getUserType() {
		return userType;
	}

	@Override
	public String getLoginType() {
		return LoginType.SMS_CODE.getCode();
	}

	/**
	 * @param loginUser:
	 * @param user:
	 * @param token:
	 * @return void
	 * @desc: app用户转换登陆用户信息
	 * @author wuyingqiang
	 * @date 2022/8/3 16:20
	 */
	private void convertAppLoginUser(LoginUser loginUser, com.sales.app.api.domain.AppUser user, String token) {
		loginUser.setUserType(getUserType());
		loginUser.setUserid(user.getUserId());
		if (StringUtils.isNotEmpty(user.getUserName())) {
			loginUser.setUsername(user.getUserName());
		}
		if (StringUtils.isNotEmpty(user.getUserName())) {
			loginUser.setCompletedBaseInfo(true);
		} else {
			loginUser.setCompletedBaseInfo(false);
		}
		loginUser.setToken(token);
	}

	/**
	 * @param accid:
	 * @param token:
	 * @return java.lang.String
	 * @desc: 云信平台用户注册
	 * @author wuyingqiang
	 * @date 2022/7/13 16:06
	 */
	private String yunxinUserRegister(String accid, String token) {
		// 注册云信平台
		YunXinUser yunXinUser = new YunXinUser();
		yunXinUser.setAccid(accid);
		yunXinUser.setToken(token);
		String result = "";
		try {
			result = AuthApi.createUser(yunXinUser, yunXinConfig);
			log.info("注册云信用户返回结果:{}" + result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(result)) {
			throw new ServiceException("用户登陆失败，请重新尝试");
		}
		return result;
	}

	/**
	 * @param accid:
	 * @param token:
	 * @return void
	 * @desc: 云信令牌更新
	 * @author wuyingqiang
	 * @date 2022/7/13 16:20
	 */
	private void updateYunxinToken(String accid, String token) {
		// 更新用户令牌
		UpdateToken updateToken = new UpdateToken(accid, "", token);
		try {
			String resultJson = AuthApi.updateToken(updateToken, yunXinConfig);
			log.info("云信令牌更新结果:{}", resultJson);
			JSONObject updateResult = JSONObject.parseObject(resultJson);
			// 用户未注册云信
			if (updateResult.getInteger("code") == 414) {
				// 用户不存在，重新注册云信
				String result = yunxinUserRegister(accid, token);
				if (StringUtils.isEmpty(result)) {
					throw new ServiceException("用户登陆失败，请重新尝试");
				}
			}

		} catch (IOException e) {
			throw new ServiceException("用户登陆失败，请重新尝试");
		} catch (NoSuchMethodException e) {
			throw new ServiceException("用户登陆失败，请重新尝试");
		} catch (IllegalAccessException e) {
			throw new ServiceException("用户登陆失败，请重新尝试");
		} catch (InvocationTargetException e) {
			throw new ServiceException("用户登陆失败，请重新尝试");
		}
	}

	/**
	 * 新用户注册
	 */
	public void register(RegisterBody registerBody) {
		if (!registerBody.getPassword().equals(registerBody.getPassword2())) {
			throw new ServiceException("两次输入的密码不一致");
		}
		// 注册用户信息
		com.sales.app.api.domain.AppUser appUser = new com.sales.app.api.domain.AppUser();
		appUser.setPhoneMember(registerBody.getPhone());
		appUser.setPassword(registerBody.getPassword());
		appUser.setRegisterTime(new Date());
		// 保存注册用户信息
		BaseResult<?> registerResult = remoteAppUserService.registerUser(appUser, SecurityConstants.INNER);

		if (BaseResult.FAIL == registerResult.getCode()) {
			throw new ServiceException(registerResult.getMsg());
		}
		recordLoginInfo(appUser.getUserName(), Constants.REGISTER, "注册成功");
	}

	/**
	 * 记录登录信息
	 *
	 * @param username 用户名
	 * @param status   状态
	 * @param message  消息内容
	 * @return
	 */
	private void recordLoginInfo(String username, String status, String message) {
		SysLoginInfo logininfor = new SysLoginInfo();
		logininfor.setUserName(UserType.SYSTEM.getType() + ":" + username);
		logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
		logininfor.setMsg(message);
		logininfor.setAccessTime(DateUtils.getNowDate());
		// 日志状态
		if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
			logininfor.setStatus("0");
		} else if (Constants.LOGIN_FAIL.equals(status)) {
			logininfor.setStatus("1");
		}
		log.info(logininfor.toString());
		// remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
	}

	public Boolean checkPhone(String phone) {
		// 查询用户信息
		BaseResult<Boolean> userResult = remoteAppUserService.checkAppUserPhone(phone, SecurityConstants.INNER);
		if (BaseResult.FAIL == userResult.getCode()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}