package com.sales.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.sales.auth.enums.StatusType;
import com.sales.auth.form.LoginBody;
import com.sales.auth.form.RegisterBody;
import com.sales.common.core.constant.Constants;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.constant.UserConstants;
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
import com.sales.system.api.RemoteLogService;
import com.sales.system.api.RemoteSysUserService;
import com.sales.system.api.domain.SysLoginInfo;
import com.sales.system.api.domain.SysUser;
import com.sales.system.api.domain.request.PassWordModifyReq;
import com.sales.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author sales
 */
@Slf4j
@Component
public class SysLoginService implements BaseLogin {
    /**
     * 登陆用户类型
     */
    private static final String userType = AuthUserType.MANAGE.getType();
    @Autowired
    private RemoteLogService remoteLogService;
    @Autowired
    private RemoteSysUserService remoteSysUserService;

    @Autowired
    private RedisService redisService;

    @Override
    public LoginUser login(LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        // 查询用户信息
        BaseResult<JSONObject> userResult = remoteSysUserService.getSysUserInfo(username, SecurityConstants.INNER);
        if (BaseResult.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }
        // 返回空内容说明该用户未注册
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            recordLoginInfo(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        JSONObject userInfo = userResult.getData();
        JSONObject checkUserObject = userInfo.getJSONObject("user");
        // 新用户注册
        if (BaseResult.SUCCESS == userResult.getCode() && checkUserObject == null) {
            throw new ServiceException("该用户未注册，请注册后登录!");
        }
        // 登陆用户信息转换
        SysUser user = SysUser.builder()
                .sysUserId(checkUserObject.getLong("sysUserId"))
                .sysPermission(checkUserObject.getString("sysPermission"))
                .partnerLevel(checkUserObject.getInteger("partnerLevel"))
                .sysUsername(checkUserObject.getString("sysUsername"))
                .sysPassword(checkUserObject.getString("sysPassword"))
                .sysPhone(checkUserObject.getString("sysPhone"))
                .realName(checkUserObject.getString("realName"))
                .identityNumber(checkUserObject.getString("identityNumber"))
                .verifiedStatus(checkUserObject.getInteger("verifiedStatus"))
                .delFlag(checkUserObject.getInteger("delFlag"))
                .build();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            recordLoginInfo(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (!SecurityUtils.matchesPassword(password, user.getSysPassword())) {
            recordLoginInfo(username, Constants.LOGIN_FAIL, "用户密码错误");
            throw new ServiceException("用户不存在/密码错误");
        }
        // 生成token
        String token = TokenUtils.createToken(TokenStyleEnum.RANDOM_128.getTypeName());
        recordLoginInfo(username, Constants.LOGIN_SUCCESS, "登录成功");
        LoginUser loginUser = new LoginUser();
        loginUser.setSysUser(user);
        loginUser.setToken(token);
        loginUser.setUserid(user.getSysUserId());
        loginUser.setUserType(userInfo.getString("userType"));
        loginUser.setUsername(userInfo.getString("username"));
        loginUser.setLoginTime((Long) userInfo.get("loginTime"));
        return loginUser;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public String getLoginType() {
        return null;
    }

    /**
     * @param :
     * @return
     * @desc: 登陆参数校验
     * @author wuyingqiang
     * @date 2022/7/13 16:35
     */

    public void logout(String loginName) {
        recordLoginInfo(loginName, Constants.LOGOUT, "退出成功");
    }


    /**
     * 注册
     */
    /*public void register(RegisterBody registerBody) {
        if (!registerBody.getPassword().equals(registerBody.getPassword2())) {
            throw new ServiceException("两次输入的密码不一致");
        }
        // 注册系统用户信息
        SysUser sysUser = new SysUser();
        sysUser.setSysPhone(registerBody.getPhone());
        sysUser.setSysUsername(UserConstants.DZQZ_USER + NanoIdUtils.randomNanoId());
        sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
        sysUser.setEnabled(StatusType.ENABLE.getCode());
        sysUser.setAuthenticateFlag(StatusType.UNENABLE.getCode());
        // 保存用户信息
        BaseResult<?> registerResult = remoteSysUserService.registerSysUserInfo(sysUser, SecurityConstants.INNER);
        if (BaseResult.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLoginInfo(sysUser.getUsername(), Constants.REGISTER, "注册成功");
    }*/



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

    /**
     * 查询该手机号是否注册
     *
     * @param phone
     * @return
     */
    public Boolean checkPhone(String phone) {
        // 查询用户是否注册
        BaseResult<Boolean> userResult = remoteSysUserService.checkSysUserPhone(phone, SecurityConstants.INNER);
        if (BaseResult.FAIL == userResult.getCode()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 修改密码
     *
     * @param passWordModifyReq
     * @return
     */
/*
  public Boolean modifyPassWord(PassWordModifyReq passWordModifyReq) {
        // 判断短信验证码是否正确
        String resultString = redisService.getCacheObject(Constants.SMS_CODE_KEY + passWordModifyReq.getUuid());
        if (!resultString.equalsIgnoreCase(passWordModifyReq.getSmsCode())) {
            return Boolean.FALSE;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(passWordModifyReq, sysUser);
        sysUser.setPassword(SecurityUtils.encryptPassword(passWordModifyReq.getPassword()));
        // 查询用户是否注册
        BaseResult<Boolean> userResult = remoteSysUserService.modifySysUserPassWord(sysUser, SecurityConstants.INNER);
        if (BaseResult.FAIL == userResult.getCode()) {
            return Boolean.FALSE;
        }
        // 修改后删除redis中存储的短信验证码
        redisService.deleteObject(Constants.SMS_CODE_KEY + passWordModifyReq.getUuid());
        return Boolean.TRUE;
    }
*/

}
