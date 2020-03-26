package com.github.kangarooxin.spring.boot.starter.swagger2;

import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kangarooxin
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({Swagger2Properties.class})
@ConditionalOnClass({Swagger.class})
@Import(Swagger2Registrar.class)
@ConditionalOnProperty(prefix = Constants.SWAGGER2_PREFIX, name = "enabled", matchIfMissing = true)
public class Swagger2AutoConfiguration {

    @Autowired
    private Swagger2Properties properties;

    @Bean
    public Docket swagger2Docket() {
        return Swagger2Utils.buildDocket(properties);
    }
}
