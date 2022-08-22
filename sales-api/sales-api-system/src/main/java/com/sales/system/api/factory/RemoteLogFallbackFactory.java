package com.sales.system.api.factory;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.RemoteLogService;
import com.sales.system.api.domain.SysLoginInfo;
import com.sales.system.api.domain.SysOperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 * 
 * @author sales
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public BaseResult<Boolean> saveLog(SysOperLog sysOperLog, String source)
            {
                return null;
            }

            @Override
            public BaseResult<Boolean> saveLogininfor(SysLoginInfo sysLoginInfo, String source)
            {
                return null;
            }
        };

    }
}
