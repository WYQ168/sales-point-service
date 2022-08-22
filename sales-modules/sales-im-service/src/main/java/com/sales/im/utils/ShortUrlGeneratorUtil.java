package com.sales.im.utils;

import java.security.MessageDigest;
import java.util.UUID;
 
/***
 * 生成短连接
 *
 * Created by lhs on 2021/8/31.
 */
public class ShortUrlGeneratorUtil {
 
 
    // 生成几个短连接地址
    private final static int COUNT = 1;
    // 生成几位短连接地址的签名
    public final static int LENGTH = 5;
 
    public static void main(String[] args) {
        
        String sLongUrl = "https://www.baidu.com/s?wd=java%20%E7%9F%AD%E9%93%BE%E6%8E%A5%E7%94%9F%E6%88%90%E5%B7%A5%E5%85%B7&pn=10&oq=java%20%E7%9F%AD%E9%93%BE%E6%8E%A5%E7%94%9F%E6%88%90%E5%B7%A5%E5%85%B7&ie=utf-8&rsv_pq=9d7148b5000012fa&rsv_t=d3dfLVSZtI6guu0O4L7S3fJQHX55ti0cHVFcNbmMrogN8NaZpy7eLffUlpY&rsv_page=1&bs=java%20%E7%9F%AD%E9%93%BE%E6%8E%A5%E7%94%9F%E6%88%90%E5%B7%A5%E5%85%B7"; // 原始链接
        System.out.println("短链接:"+getShortUrl(sLongUrl));// 随机取一个作为短链
        System.out.println("短链接长度:"+getShortUrl(sLongUrl).length());// 随机取一个作为短链
        System.out.println();
    }
 
 
    /**
     * 获取短连接地址
     * @param url
     * @return
     * @return String
     * @author lhs
     * @date   2021年8月31日下午2:27:24
     */
    public static String getShortUrl(String url) {
        return shortUrl(url)[0];
    }
 
    /**
     * 返回4组端地址
     * @param url
     * @return
     * @return String[]
     * @author lhs
     * @date   2021年8月31日下午2:28:36
     */
    public static String[] shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
 
        };
        // 对传入网址进行 MD5 加密
        String hex = md5ByHex(UUID.randomUUID().toString() + url);
 
        String[] resUrl = new String[COUNT];
        for (int i = 0; i < COUNT; i++) {
 
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
 
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < LENGTH; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }
 
    /**
     * MD5加密(32位大写)
     * @param src
     * @return
     * @return String
     * @author lhs
     * @date   2021年8月31日下午6:31:59
     */
    public static String md5ByHex(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes();
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            String hs = "";
            String stmp = "";
            for (int i = 0; i < hash.length; i++) {
                stmp = Integer.toHexString(hash[i] & 0xFF);
                if (stmp.length() == 1)
                    hs = hs + "0" + stmp;
                else {
                    hs = hs + stmp;
                }
            }
            return hs.toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }
}