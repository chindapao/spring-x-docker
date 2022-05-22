package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket bookHotelApi() {
        return new Docket( DocumentationType.SWAGGER_2)
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths( PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Chinda Pao", "www.wabooks.com.kh", "chinpao@wabooks.com.kh");
        return new ApiInfoBuilder()
                .title("Swagger By NDA")
                .version("1.0")
                .description("Brilliant boy!!")
                .contact(contact)
                .license("Apache License Version 2.0")
                .build();
    }
}
