package com.chilun.hotAir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 齿轮
 * @date 2023-12-21-0:33
 */
@Configuration
@EnableSwagger2
@Profile({"dev"})
public class Knife4jConfig {
    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("热风润叶仿真控制平台")
                        .description("热风润叶闭环控制平台")
                        .contact(new Contact("chilun", "http://home.shoumingchilun.cn", "2265501251@qq.com"))
                        .version("1.0")
                        .build())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.chilun.hotAir.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
