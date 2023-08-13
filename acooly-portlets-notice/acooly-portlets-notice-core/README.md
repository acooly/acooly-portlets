<!-- title: 消息推送组件 -->
<!-- type: portlets -->
<!-- author: zhangpu -->
<!-- date: 2018-12-28 -->
## 1.组件介绍
该组件用来向app推送消息使用，支持多种推送渠道：极光、友盟。可结构性扩展。

### 1.1 特性说明
1. 利用极光、友盟推送渠道向app端推送消息（集成封装提供方的能力，并提供数据持久化管理）
2. app内查看历史消息，并标识某一条消息是否已读
3. 以用户身份（用户标志）查看消息列表，并标记已读未读
4. 消息支付广播和定向发送（单发，群发）
5. 推送消息按业务自定义的分组进行分类显示与查询（customGroup）
6. 支持dubbo分布式


## 2.使用说明
### 2.1 配置说明

```
#################### 公共配置 ###################
#是否需要启用该组件
acooly.portlets.notice.enable
#推送渠道:JPUSH、UMENG
acooly.portlets.notice.pushProvider
#推送api地址
acooly.portlets.notice.gateway
#是否开启推送功能，默认为推送。只有在该标识为true时才会进行消息推送
acooly.portlets.notice.push

#################### jpush ###################
#使用jpush做推送时，推送应用id，在渠道方后台配置
acooly.portlets.notice.jpush.appKey
#使用jpush做推送时，应用密钥，在渠道方后台生成
acooly.portlets.notice.jpush.masterSecret

#################### 友盟 ###################
#使用友盟推送时 安卓应用id
acooly.portlets.notice.umeng.androidAppKey
#使用友盟推送时 安卓应用密钥
acooly.portlets.notice.umeng.androidMasterSecret
#使用友盟推送时 IOS应用id
acooly.portlets.notice.umeng.iosAppKey
#使用友盟推送时 IOS应用密钥
acooly.portlets.notice.umeng.iosMasterSecret
```

### 2.2 注意事项
1.ios app只有在online 环境下才会推送消息到生产环境，其他环境均推送测试环境
2.安卓 app在app端会收到推送的 message 和 notification 安卓app需要屏蔽掉notification默认的显示方式

## 3.代码示例

### 3.1 核心消息发送api
```
public interface NoticeService {

    /**
     * 群发
     *
     * @param noticeMessage
     * @param targets
     */
    void group(NoticeMessage noticeMessage, List<String> targets);

    /**
     * 广播
     *
     * @param noticeMessage
     */
    void broadcast(NoticeMessage noticeMessage);
}

```
### 3.2 核心查询api

```
public interface NoticeQueryService {
	
	/**
	 * 分页查询消息 默认按照消息的发送时间降序
	 *
	 * @param pager   分页器
	 * @param params  查询参数
	 * @param orderBy 排序
	 *
	 * @return
	 */
	PageInfo<PageableNoticeInfo> pageQuery (String receiver,PageInfo<NoticeInfo> pager,Map<String, Object> params,
	                                        Map<String, Boolean> orderBy);
	
	
	/**
	 * 阅读消息
	 *
	 * @param
	 *
	 * @return
	 */
	NoticeInfo readNotice (String receiver, Long commentId);
}
```

### 3.3 代码示例

#### 我想要向指定用户推送app消息：

```java
NoticeMessage noticeMessage = new NoticeMessage ();
noticeMessage.setPushNo (Ids.getDid ());
noticeMessage.setPush (false);
noticeMessage.setTitle ("测试消息");
noticeMessage.setContent ("这是消息、消息、消息。");
noticeMessage.setPush (true);
// 自定义消息分类
noticeMessage.setCustomGroup("0001")

Map<String,Object> context = new HashMap<> ();
context.put ("customerId","12312312312");
context.put ("userName","刘斌");
context.put ("realName","刘斌");

noticeMessage.setContext (context);

noticeService.group (noticeMessage, Lists.newArrayList ("yanjun89"));

```

#### 我要向所有app用户都推送消息

```java
NoticeMessage noticeMessage = new NoticeMessage ();
noticeMessage.setPushNo (Ids.getDid ());
noticeMessage.setPush (false);
noticeMessage.setTitle ("测试广播");
noticeMessage.setContent ("这是广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播。");
noticeMessage.setPush (true);

noticeService.broadcast (noticeMessage);

```

#### 用户A登录app并且查看自己的未读消息


```java
NoticeInfo noticeInfo = noticeQueryService.readNotice ("用户标识，通常是alias", 消息id);

```

#### 分页查询消息

```java
Map<String,Object> params = new HashMap<> ();
//只查询某个自定义分类的消息
params.put("EQ_customGroup","0001")
		
PageInfo pager = new PageInfo ();

PageInfo<PageableNoticeInfo> pageInfo = noticeQueryService.pageQuery ("yanjun89", pager, params, null);
``` 

#### 消息分类

如果集成工程需要在后台管理里面选择消息分类进行查询，请实现：`com.acooly.portlets.notice.core.service.MessageGroup`接口，注入到spring容器中即可，后台BOSS界面后自动判断加载，如果未提供实现，则以文本框方式提供查询条件。

案例: acooly-portlets-test中的：`com.acooly.portlets.notice.CustomMessageGroup`

