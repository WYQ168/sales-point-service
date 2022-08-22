package com.sales.system.api;

import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.constant.ServiceNameConstants;
import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.domain.SysLoginInfo;
import com.sales.system.api.domain.SysOperLog;
import com.sales.system.api.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 日志服务
 *
 * @author sales
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @param source     请求来源
     * @return 结果
     */
    @PostMapping("/operlog")
    public BaseResult<Boolean> saveLog(@RequestBody SysOperLog sysOperLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param sysLoginInfo 访问实体
     * @param source        请求来源
     * @return 结果
     */
    @PostMapping("/logininfor")
    public BaseResult<Boolean> saveLogininfor(@RequestBody SysLoginInfo sysLoginInfo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
