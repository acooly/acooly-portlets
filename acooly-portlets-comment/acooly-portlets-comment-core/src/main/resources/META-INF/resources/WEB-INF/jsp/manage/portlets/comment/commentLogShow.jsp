<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>ID:</th>
		<td>${commentLog.id}</td>
	</tr>					
	<tr>
		<th width="25%">所属评论:</th>
		<td>${commentLog.commentId}</td>
	</tr>					
	<tr>
		<th>操作人:</th>
		<td>${commentLog.userNo}</td>
	</tr>					
	<tr>
		<th>操作人用户名:</th>
		<td>${commentLog.userName}</td>
	</tr>					
	<tr>
		<th>操作类型:</th>
		<td>${commentLog.actionType.message}</td>
	</tr>					
	<tr>
		<th>操作IP:</th>
		<td>${commentLog.actionIp}</td>
	</tr>					
	<tr>
		<th>相关内容:</th>
		<td>${commentLog.actionContent}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${commentLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td><fmt:formatDate value="${commentLog.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${commentLog.comments}</td>
	</tr>					
</table>
</div>
