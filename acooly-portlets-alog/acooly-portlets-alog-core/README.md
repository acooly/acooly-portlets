<!-- title: 行为日志组件 -->
<!-- type: portlets -->
<!-- author: zhangpu -->
<!-- date: 2019-01-11 -->
## 1.简介
互联网常用的站点分析工具（比如：百度站点工具）可以完成大通用访问行为的分析，但存在以下两个问题没有很好的解决，这也是本组件需要重点满足的需求。

* 方便的多端搜集和合并
* 灵活的与业务场景结合

## 2.特性

alog组件主要提供两个方面的能力：收集和分析。

* 收集：支持多端和多种方式收集（js/openapi/spring拦截器/自定义），缓存队列方式收集和批量持久化。
* 分析：提供一批标准的行为分析，同时提供可扩展的按需分析支持。

## 3.集成

### 3.1.模块和坐标

部署行为日志服务时候，你需要根据你的目标平台场景进行选择部署方式。主要以下几种情况：

1. 单Web服务：行为日志服务就是你的网站服务，没有Api对外或App的情况，只需要集成：acooly-portlets-alog-core
2. 微服务模式：独立部署行为日志服务（或与平台业务服务合一），有网关OpenApi服务，对外有网站，App等多端需要收集行为，行为日志服务需要集成：acooly-portlets-alog-openapi，提供JS和openApi两种对外收集方案


```xml
	<!-- 行为日志：核心 -->
	<dependency>
		<groupId>com.acooly</groupId>
		<artifactId>acooly-portlets-alog-core</artifactId>
		<verion>4.2.1-SNAPSHOT</version>
	</dependency>
```

其他模块为：

* acooly-portlets-alog-client：openapi或facade的公共定义类
* acooly-portlets-alog-openapi：对外提供openapi方式收集日志的能力（AlogApiService）
* acooly-portlets-alog-analysis: 行为日志分析模块


### 3.2.配置

#### 3.2.1.行为日志服务参数

```ini
## alog
# 日志缓存队列大小
acooly.alog.cacheQueueSize=10000
# 从日志缓存中拉取的批量存储大小，每次批量持久化的最大值
acooly.alog.saveCacheSize=1000
# 检查并拉取缓存队列持久化的间隔时间
acooly.alog.savePeriodSeconds=5
# session的userKey，可多个，直到找到不为空的值(用于单服务应用，可自动从请求获取用户)
acooly.alog.sessionUserKeys=USER_IN_SESSION,SESSION_USER
# alog js收集配置参数
# 启动JS收集服务
acooly.alog.enablePortal=true
# 配置可被收集的站点来源域名. 例如：http://portal.domain.com:8083,https://portal2.domain.com
acooly.alog.allowOrigins=
## alog openapi自动收集App请求行为日志
# 打开OpenApi服务执行后事件监听日志收集
acooly.alog.openApiEnable=true
# OpenApi服务执行后事件监听只收集App请求
acooly.alog.openApiOnlyApp=true
```

>PS: 以上配置为默认值。

#### 3.2.2.埋点配置

组件支持自定义埋点配置，例如需要收集App点击某个按钮（不能通过openApi,因为这个按钮可能调用多个可能被复用的Api）。在启动行为日志服务后，在后台进入: 行为日志 -> 埋点管理

* 你可以定义埋点的：actionKey(埋点名称) -> actionName(中文名) -> actionGroup(对埋点的分组)
* 你可以定义埋点的请求是否收集特点数据和参数（如果是httpRequest,可支持AntPath方式定义需要收集的参数，比如：*）

>特别注意：如果是收集的网站URL最为actionKey,则会自动通过url翻译访问日志中的actionName和actionGoup，并根据参数配置进行数据解析。（支持：JS和springMVC拦截器两种方式）


### 3.3.JS收集

1. 在部署行为日志服务时，打开和配置JS收集特性参数。

	```ini
	# alog js收集配置参数
	# 启动JS收集服务
	acooly.alog.enablePortal=true
	# 配置可被收集的站点来源域名
	acooly.alog.allowOrigins=http://portal.domain.com:8083,https://portal2.domain.com
	```

2. 被收集的网站或微网站中的footer中加入配置，类似百度站点工具集成。

	```javascript
	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
	<script src="${行为日志服务域名}/acooly/alog/acooly.alog.js"></script>
	<script>
	    $(function () {
	        // 注意：被收集的网站的域名需要在后台配置注册
	        // 行为日志收集的服务器域名配置
	        $.acooly.alog.host = "${行为日志服务域名}";
	        $.acooly.alog.log();
	    });
	</script>
	```

	>PS: jquery是必须的依赖，如果你网站已有了引入，这里可以忽略；${行为日志服务域名}需要替换为你部署的行为日志服务的公网地址或域名。

3. 正常启动行为日志服务和被收集的网站服务，访问网站后，登录行为日志后台，访问：行为日志->访问日志 模块查询最新的访问日志（最多延迟5秒，依赖你的配置）

### 3.4.openApi收集

#### 3.4.1.自动收集

通过OpenApi框架的事件机(`AfterServiceExecuteEvent`)制，监听服务执行后置事件收集行为日志。配置如下：

```ini
## alog openapi自动收集App请求行为日志
# 打开OpenApi服务执行后事件监听日志收集
acooly.alog.openApiEnable=true
# OpenApi服务执行后事件监听只收集App请求
acooly.alog.openApiOnlyApp=true
```

#### 3.4.2.埋点收集

日志服务需集成：acooly-portlets-alog-openapi模块，启动后，对外自动提供名称为：alog的openApi服务，你可通过apidoc文档查看。

openApi模式主要应用于App端的行为收集，建议先规划和定义好App端需要收集的行为，并通过后台完成埋点管理，导出埋点列表，交给App端开发人员进行配置开发。


### 3.5.springMVC拦截器收集

拦截器实现：`com.acooly.portlets.alog.core.intergration.AlogSpringMvcInterceptor`，请在目标工程配置拦截路径即可，不推荐该方式，也只能应用于单服务项目

>该模式下，你可以通过@Alog对spring的Controller的方法进行配置注解方式设置日志属性。

#### 3.5.1.自定义收集扩展

Alog的日志收集服务已进行了facade模式封装，可根据需求，自定义扩展和调用集成, Alog服务：`com.acooly.portlets.alog.core.service.AlogService`,在依赖了core模块后，可直接注入：AlogService服务使用。

```java
public interface AlogService {

    /**
     * 通过DTO保存
     *
     * @param actionLogInfo
     */
    void log(ActionLogInfo actionLogInfo);

    /**
     * 根据Http请求解析并保持log
     *
     * @param request
     */
    void log(HttpServletRequest request);

    /**
     * DTO结合请求解析
     *
     * @param actionLogInfo
     * @param request
     */
    void log(ActionLogInfo actionLogInfo, HttpServletRequest request);
}
```

## 4.案例

假设平台A有唯一的后台服务：platform对外通过OpenApi服务，前端有App，H5和Web三端。我们最长久的收集统计整个平台的访问情况（PV,UV和IP），可按一下方式配置：

* Web和H5采用:JS收集模式
* App都是通过OpenApi访问的，在OpenApi服务（platform）上扩展OpenApi，监听：AfterServiceExecuteEvent事件，判断USER_AGENT为移动App，则收集日志，原始请求可以通过ApiContext获得。`com.acooly.portlets.alog.openapi.listener.AlogApiListener`
* 三端都可以定义埋点，通过actionGroup分组来隔离不同的收集场景，然后通过openApi服务：alog定向收集行为日志。

## 数据分析

alog的数据分析需要依赖：`acooly-portlets-alog-analysis`模块，该模块的设计原则为提供部分公共的访问分析能力，然后可根据具体的需要进行定制开发。

### 访问量分析
提供基于alog的全站访问量分析。支持按小时（实时），天，周，月，季分析时间段内全站的访问K线图和柱状图分析。

在依赖`acooly-portlets-alog-analysis`模块后，需要引入`acooly-component-scheduler`的依赖，然后配置访问量分析的每日统计定时任务。在acooly-component-scheduler后台的管理功能添加任务：

* 任务名称: log访问量日归档
* 任务类型: LOCAL（本地）
* 执行类名: com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisService
* 执行方法: analysisVisitsByYesterday
* 任务定时: 0 10 0 * * ?   (每天凌晨00:10，可自行调整)

>PS: 必须添加定时任务，后台的：行为日志->行为分析->访问量分析功能才可用


## 5.demo

* acooly-portlets-alog-core模块下：`resource/static/acooly/alog/alog_test.html`
* acooly-portlets-test模块下：`AlogTestController`和`resource/templates/alog`


