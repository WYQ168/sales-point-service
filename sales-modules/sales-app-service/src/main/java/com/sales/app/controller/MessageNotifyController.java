package com.sales.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Serializable;
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

    /**
     * 嘉联支付会以 <code>HTTP POST</code>的方式, 用<code>content-type: application/json</code> 推送流水
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
        resp.setRetMsg("接收成功");
        return resp;
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
