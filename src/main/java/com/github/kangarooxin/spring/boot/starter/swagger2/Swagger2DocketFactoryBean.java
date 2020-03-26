package com.github.kangarooxin.spring.boot.starter.swagger2;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author kangarooxin
 */
public class Swagger2DocketFactoryBean extends AbstractFactoryBean<Docket> {

    private Swagger2Properties properties;

    public Swagger2DocketFactoryBean(Swagger2Properties properties) {
        this.properties = properties;
    }

    @Override
    public Class<?> getObjectType() {
        return Docket.class;
    }

    @Override
    protected Docket createInstance() throws Exception {
        return Swagger2Utils.buildDocket(properties);
    }
}
