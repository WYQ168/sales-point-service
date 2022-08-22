package com.sales.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.constant.CacheConstants;
import com.sales.common.core.constant.HttpStatus;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.constant.TokenConstants;
import com.sales.common.core.utils.ServletUtils;
import com.sales.common.core.utils.StringUtils;
import com.sales.common.redis.service.RedisService;
import com.sales.gateway.config.properties.IgnoreWhiteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关鉴权
 * 
 * @author sales
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered
{
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);
    private final static String[] VALIDATE_URL = new String[] { "/auth/**", "/file/**","" };
    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisService redisService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites()))
        {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token))
        {
            return unauthorizedResponse(exchange, "拒绝访问");
        }
        boolean islogin = redisService.hasKey(getTokenKey(token));
        if (!islogin)
        {
            return unauthorizedResponse(exchange, "令牌已过期");
        }

        JSONObject loginUser =  redisService.getCacheObject(getTokenKey(token));
//        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))
//        {
//            return unauthorizedResponse(exchange, "令牌验证失败");
//        }
        //根据用户类型设定访问的服务权限范围。

        // 设置用户信息到请求
        addHeader(mutate, SecurityConstants.USER_KEY, token);
        addHeader(mutate, SecurityConstants.DETAILS_USER_TYPE, loginUser.getString("userType"));
        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, loginUser.getLong("userid"));
        addHeader(mutate, SecurityConstants.DETAILS_USERNAME,  loginUser.getString("username"));
        // 内部请求来源参数清除
        removeHeader(mutate, SecurityConstants.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value)
    {
        if (value == null)
        {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name)
    {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token)
    {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request)
    {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder()
    {
        return -200;
    }
}