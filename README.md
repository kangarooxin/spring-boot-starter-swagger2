# spring-boot-starter-swagger2

swagger2 spring boot starter

## Usage:
1. build and install jar
```
git clone https://github.com/kangarooxin/spring-boot-starter-swagger2.git
cd spring-boot-starter-swagger2
mvc clean install
```
2. import dependency in pom.xml
```
<dependency>
    <groupId>com.github.kangarooxin</groupId>
    <artifactId>spring-boot-starter-swagger2</artifactId>
    <version>1.0.0</version>
</dependency>
```
3. config in properties
```
   #swagger2
   #swagger2.enabled=true
   swagger2.title=${spring.application.name}
   swagger2.version=1.0.0
   swagger2.contact-name=kangarooxin
   swagger2.contact-email=kangarooxin@github.com
   #back package,support multiple package
   swagger2.base-package=com.example.controller
   #is include sub package
   swagger2.include-sub-package=true
   #global parameters
   swagger2.global-parameters[0].name=appId
   swagger2.global-parameters[0].description=应用ID
   swagger2.global-parameters[0].required=true
   swagger2.global-parameters[0].model-ref=int
   swagger2.global-parameters[1].name=accessToken
   swagger2.global-parameters[1].description=访问秘钥
   swagger2.global-parameters[1].required=false
   swagger2.global-parameters[1].model-ref=string
```
4. Support multiple group
```
   #declare multiple group
   swagger2.groups=group1,group2
   #group1 config
   swagger2.group1.title=${spring.application.name}
   swagger2.group1.group-name=group1-name
   swagger2.group1.version=1.0.0
   swagger2.group1.base-package=com.example.controller.group1
   #group2 config
   swagger2.group2.title=${spring.application.name}
   swagger2.group2.group-name=group2-name
   swagger2.group2.version=1.0.0
   swagger2.group2.base-package=com.example.controller.group2
```
5. Swagger2常用注解
   - @Api() 用于类； 表示标识这个类是swagger的资源
   - @ApiOperation() 用于方法； 表示一个http请求的操作
   - @ApiParam()
   用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
   - @ApiModel() 用于类 表示对类进行说明，用于参数用实体类接收
   - @ApiModelProperty() 用于方法，字段 表示对model属性的说明或者数据操作更改
   - @ApiIgnore() 用于类，方法，方法参数 表示这个方法或者类被忽略
   - @ApiImplicitParam() 用于方法 表示单独的请求参数
   - @ApiImplicitParams()
   用于方法，包含多个 @ApiImplicitParam
