// This file is auto-generated, don't edit it. Thanks.
package com.sales.im.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.sales.im.domain.entry.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleSendSmsUtil {

    @Autowired
    private SampleSendSmsUtil sampleSendSmsUtil;

    @Value("${aliyun.AccessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.AccessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.singName}")
    private  String singName;

    @Value("${aliyun.sms.templateCode}")
    private  String templateCode;

    @Value("${aliyun.notice.templateCode}")
    private String templateNotice;

    /**
     * 使用AK&SK初始化账号Client
     * @return Client
     * @throws Exception
     */
    public  com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public  void send(Sms sms) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = sampleSendSmsUtil.createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        if (!sms.getCode().isEmpty()) {
            sendSmsRequest
                    .setSignName(singName)
                    .setTemplateCode(templateCode)
                    .setPhoneNumbers(sms.getPhone())
                    .setTemplateParam("{\"code\":\"" + sms.getCode() + "\"}");
        }else {
            sendSmsRequest
                    .setSignName(singName)
                    .setTemplateCode(templateNotice)
                    .setPhoneNumbers(sms.getPhone())
                    .setTemplateParam("{\"name\":\"" + sms.getName() + "\"}")
                    .setTemplateParam("{\"url\":\"" + sms.getUrl() + "\"}");
        }
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);
            log.info("结果是======》{}",response);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            error.printStackTrace();
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            error.printStackTrace();
        }
    }


}