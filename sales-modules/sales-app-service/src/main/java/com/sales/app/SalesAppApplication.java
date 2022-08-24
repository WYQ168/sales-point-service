package com.sales.app;

import com.sales.common.security.annotation.EnableCustomConfig;
import com.sales.common.security.annotation.EnableRyFeignClients;
import com.sales.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableRyFeignClients
@EnableCustomSwagger2
@SpringBootApplication(scanBasePackages = "com.sales")
public class SalesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesAppApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  SalesApp启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
