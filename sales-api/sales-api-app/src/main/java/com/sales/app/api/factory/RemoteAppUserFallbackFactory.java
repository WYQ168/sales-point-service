package com.sales.app.api.factory;

import com.sales.app.api.RemoteAppUserService;
import com.sales.app.api.domain.AppUser;
import com.sales.common.core.domain.BaseResult;
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
public class RemoteAppUserFallbackFactory implements FallbackFactory<RemoteAppUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteAppUserFallbackFactory.class);

    @Override
    public RemoteAppUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteAppUserService()
        {

            @Override
            public BaseResult<AppUser> getAppUserInfo(String userName, String source) {
                return BaseResult.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<AppUser> registerUser(AppUser appUser, String source) {
                return BaseResult.fail("用户注册失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<Boolean> checkAppUserPhone(String phone, String inner) {
                return BaseResult.fail("该手机号不存在:" + throwable.getMessage());
            }

        };
    }
}
