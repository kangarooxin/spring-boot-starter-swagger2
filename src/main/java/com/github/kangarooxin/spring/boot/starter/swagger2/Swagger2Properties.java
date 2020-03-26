package com.github.kangarooxin.spring.boot.starter.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author kangarooxin
 */
@ConfigurationProperties(prefix = Constants.SWAGGER2_PREFIX)
@Data
public class Swagger2Properties {

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 多组文档
     */
    private String[] groups;

    /**
     * 文档组名
     */
    private String groupName;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String description;

    /**
     * 文档版本号
     */
    private String version;

    /**
     * 服务条款网址
     */
    private String termsOfServiceUrl;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人网址
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 许可证名称
     */
    private String license;

    /**
     * 许可证网址
     */
    private String licenseUrl;

    /**
     * 接口域名
     */
    private String host;

    /**
     * 接口路径前缀
     */
    private String pathMapping;

    /**
     * 接口扫描包名
     */
    private String[] basePackage;

    /**
     * 接口是否包含子包
     */
    private boolean includeSubPackage = true;

    /**
     * 接口泛型类
     */
    private Class<?>[] genericClasses;

    /**
     * 接口选择器
     */
    private String[] pathSelect;

    /**
     * 公共参数
     */
    private List<Swagger2GlobalOperationParameter> globalParameters;
}
