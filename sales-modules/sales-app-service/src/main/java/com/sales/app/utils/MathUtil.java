package com.sales.app.utils;

/**
 * @description: 数字处理工具类
 * @author: wuyingqiang
 * @date: 2022-08-23 14:45
 */

public class MathUtil {

    public static String getRandomNumString(Integer n) {
        StringBuffer stringBuffer = new StringBuffer();
        int num ;
        double r ;
        for (int i = 0; i < n; i++) {
            r = Math.random();
            num = (int) (r * n + 1);
            stringBuffer.append(num);
        }
        return stringBuffer.toString();
    }

}
