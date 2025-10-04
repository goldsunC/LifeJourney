package org.kangning.lifejourney.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j (Swagger) 接口文档配置类
 * 用于自动生成和展示API接口文档
 */
@Configuration
public class Knife4jConfig {

    /**
     * 配置OpenAPI信息
     * @return OpenAPI对象
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // 创建安全模式（JWT认证）
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
        
        // 创建安全需求
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
        
        return new OpenAPI()
                // 添加接口文档的基本信息
                .info(new Info()
                        .title("LifeJourney API接口文档")
                        .description("个人数字空间平台 - API接口详细说明文档，包含用户认证、时间线、文章、相册等功能接口")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("goldsunC")
                                .email("goldsunckn@163.com")
                                .url("https://github.com/goldsunC/lifejourney"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                // 添加安全配置
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
    
    /**
     * 配置分组OpenAPI
     * @return GroupedOpenApi对象
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .packagesToScan("org.kangning.lifejourney.controller")
                .build();
    }
    

}