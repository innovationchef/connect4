package com.innovationchef.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String API_TITLE = "Connect4 Game APIs";
    public static final String API_DESC = "APIs to support the connect4 game play";
    public static final String API_VERSION1 = "v1";
    public static final String API_TERMS_URL = "https://github.com/innovationchef/connect4/blob/master/LICENSE";
    public static final String API_LICENSE = "License of API";
    public static final String API_LICENSE_URL = "API license URL";
    public static final String CONTACT_NAME = "Ankit Lohani";
    public static final String CONTACT_EMAIL = "lohani.1575@gmail.com";
    public static final String CONTACT_URL = "https://www.linkedin.com/in/innovationchef/";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                API_TITLE,
                API_DESC,
                API_VERSION1,
                API_TERMS_URL,
                new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL),
                API_LICENSE, API_LICENSE_URL, Collections.emptyList());
    }
}
