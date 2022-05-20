package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableAutoConfiguration
public class SwaggerConfig {
    
    public static final Contact DEFAULT_CONTACT = new Contact("oga", "https://spring.io", "oscar.garcia@mariachi.io");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Hello world api", "Api for greeting new developers",
            "v1.0", "htt://spring.io", DEFAULT_CONTACT, "Apache 2.0", "http://spring.io", Arrays.asList());
    public static final Set<String> DEFAULT_PRODUCE_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCE_CONSUMES)
        .consumes(DEFAULT_PRODUCE_CONSUMES)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
                .build();
    }
}
