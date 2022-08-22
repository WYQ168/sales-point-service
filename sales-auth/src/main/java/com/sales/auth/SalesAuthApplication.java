package com.sales.auth;

import com.sales.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 认证授权中心
 *
 * @author sales
 */
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = "com.sales",exclude = {DataSourceAutoConfiguration.class})
public class SalesAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
