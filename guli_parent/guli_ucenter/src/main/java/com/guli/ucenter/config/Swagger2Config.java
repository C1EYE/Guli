package com.guli.ucenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("webApi").select()
                .apis(RequestHandlerSelectors.basePackage("com.guli.ucenter.controller")).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("用户中心").description("用户中心接口定义").
                version("1.0").contact(new Contact("c1eye", "8.136.232.203:8080", "2010782766@qq.com"))
                .build();
    }

}
