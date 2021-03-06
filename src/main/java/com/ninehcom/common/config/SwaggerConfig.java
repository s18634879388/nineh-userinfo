package com.ninehcom.common.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
                apiInfo()).includePatterns(".*?");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "用户中心接口文档",
                "API for User Service",
                null,
                null,
                null,
                null);
        return apiInfo;
    }
}
