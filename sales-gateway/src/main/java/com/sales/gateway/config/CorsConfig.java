package com.sales.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * * 程序名 : CorsConfig
 * 建立日期: 2018-09-09
 * 作者 : someday
 * 模块 : 网关
 * 描述 : 跨域
 * 备注 : version20180909001
 * <p>
 * 修改历史
 * 序号 	       日期 		        修改人 		         修改原因
 */
@Configuration
public class CorsConfig {
	private static final String ALL = "*";
	private static final String MAX_AGE = "18000L";

//	@Bean
//	public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient,
//			DiscoveryLocatorProperties properties) {
//		return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
//	}

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return new DefaultServerCodecConfigurer();
	}

	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
			if (!CorsUtils.isCorsRequest(request)) {
				return chain.filter(ctx);
			}
			HttpHeaders requestHeaders = request.getHeaders();
			ServerHttpResponse response = ctx.getResponse();
			HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
			HttpHeaders headers = response.getHeaders();
			headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
			headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
			if (requestMethod != null) {
				headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
			}
			headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
			headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL);
			headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
			if (request.getMethod() == HttpMethod.OPTIONS) {
				response.setStatusCode(HttpStatus.OK);
				return Mono.empty();
			}
			return chain.filter(ctx);
		};
	}
}
