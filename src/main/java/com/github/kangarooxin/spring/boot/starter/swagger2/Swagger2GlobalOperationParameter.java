package com.github.kangarooxin.spring.boot.starter.swagger2;

import lombok.Data;

/**
 * @author kangarooxin
 */
@Data
public class Swagger2GlobalOperationParameter {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数描述
     */
    private String description = "";

    /**
     * 参数默认值
     */
    private String defaultValue = "";

    /**
     * 是否必须
     */
    private boolean required = true;


    /**
     * 参数类型
     */
    private String modelRef = "String";

    /**
     * 参数位置
     * header, cookie, body, query
     */
    private String paramType = "query";
}
