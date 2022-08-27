package com.sales.im.utils;
 
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
 
/**
 * 
 * @title: 短连接生成工具
 * @author: wll
 * @since: 2021-2-9 16:34:13
 */
public class ShortUrl {
 
	static Map<String, String> urlMap = new HashMap<String, String>();
 
	public static void main(String[] args) {

		String url = "http://47.92.80.167:8848/nacos/#/configeditor?serverId=center&dataId=sales-im-service-dev.yml&group=DEFAULT_GROUP&namespace=dev&edasAppName=&edasAppId=&searchDataId=&searchGroup=&pageSize=10&pageNo=1";
		String transShortUrl = transShortUrl(url);
 
		System.out.println("转换后：" + transShortUrl);
		System.out.println("转换后长度：" + transShortUrl.length());

		String longlString = openShortUrl(transShortUrl);
		System.out.println("转换前：" + longlString);
		System.out.println("转换前长度：" + longlString.length());

		System.out.println("点击连接："+ openShortUrl(transShortUrl));
		System.out.println("点击连接长度："+ openShortUrl(transShortUrl).length());
	}

	public static String openShortUrl(String shortUrl) {
		return urlMap.get(shortUrl);
	}
 
	/**
	 * 转换为短连接
	 * 
	 * @param url
	 */
	public static String transShortUrl(String url) {
 
		String resultStr = "htttp://www.lbgjs.cn/" + (UUID.randomUUID().toString().replace("-", ""));
		urlMap.put(resultStr, url);
		return resultStr;
 
	}
 
}