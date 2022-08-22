package com.sales.im.utils;

import com.sales.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/22 22:17
 */
public class SensitiveWordInitUtil {
    @Autowired
    RedisService  redisService;
    // 字符编码
    private String ENCODING = "UTF-8";

    // 初始化敏感字库
    public Map initKeyWord() {
        // 读取敏感词库 ,存入Set中
        redisService.getCacheSet("SENSITIVE_WORD");
        Set<String> wordSet = readSensitiveWordFile();
        // 将敏感词库加入到HashMap中//确定有穷自动机DFA
        return addSensitiveWordToHashMap(wordSet);
    }
    private Set<String> readSensitiveWordFile() {
        Set<String>  wordSet = new HashSet<String>();
        wordSet.add("微信");
        wordSet.add("QQ");
        wordSet.add("做爱");
        wordSet.add("约炮");
        wordSet.add("中");
        wordSet.add("国");
        wordSet.add("人");
        wordSet.add("坏");
        return wordSet;
    }


    /**
     * 初始化敏感词库，构建DFA算法模型
     *
     * @param wordSet 敏感词库
     */
    private Map addSensitiveWordToHashMap(Set<String> wordSet) {

        // 初始化敏感词容器，减少扩容操作
        Map wordMap = new HashMap(wordSet.size());
        for (String word : wordSet) {
            Map nowMap = wordMap;
            for (int i = 0; i < word.length(); i++) {
                // 转换成char型
                char keyChar = word.charAt(i);
                // 获取
                Object tempMap = nowMap.get(keyChar);
                // 如果存在该key，直接赋值
                if (tempMap != null) {
                    nowMap = (Map) tempMap;
                } else {
                    // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    // 设置标志位
                    Map<String, String> newMap = new HashMap<String, String>();
                    newMap.put("isEnd", "0");
                    // 添加到集合
                    nowMap.put(keyChar, newMap);
                    nowMap = newMap;
                }
                // 最后一个
                if (i == word.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
        return wordMap;
    }


}