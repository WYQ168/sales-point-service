package com.sales.app.utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.http.AipHttpClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.AipResponse;

import java.net.URLEncoder;
import com.sales.common.core.constant.CacheConstants;
import com.sales.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 识别工具类
 */
@Component
public class RecognitionUtil {

    @Value("${baidu.AccessKey}")
    private String accessKey;

    @Value("${baidu.SecretKey}")
    private String secretKey;

    @Value("${baidu.ScheheId}")
    private String scheheId;

    @Value("${baidu.AppId}")
    private String appId;

    @Value("${baidu.ApiKey}")
    private String apiKey;

    @Value("${baidu.ApiSecretKey}")
    private String apiSecretKey;

    @Autowired
    private RedisService redisService;

    private String getAccessToken() {
        String tokenUrl = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + apiKey + "&client_secret=" + apiSecretKey;
        AipRequest aipRequest = new AipRequest();
        aipRequest.addHeader("Content-Type", "application/json");
        aipRequest.setUri(tokenUrl);
        AipResponse response = AipHttpClient.post(aipRequest);
        JSONObject responseObject = JSONObject.parseObject(response.getBodyStr());
        return responseObject.getString("access_token");
    }

    private Map<String, String> getVerifyToken() {
        String token = getAccessToken();
        String requestUrl = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/verifyToken/generate?access_token=" + token;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("plan_id", scheheId);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject = restTemplate.postForObject(requestUrl, paramMap, JSONObject.class);
        JSONObject result = jsonObject.getJSONObject("result");
        Map<String, String> voMap = new HashMap<>();
        voMap.put("accessToken", token);
        voMap.put("verifyToken", result.getString("verify_token"));
        return voMap;
    }

    /**
     * 提交用户数据
     *
     * @param name 名字
     * @param code 身份证号
     * @return token信息
     */
    public Map<String, String> submitUserData(String name, String code) {
        Map<String, String> tokenMap = getVerifyToken();
        String requestUrl = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/idcard/submit?access_token=" + tokenMap.get("accessToken");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("verify_token", tokenMap.get("verifyToken"));
        paramMap.put("id_name", name);
        paramMap.put("id_no", code);
        JSONObject responseObject = restTemplate.postForObject(requestUrl, paramMap, JSONObject.class);
        String redisKey = CacheConstants.VERIFY_TOKEN_KEY + name + "-" + code;
        redisService.setCacheObject(redisKey, tokenMap.get("verifyToken"), 3L, TimeUnit.DAYS);
        return tokenMap;
    }

    /**
     * 验证身份证号名字是否验证成功
     *
     * @param userName 名字
     * @param iCard    身份证号
     * @return
     */
    public boolean certificationDetail(String userName, String iCard) {
        String accessToken = getAccessToken();
        String requestUrl = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/result/detail?access_token" + accessToken;
        // verify_token从redis中获取
        String redisKey = CacheConstants.VERIFY_TOKEN_KEY + userName + "-" + iCard;
        String verifyToken = null;
        if (redisService.hasKey(redisKey)) {
            verifyToken = redisService.getCacheObject(redisKey);
        }
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("verify_token", verifyToken);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject responseObject = restTemplate.postForObject(requestUrl, paramMap, JSONObject.class);
        Object result = responseObject.get("success");
        if (result != null) {
            if (true == (boolean) result) {
                return true;
            }
        }
        return false;
    }


    /**
     * 身份证与名字识别
     *
     * @param name 名字
     * @param code 身份证号
     * @return token信息
     */
    public Map<String, String> idMatch(String name, String code) {
        Map<String, String> tokenMap = getVerifyToken();
        String requestUrl = "https://aip.baidubce.com/rest/2.0/face/v3/person/idmatch?access_token=" + tokenMap.get("accessToken");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("verify_token", tokenMap.get("verifyToken"));
        paramMap.put("id_name", name);
        paramMap.put("id_no", code);
        JSONObject responseObject = restTemplate.postForObject(requestUrl, paramMap, JSONObject.class);
        String redisKey = CacheConstants.VERIFY_TOKEN_KEY + name + "-" + code;
        redisService.setCacheObject(redisKey, tokenMap.get("verifyToken"), 3L, TimeUnit.DAYS);
        return tokenMap;
    }

    /**
     * 身份证文字识别
     *
     * @return
     */
    public String idcard() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            // 本地文件路径
            String filePath = "[本地文件路径]";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = getAccessToken();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
