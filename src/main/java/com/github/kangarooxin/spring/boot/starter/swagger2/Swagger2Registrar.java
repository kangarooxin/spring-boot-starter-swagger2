package com.github.kangarooxin.spring.boot.starter.swagger2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * @author kangarooxin
 */
public class Swagger2Registrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, ResourceLoaderAware {

    protected Environment environment;

    protected ResourceLoader resourceLoader;

    protected BeanDefinitionRegistry registry;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        this.registry = registry;
        Swagger2Properties groupProperties;
        Swagger2Properties properties = getProperties(Constants.SWAGGER2_PREFIX, Swagger2Properties.class);
        if(properties != null && properties.getGroups() != null) {
            for (String group : properties.getGroups()) {
                groupProperties = getProperties(Constants.SWAGGER2_PREFIX + "." + group, Swagger2Properties.class);
                if(groupProperties == null) {
                    continue;
                }
                if (StringUtils.isEmpty(groupProperties.getGroupName())) {
                    groupProperties.setGroupName(group);
                }
                registerBean(group + "Docket", Swagger2DocketFactoryBean.class, groupProperties);
            }
        }
    }

    private <T> T getProperties(final String prefix, final Class<T> targetClass) {
        String prefixParam = prefix.endsWith(".") ? prefix.substring(0, prefix.length() - 1) : prefix;
        Binder binder = Binder.get(environment);
        BindResult<T> bindResult = binder.bind(prefixParam, targetClass);
        if(bindResult == null || !bindResult.isBound()) {
            return null;
        }
        return bindResult.get();
    }

    private void registerBean(String name, Class<?> clazz, Object... args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        registry.registerBeanDefinition(name, beanDefinition);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
