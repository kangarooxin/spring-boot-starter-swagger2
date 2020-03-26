# spring-boot-starter-swagger2

swagger2 spring boot starter

## Usage:
1. import dependency in pom.xml
```
<dependency>
    <groupId>com.github.kangarooxin</groupId>
    <artifactId>spring-boot-starter-swagger2</artifactId>
    <version>1.0.0</version>
</dependency>
```
2. config in properties
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
3. Support multiple group
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
