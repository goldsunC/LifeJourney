package org.kangning.lifejourney.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * 用于解决Spring Boot 3.x与MyBatis-Plus的兼容性问题
 */
@Configuration
@MapperScan("org.kangning.lifejourney.mapper")
public class MyBatisConfig {
    
    // 配置类不需要额外的方法
}