package com.sales.file;

import com.sales.common.security.annotation.EnableCustomConfig;
import com.sales.common.security.annotation.EnableRyFeignClients;
import com.sales.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务
 *
 * @author sales
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.sales.file"},exclude = {DataSourceAutoConfiguration.class})
public class SalesFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
