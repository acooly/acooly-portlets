评论组件
====
 
acooly-portlets-feedback
----

## 1. 简介
用于互联网业务中常见业务的SNS用户反馈功能，可用于WEB，H5，小程序和App多个场景

## 2. 特性

* 用户反馈基础功能包括：分类：建议/咨询/投诉等类型，支持提交简单反馈信息
* 反馈提交同时支持手机用户基本联系信息和客户端信息（IP，设备，浏览器等）
* BOSS后台已集成完善的反馈意见处理功能
* 在提交反馈和处理反馈的时提供事件发布
* 支持本地单项目集成，openapi服务和dubbo服务多种方式部署和应用



## 3. 集成

### 3.1. 模块

评论组件为了支持多种集成和部署场景使用，设计为三个子模块：

* acooly-portlets-feedback-core: 组件业务核心实现和dubbo-facade接口实现
* acooly-portlets-feedback-openapi: 组件对外的openapi接口
* acooly-portlets-feedback-client: 组件对外的客户端，包括dubbo客户端（等同于*-facade）和openapi-client

通用acooly组件集成方式。在引入依赖坐标后，通过 `acooly.portlets.feedback.enable=true|false`控制启用和停用

```xml
	<dependency>
	    <groupId>com.acooly</groupId>
	    <artifactId>acooly-portlets-feedback-xxx</artifactId>
	    <version>${acooly.version}</version>
	</dependency>
```
>ps: 最新acooly.version=4.2.0-SNAPSHOT

### 3.2. 组件参数

组件配置参数：

```
# 组件开关
acooly.portlets.feedback.enable=true
# 组件发布事件开关
acooly.portlets.feedback.eventPublishEnable=true
# 组件是否启动暴露dubbo接口服务开关
acooly.portlets.feedback.facadeEnable=false

```


## 4. 应用

### 4.1. 单服务

引入核心依赖：acooly-portlets-feedback-core

服务层核心接口：`com.acooly.portlets.feedback.openapi.service.FeedbackService`
在你的应用层可注入并直接应用,提供apply（前台）和handle(后台)接口，你可以通过监听事件对apply和handle进行处理，比如：监听handle成功接口给用户发送短信或邮件通知等。

该模式下组件参数配置为默认配置即可。

### 4.2. 业务服务和网关服务合一

该模式为：服务端直接集成组件获得业务能力（FeedbackService），同时OpenAPI服务也在改进程启动和对外提供服务。OpenAPI客户端可能是另外的前端网站服务或App

组件配置参数采用默认即可

>特别注意：
>
>1. 该模式下，业务服务和OpenAPI服务在统一进程和容器内，OpenAPI模块会自动选择使用FeedbackService对外提供服务，所以无法打开facadeEnable开关：`acooly.portlets.feedback.facadeEnable=false`。
>2. 业务的客户端是前端网站（xxx-portal）服务的时候，请依赖：`acooly-portlets-feedback-client`,直接注入`FeedbackApiClientService`客户端服务即可直接调用。当然请注意配置好openapi-framework-client的公共客户端参数

1、服务器端项目

依赖：acooly-portlets-feedback-core和acooly-portlets-feedback-openapi，默认配置即可。

2、客户端项目

依赖：acooly-portlets-feedback-client，注入`FeedbackApiClientService `直接调用。

### 4.3. 业务服务和网关服务独立

该模式为：

1. 业务服务:只提供业务的实现和后台管理，独立进程
2. 网关服务：openapi网关服务通过dubbo调用业务服务对外提供接口服务，独立进程
3. 客户端服务：前端网站（或其他App）

1、业务服务

依赖：acooly-portlets-feedback-core

配置参数为：

```
# 组件开关
acooly.portlets.feedback.enable=true
# 组件发布事件开关
acooly.portlets.feedback.eventPublishEnable=true
# 组件是否启动暴露dubbo接口服务开关
acooly.portlets.feedback.facadeEnable=true
```
>注意：`acooly.portlets.feedback.facadeEnable=true`表示启动dubbo服务，待网关服务调用。

2、网关服务

依赖：acooly-portlets-feedback-openapi，无需其他配置，打开dubbo组件开关即可（使用客户端功能）
>PS: 模块内会自动判断注入的本地feedbackService不存在，会直接调用feedbackFacade客户端。

3、客户端服务

依赖：acooly-portlets-feedback-client，注入`FeedbackApiClientService `直接调用。

>注意：这里的OpenAPI服务的应该配置网关服务的对应参数

## 5. 接口

### 5.1 dubbo

`com.acooly.portlets.feedback.client.facade.api.FeedbackFacade`

### 5.2 openapi

1、feedbackApply: 反馈提交：com.acooly.portlets.feedback.openapi.FeedbackApplyApiService

## 6. 扩展

### 6.1 事件

1、反馈成功提交事件

com.acooly.portlets.feedback.openapi.event.FeedbackApplySuccessEvent

2、反馈成功处理事件

com.acooly.portlets.feedback.openapi.event.FeedbackHandleSuccessEvent
