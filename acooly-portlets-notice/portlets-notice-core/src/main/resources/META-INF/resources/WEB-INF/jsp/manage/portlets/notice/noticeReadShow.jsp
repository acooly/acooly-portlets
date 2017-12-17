<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>ID:</th>
		<td>${noticeRead.id}</td>
	</tr>					
	<tr>
		<th width="25%">接收人:</th>
		<td>${noticeRead.receiver}</td>
	</tr>					
	<tr>
		<th>广播已读:</th>
		<td>${noticeRead.messageRead}</td>
	</tr>					
	<tr>
		<th>群发未读:</th>
		<td>${noticeRead.messageUnread}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${noticeRead.status.message}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${noticeRead.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td><fmt:formatDate value="${noticeRead.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${noticeRead.comments}</td>
	</tr>					
</table>
</div>
