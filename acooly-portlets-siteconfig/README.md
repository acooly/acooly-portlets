<!-- title: 网站配置组件 -->
<!-- type: portlets -->
<!-- author: zhangpu -->
<!-- date: 2019-03-27 -->
## 1. 组件介绍
该组件提供通用网站必须的参数配置。

### 2. 特性说明

1. 自动内置常规配置参数，包括：客服热线，服务时间，服务邮箱，服务微博，服务QQ，微信公众号等参数
2. 支持参数缓存配置
3. 后台管理模块管理参数值和对应常量。


## 3. 集成

### 3.1. 模块

```xml
<dependency>
    <groupId>com.acooly</groupId>
    <artifactId>acooly-portlets-siteconfig</artifactId>
    <version>${acooly.version}</version>
</dependency>
```

### 3.2. 组件参数

组件配置参数：

```
# 组件开关
acooly.acooly.siteconfig.enable=true
```

## 4. 应用

### 4.1. 内置参数常量

内置参数集成组件后，自动完成数据准备，并定义好了对应的常量KEY，你需要在后台BOSS中维护对应的参数值。程序中（包括页面端）可直接使用组件内提供的常量表。
`com.acooly.portlets.siteconfig.enums.SiteConfigKeyEnum`

```java
public enum SiteConfigKeyEnum implements Messageable {
    
    serviceTel("serviceTel", "客服热线"),

    serviceWorkHours("serviceWorkHours", "服务时间"),

    serviceEmail("serviceEmail", "服务邮箱"),

    serviceWeibo("serviceWeibo", "服务微博"),

    serviceQQ("serviceQQ", "服务QQ"),

    serviceQQGroup("serviceQQGroup", "服务QQ群"),

    serviceWeChat("serviceWeChat", "微信公众号");
    
    // ...
}
```

