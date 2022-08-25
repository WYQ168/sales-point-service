package com.sales.system.api.factory;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.RemoteSysUserService;
import com.sales.system.api.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author sales
 */
@Component
public class RemoteSysUserFallbackFactory implements FallbackFactory<RemoteSysUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteSysUserFallbackFactory.class);

    @Override
    public RemoteSysUserService create(final Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteSysUserService()
        {
            @Override
            public BaseResult<JSONObject> getSysUserInfo(String username, String source)
            {
                return BaseResult.fail("获取系统用户失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<Boolean> registerSysUserInfo(SysUser sysUser, String source)
            {
                return BaseResult.fail("注册系统用户失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<Boolean> checkSysUserPhone(String phone, String inner) {
                return BaseResult.fail("校验用户手机号失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<Boolean> modifySysUserPassWord(SysUser sysUser, String inner) {
                return BaseResult.fail("修改用户密码失败:" + throwable.getMessage());
            }
        };
    }
}
