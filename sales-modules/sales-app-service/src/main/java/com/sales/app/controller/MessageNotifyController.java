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
     * 《绑机消息推送》
     *
     * @param body 绑机记录信息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushBindingMachine")
    public Object pushBindingMachine(@RequestBody(required = false) String body) {
        String bodyEg = "{ \"possn\" : \"LD188FBMS78888\",\n" +
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

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《押金/服务费推送》
     *
     * @param body 押金/服务费信息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushDepositServiceFee")
    public Object pushDepositServiceFee(@RequestBody(required = false) String body) {
        log.info("收到押金/服务费信息推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        //asyncBindingNotice(body);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《购买会员消息推送》
     *
     * @param body 购买会员消息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushPurchaseMember")
    public Object pushPurchaseMember(@RequestBody(required = false) String body) {
        log.info("收到购买会员消息推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        //asyncBindingNotice(body);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《交易流水消息推送》
     *
     * @param body 交易流水消息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushTransactionFlow")
    public Object pushTransactionFlow(@RequestBody(required = false) String body) {
        log.info("收到交易流水消息推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        //asyncBindingNotice(body);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《通讯费推送》
     *
     * @param body 通讯费推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushCommunicationFee")
    public Object pushCommunicationFee(@RequestBody(required = false) String body) {
        log.info("收到通讯费推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        //asyncBindingNotice(body);
        // 返回接收成功，失败的响应消息可自定义, 务必按照文档约定的格式返回
        Response resp = new Response();
        resp.setRetCode("00");
        resp.setRetMsg("SUCCESS");
        return resp;
    }

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
     * 《提现消息推送》
     *
     * @param body 提现消息推送
     * @return 约定的返回内容
     */
    @PostMapping(value = "/pushWithdraw")
    public Object pushWithdraw(@RequestBody(required = false) String body) {
        log.info("收到通讯费推送: {}", body);

        // 反序列化消息
        Map<String, Object> msg = deserialize(body);
        System.out.println(msg);
        //建议异步处理业务逻辑, 这样不影响后续消息的推送
        //asyncBindingNotice(body);
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
            sysConfig.setValue(message);
            sysConfig.setDescription("绑机消息推送");
            sysConfig.setOutsideAccess(0);
            sysConfig.setCreateTime(new Date());
            //保存到系统配置
            sysConfigService.insertSysConfig(sysConfig);

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
