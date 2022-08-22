package com;

import com.sales.common.security.annotation.EnableCustomConfig;
import com.sales.common.security.annotation.EnableRyFeignClients;
import com.sales.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.sales"},exclude = {DataSourceAutoConfiguration.class})
public class SalesImApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesImApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  im服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");

    }
}
