package com.stock.exchange.config.swagger;

import com.stock.exchange.controller.StockController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@EnableSwagger2
@ComponentScan(basePackageClasses = {StockController.class})
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Stock Exchange REST API";
    private static final String DESCRIPTION = "RESTful API For Stock Exchange";
    private static final String LICENSE_TEXT = "License";
    private static final String SWAGGER_API_VERSION = "1.0";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .termsOfServiceUrl("localhost")
                .version(SWAGGER_API_VERSION)
                .license(LICENSE_TEXT)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}
