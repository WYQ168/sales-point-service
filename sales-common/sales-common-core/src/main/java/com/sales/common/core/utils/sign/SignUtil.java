package com.sales.common.core.utils.sign;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc: SignUtil
 * @author wuyingqiang
 * @date 2022/7/14 15:29
 * @param
 * @return
 */
public class SignUtil {
    private final static String LOGIN_IN_RANDOM="1CcTxu5j0n";
    private static int DEFAULT_TIMEOUT_SECOND=180;
    /**
     * 验证加密串是否成功
     * @param sign
     * @return
     */
    public static boolean verfiySign(String sign){
        if(StringUtils.isEmpty(sign)){
            return false;
        }
        String decryptSign = UrlEncodeUtil.decryptUrl(sign, UrlEncodeUtil.KEY);

        String random = decryptSign.substring(0,10);
        //log.error("解密后的串：" + random);
        if(!LOGIN_IN_RANDOM.equals(random)){
            return false;
        }
        // 对比 现在的时间超过三分钟 即视为非法请求
        String randomTime = decryptSign.substring(random.length());
        //log.error("解密后的时间：" + randomTime);
        if(StringUtils.isEmpty(randomTime)){
            return false;
        }
        int nowTime = (int)(System.currentTimeMillis()/1000);
        int passTime = Integer.parseInt(randomTime);
        int surplusTime = nowTime - passTime;
        if(surplusTime>DEFAULT_TIMEOUT_SECOND){// 3分钟
            return false;
        }
        return true;
    }

}
