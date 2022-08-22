package com.sales.common.core.utils.sign;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @Title:StrUtl
 * @Desc:
 * @Author: wuyingqiang
 * @Date: 2020-3-31 23:39
 */
public class UrlEncodeUtil {
   public static String KEY="qG&0127Rj6JhC5TEdtZu4HNGO%%svuU@";
   public static final String SALT_PRE="sales";
   public static final String SALT_SUF="ban_yu_app";
     static String encryptStr(String url,String key) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        char [] c =url.toCharArray();
        char [] ckey=key.toCharArray();
        for(int i = 0;i<c.length;i++) {
            c[i]= (char)(c[i] ^getCharKey(ckey,i));
        }
        return String.valueOf(c);
    }
    public static String encryptUrl(String url,String key) {
        return  Base64.getEncoder().encodeToString(encryptStr(url,key).getBytes());
    }
    public static String decryptUrl(String encrypt64Url,String key) {
        return  decryptStr(new String(Base64.getDecoder().decode(encrypt64Url)),key);
    }
     static String decryptStr(String encryptUrl,String key) {
        if (StringUtils.isEmpty(encryptUrl)) {
            return null;
        }
        char [] c =encryptUrl.toCharArray();
        char [] ckey=key.toCharArray();
        for(int i = 0;i<c.length;i++) {
            c[i] = (char)(c[i] ^getCharKey(ckey,i));
        }
        return String.valueOf(c);
    }
   public static char getCharKey(char[] charKey,int charAt){
        if(charKey==null || charKey.length<=0){
            return 'A';
        }
        if(charAt>charKey.length-1){
            charAt=0;
        }
        return charKey[charAt];
   }
   public static void main(String[] arg){
    String url ="http://47.115.177.50:8888/group1/M00/00/0A/rBL6vF6aXrOACxY4AABMnVLblBQ714.jpg";
    System.out.println("加密后: " +encryptUrl(url,KEY) );
       System.out.println("解密后: " +decryptUrl(encryptUrl(url,KEY),KEY) );
   }

}
