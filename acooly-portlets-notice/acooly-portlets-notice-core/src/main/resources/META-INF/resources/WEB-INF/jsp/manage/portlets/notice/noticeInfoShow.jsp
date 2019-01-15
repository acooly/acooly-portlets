<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>ID:</th>
		<td>${noticeInfo.id}</td>
	</tr>					
	<tr>
		<th width="25%">消息标题:</th>
		<td>${noticeInfo.title}</td>
	</tr>					
	<tr>
		<th>消息内容:</th>
		<td>${noticeInfo.content}</td>
	</tr>
	<tr>
		<th>消息分组：</th>
		<td>${noticeInfo.customGroup}</td>
	</tr>
	<tr>
		<th>内容类型:</th>
		<td>${noticeInfo.contentType.message}</td>
	</tr>					
	<tr>
		<th>会话（附带的自定义结构体）:</th>
		<td>${noticeInfo.context}</td>
	</tr>					
	<tr>
		<th>定时发送时间:</th>
		<td><fmt:formatDate value="${noticeInfo.schedulerTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>发送时间:</th>
		<td><fmt:formatDate value="${noticeInfo.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>发送人:</th>
		<td>${noticeInfo.sender}</td>
	</tr>					
	<tr>
		<th>发送总数:</th>
		<td>${noticeInfo.sendTotal}</td>
	</tr>					
	<tr>
		<th>已读总数:</th>
		<td>${noticeInfo.receivedTotal}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${noticeInfo.status.message}</td>
	</tr>					
	<tr>
		<th>消息类型:</th>
		<td>${noticeInfo.type.message}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${noticeInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td><fmt:formatDate value="${noticeInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${noticeInfo.comments}</td>
	</tr>					
</table>
</div>
