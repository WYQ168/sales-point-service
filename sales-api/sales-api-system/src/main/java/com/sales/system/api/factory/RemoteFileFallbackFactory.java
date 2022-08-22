package com.sales.system.api.factory;


import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.RemoteFileService;
import com.sales.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 * 
 * @author sales
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(final Throwable throwable)
    {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService()
        {
            @Override
            public BaseResult<SysFile> upload(MultipartFile file)
            {
                return BaseResult.fail("上传文件失败:" + throwable.getMessage());
            }

            @Override
            public BaseResult<?> uploadFile(MultipartFile file, Integer fileType, Integer uploadType) {
                return null;
            }
        };
    }
}
