package com.github.kangarooxin.spring.boot.starter.swagger2;

import com.github.kangarooxin.spring.boot.starter.swagger2.Swagger2GlobalOperationParameter;
import com.github.kangarooxin.spring.boot.starter.swagger2.Swagger2Properties;
import com.google.common.base.Predicate;
import org.springframework.util.ClassUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author pangxin01822
 */
public class Swagger2Utils {

    public static Docket buildDocket(Swagger2Properties properties) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(properties.isEnabled())
                .groupName(properties.getGroupName())
                .host(properties.getHost())
                .pathMapping(properties.getPathMapping())
                .apiInfo(buildApiInfo(properties));
        if (properties.getGenericClasses() != null) {
            docket.genericModelSubstitutes(properties.getGenericClasses());
        }
        if(properties.getGlobalParameters() != null) {
            List<Parameter> parameters = new ArrayList<>();
            for(Swagger2GlobalOperationParameter parameter : properties.getGlobalParameters()) {
                parameters.add(buildParameter(parameter));
            }
            docket.globalOperationParameters(parameters);
        }
        ApiSelectorBuilder apiSelectorBuilder = docket.select()
                .apis(basePackage(properties.getBasePackage(), properties.isIncludeSubPackage()));

        String[] pathSelects = properties.getPathSelect();
        if(pathSelects != null && pathSelects.length > 0) {
            for(String pathSelect : pathSelects) {
                apiSelectorBuilder.paths(PathSelectors.ant(pathSelect));
            }
        } else {
            apiSelectorBuilder.paths(PathSelectors.any());
        }
        return apiSelectorBuilder.build();
    }

    public static Predicate<RequestHandler> basePackage(final String[] packageNames, final boolean includeSubPackage) {
        return input -> declaringClass(input).map(handlerPackage(packageNames, includeSubPackage)).orElse(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String[] packageNames, final boolean includeSubPackage) {
        return input -> {
            String inputPackageName = ClassUtils.getPackageName(input);
            if(includeSubPackage) {
                for(String packageName : packageNames) {
                    if(inputPackageName.startsWith(packageName)) {
                        return true;
                    }
                }
            } else {
                for(String packageName : packageNames) {
                    if(inputPackageName.equals(packageName)) {
                        return true;
                    }
                }
            }
            return false;
        };
    }

    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

    private static Parameter buildParameter(Swagger2GlobalOperationParameter parameter) {
        return new ParameterBuilder()
                .name(parameter.getName())
                .parameterType(parameter.getParamType())
                .defaultValue(parameter.getDefaultValue())
                .description(parameter.getDescription())
                .modelRef(new ModelRef(parameter.getModelRef()))
                .required(parameter.isRequired())
                .build();
    }

    private static ApiInfo buildApiInfo(Swagger2Properties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()))
                .version(properties.getVersion())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .description(properties.getDescription())
                .licenseUrl(properties.getLicenseUrl())
                .license(properties.getLicense())
                .build();
    }
}
