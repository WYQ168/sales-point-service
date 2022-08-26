package com.sales.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.app.domain.entity.SysConfig;
import com.sales.app.service.ISysConfigService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 机构消息推送接收 控制器
 *
 * @author 嘉联支付
 * @date 16:10 2020/10/15
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageNotifyController {


    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《交易流水消息推送》
     *
     * @param body 消息流水
     * @return 约定的返回内容
     */
    @PostMapping(value = "/notify")
    public Object notify(@RequestBody(required = false) String body) {
        log.info("收到消息推送: {}", body);
        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        // 建议异步处理业务逻辑, 这样不影响后续消息的推送
        asyncHandle(msg);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }


    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《绑机消息推送》
     *
     * @param bodys 绑机记录信息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/getBindingNotice")
    public Object getBindingNotice(@RequestBody(required = false) String bodys) {
        String body = "{ \"possn\" : \"LD188FBMS78888\",\n" +
                "\"agentId\" : \"88880019\",\n" +
                "\"phone\" : \"135****6666\",\n" +
                "\"bindTime\" : \"2022-07-08 14:41:10\",\n" +
                "\"merchNo\" : \"M50000003529999\",\n" +
                "\"supplement_info\" : \"4307************48\",\n" +
                "\" NAME \" : \"李 ** \",\n" +
                "\" termNo \" : \" M4859999 \",\n" +
                "\"deviceModel\" : \"K318\",\n" +
                "\"sign\" : \" eDlxE0I7WFhjeLbFwVmpDCOV……Rd28e8GhuR9A == \" }";

        log.info("收到绑机消息推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        asyncBindingNotice(body);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }

    private void asyncBindingNotice(String message) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            log.info("异步处理任务");
            // TODO: 用接收到的消息流水， 实现业务
            System.out.println("绑机消息推送>>>>>" + message);
            //系统配置,
            SysConfig sysConfig = new SysConfig();
            sysConfig.setName("sys_binding_config");
            sysConfig.setValue(message.toString());
            sysConfig.setDescription("绑机消息推送");
            sysConfig.setOutsideAccess(0);
            sysConfig.setCreateTime(new Date());
            //保存到系统配置
            sysConfigService.insertSysConfig(sysConfig);



     /*       //app用户的实体类
            AppUser appUser = new AppUser();
            *//*
             *phone 商户手机号
             *name 小微商户名称
             *supplement_info 附加信息
             *agentId   服务商id
             *merchNo 商户号
             *possn 机身号
             *deviceModel 机型
             *termNo 终端号
             *bindTime 绑机时间
             * sign 签名
             * *//*
            appUser.setPhoneMember(msg.get("phone").toString());//商户手机号
            appUser.setLoginName(msg.get("name").toString());//小微商户名称
            appUser.setUserName(msg.get("name").toString());
            appUser.setIdentityMember(msg.get("supplement_info").toString());//附加信息
            //我的机具的实体类
            Machine machine =new Machine();*/


        });
    }

    private void asyncHandle(Map<String, Object> msg) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            log.info("异步处理任务");
            // TODO: 用接收到的消息流水， 实现业务
        });
    }

    private Map<String, Object> deserialize(String body) {
        // 这里用了jackson 的反序列化方式, 也可以选用其他反序列化方案, 如 阿里的fastjson
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(body, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("反序列化消息失败");
        }
    }

    @Data
    static class Response implements Serializable {
        private String retCode;
        private String retMsg;
    }
}
