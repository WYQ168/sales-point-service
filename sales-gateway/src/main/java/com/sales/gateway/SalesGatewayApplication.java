package com.sales.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动程序
 *
 * @author wuyingqiang
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.sales"})
public class SalesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  sales网关启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
