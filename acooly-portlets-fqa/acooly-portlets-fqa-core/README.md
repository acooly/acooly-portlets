<!-- title: 客户反馈组件 -->
<!-- type: portlets -->
<!-- author: zhangpu -->
<!-- date: 2020-02-18 -->
## 1. 简介
用于互联网业务中常见问题的FQA，可用于WEB，H5，小程序和App多个场景

## 2. 特性

* BOSS后台添加和维护常见问题库
* 集成TreeType组件，支持自定义多级分类。
* 支持本地单项目集成，openapi服务和dubbo/springcloud服务多种方式部署和应用
* 支持swagger方式输出文档

## 3. 集成

### 3.1. 模块

评论组件为了支持多种集成和部署场景使用，设计为三个子模块：

* acooly-portlets-fqa-core: 组件业务核心实现和dubbo-facade接口实现
* acooly-portlets-fqa-openapi: 组件对外的openapi接口
* acooly-portlets-fqa-client: 组件对外的客户端，包括dubbo客户端（等同于*-facade）和openapi-client

通用acooly组件集成方式。在引入依赖坐标后，通过 `acooly.portlets.fqa.enable=true|false`控制启用和停用

```xml
<dependency>
    <groupId>com.acooly</groupId>
    <artifactId>acooly-portlets-fqa-client</artifactId>
    <version>${acooly.version}</version>
</dependency>

<!-- 本地应用，只需集成core -->
<dependency>
    <groupId>com.acooly</groupId>
    <artifactId>acooly-portlets-fqa-core</artifactId>
    <version>${acooly.version}</version>
</dependency>
	
<dependency>
    <groupId>com.acooly</groupId>
    <artifactId>acooly-portlets-fqa-openapi</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

### 3.2. 组件参数

组件配置参数：

```
# 组件开关
acooly.portlets.fqa.enable=true
# 组件是否启动暴露dubbo接口服务开关
acooly.portlets.fqa.facadeEnable=false

```


## 4. 应用

### 4.1. 单服务

引入核心依赖：acooly-portlets-fqa-core

服务层核心接口：`com.acooly.portlets.feedback.openapi.service.FqaService`
该模式下组件参数配置为默认配置即可。

### 4.2. 业务服务和网关服务合一

该模式为：服务端直接集成组件获得业务能力（FqaRemoteService），同时OpenAPI服务也在改进程启动和对外提供服务。OpenAPI客户端可能是另外的前端网站服务或App

组件配置参数采用默认即可

>特别注意：
>
>1. 该模式下，业务服务和OpenAPI服务在统一进程和容器内，OpenAPI模块会自动选择使用FqaService对外提供服务，所以无法打开facadeEnable开关：`acooly.portlets.fqa.facadeEnable=false`。
>2. 业务的客户端是前端网站（xxx-portal）服务的时候，请依赖：`acooly-portlets-fqa-client`, 请注意配置好openapi-framework-client的公共客户端参数

1、服务器端项目

依赖：acooly-portlets-fqa-core和acooly-portlets-fqa-openapi，默认配置即可。

2、客户端项目

依赖：acooly-portlets-fqa-client，注入`FqaApiClientService `直接调用。

### 4.3. 业务服务和网关服务独立

该模式为：

1. 业务服务:只提供业务的实现和后台管理，独立进程
2. 网关服务：openapi网关服务通过dubbo调用业务服务对外提供接口服务，独立进程
3. 客户端服务：前端网站（或其他App）

1、业务服务

依赖：acooly-portlets-fqa-core

配置参数为：

```
# 组件开关
acooly.portlets.feedback.enable=true
# 组件是否启动暴露dubbo接口服务开关
acooly.portlets.feedback.facadeEnable=true
```
>注意：`acooly.portlets.feedback.facadeEnable=true`表示启动dubbo服务，待网关服务调用。

2、网关服务

依赖：acooly-portlets-fqa-openapi，无需其他配置，打开dubbo组件开关即可（使用客户端功能）
>PS: 模块内会自动判断注入的本地fqaService不存在，会直接调用fqaRemoteService客户端。

3、客户端服务

依赖：acooly-portlets-fqa-client，注入`FqaApiClientService `直接调用。

>注意：这里的OpenAPI服务的应该配置网关服务的对应参数
