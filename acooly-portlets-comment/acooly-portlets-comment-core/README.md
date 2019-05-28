<!-- title: 评论组件 -->
<!-- type: portlets -->
<!-- author: zhangpu -->
评论组件
====
 
acooly-portlets-comment
----

## 简介
用于互联网业务中常见业务的SNS评论功能，通过传入业务类型和业务Key建议与被评论对象的关联关系，实现SNS评论的核心功能封装。

## 特性

* 常规评论功能，包括：评论，复评，点赞等
* 支持与业务层的解耦，通过外部定义和传入busiType和busiKey实现业务与评论的关联
* 支持后台管理员编辑处理评论。包括：编辑和禁用等。
* 支持本地单项目集成，openapi服务和dubbo服务多种方式部署和应用
* 支持附件（图片/视频）[2019-05-28]

## 集成

通用acooly组件集成方式。在引入依赖坐标后，通过 `acooly.portlets.comment.enable=true|false`控制启用和停用

## 应用

### 单服务应用

引入核心依赖

```xml
	<dependency>
	    <groupId>com.acooly</groupId>
	    <artifactId>acooly-portlets-comment-core</artifactId>
	    <version>${acooly.version}</version>
	</dependency>
```
>ps: 最新acooly.version=4.2.0-SNAPSHOT

服务层核心接口：com.acooly.portlets.comment.core.service.CommentService
在你的应用层可注入并直接应用

```java
public interface CommentService {


    /**
     * 评论
     */
    CommentInfo comment(CommentBaseInfo commentBaseInfo);

    /**
     * 点赞
     */
    Integer thumbsup(Long commentId);

    /**
     * 有效评论分页查询
     * <p>
     * 1、查询有效评论
     * 2、根据业务类型和业务key查询
     * 3、根据置顶和时间倒叙排序
     * 4、预留其他查询参数和排序参数（都可为空）
     *
     * @param pageInfo   [必选] 分页参数
     * @param busiKey    [必选] 被评论的业务对象ID
     * @param busiType   [可选] 业务类型（默认内置: DEFAULT）
     * @param map        [可选] 扩展条件（可覆盖内置）
     * @param sortMap    [可选] 扩展排序（可覆盖内置）
     * @param queryChild [可选] 是否查询子评论（默认为：true）
     * @return
     */
    PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String busiKey, String busiType,
                                Map<String, Object> map, Map<String, Boolean> sortMap, Boolean queryChild);

    default PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String busiKey, String busiType) {
        return query(pageInfo, busiKey, busiType, null, null, null);
    }
}

```

### 服务+openapi服务应用

1、服务器端项目

包含core和openapi的服务接口实现

```xml
	<dependency>
	    <groupId>com.acooly</groupId>
	    <artifactId>acooly-portlets-comment-openapi</artifactId>
	    <version>${acooly.version}</version>
	</dependency>
```
>ps: acooly-portlets-comment-openapi会关联依赖acooly-portlets-comment-core，所以不用单独再引用。


2、客户端项目

```xml
	<!-- cmment组件的openapi接口的message定义 -->
	<dependency>
	    <groupId>com.acooly</groupId>
	    <artifactId>acooly-portlets-comment-client</artifactId>
	    <version>${acooly.version}</version>
	</dependency>

	<!-- OpenAPI的客户端工具 -->
	<dependency>
	    <groupId>com.acooly</groupId>
	    <artifactId>openapi-framework-client</artifactId>
	    <version>${acooly.version}</version>
	</dependency>
```

3、接口服务

* [废弃]评论接口：CommentApiService （兼容可用）
* 评论发布：CommentPublishApiService (commentPublish)
* 评论点赞：CommentThumbsupApiService (commentThumbsup)
* 评论详情：CommentInfoApiService (commentInfo)
* 评论查询：CommentListApiService (commentList)

### 扩展
1、评论发布成功事件

com.acooly.portlets.comment.core.service.event.CommentSuccessEvent

2、评论操作（点赞/举报等）成功事件

com.acooly.portlets.comment.core.service.event.CommentActionSuccessEvent
