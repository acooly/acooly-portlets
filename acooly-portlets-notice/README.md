## 1.组件介绍
该组件用来向app推送消息使用，支持多种推送渠道：极光、友盟
### 1.1 支持功能
1. 利用极光、友盟推送渠道向app端推送消息
2. app内查看历史消息，并标识某一条消息是否已读
3. 推送消息按业务自定义的分组进行分类显示与查询

## 2.使用说明
### 2.1 配置项说明
`acooly.portlets.notice.enable`   是否需要启用该组件
`acooly.portlets.notice.pushProvider` 推送渠道：`JPUSH、UMENG`
`acooly.portlets.notice.gateway` 推送api地址
`acooly.portlets.notice.appKey` 推送应用id，在渠道方后台配置
`acooly.portlets.notice.masterSecret` 应用密钥，在渠道方后台生成 
`acooly.portlets.notice.push` 是否开启推送功能，默认为推送。只有在该标识为`true`时才会进行消息推送

### 2.2推荐配置
```
acooly.portlets.notice.enable=true
acooly.portlets.notice.push=true
acooly.portlets.notice.gateway=https://api.jpush.cn/v3/push
acooly.portlets.notice.pushProvider=JPUSH
acooly.portlets.notice.appKey=ba7797dd3f8ddbe03b63be04
acooly.portlets.notice.masterSecret=a850862aa8a556912c6471b5
```
### 2.3注意事项
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
	NoticeInfo readNotice (String receiver, Long noticeId);
}
```
### 3.3 代码
1. 我想要向指定用户推送app消息：
```
NoticeMessage noticeMessage = new NoticeMessage ();
noticeMessage.setPushNo (Ids.getDid ());
noticeMessage.setPush (false);
noticeMessage.setTitle ("测试消息");
noticeMessage.setContent ("这是消息、消息、消息。");
noticeMessage.setPush (true);

Map<String,Object> context = new HashMap<> ();
context.put ("customerId","12312312312");
context.put ("userName","刘斌");
context.put ("realName","刘斌");

noticeMessage.setContext (context);

noticeService.group (noticeMessage, Lists.newArrayList ("yanjun89"));

```
2. 我要向所有app用户都推送消息
```
NoticeMessage noticeMessage = new NoticeMessage ();
noticeMessage.setPushNo (Ids.getDid ());
noticeMessage.setPush (false);
noticeMessage.setTitle ("测试广播");
noticeMessage.setContent ("这是广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播。");
noticeMessage.setPush (true);

noticeService.broadcast (noticeMessage);

```
3. 用户A登录app并且查看自己的未读消息

```
NoticeInfo noticeInfo = noticeQueryService.readNotice ("用户标识，通常是alias", 消息id);

```
4. 分页查询消息

```
Map<String,Object> params = new HashMap<> ();
		
PageInfo pager = new PageInfo ();

PageInfo<PageableNoticeInfo> pageInfo = noticeQueryService.pageQuery ("yanjun89", pager, params, null);
``` 


  