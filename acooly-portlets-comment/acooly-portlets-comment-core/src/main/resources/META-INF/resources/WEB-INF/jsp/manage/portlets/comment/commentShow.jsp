<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>ID:</th>
		<td>${comment.id}</td>
	</tr>					
	<tr>
		<th width="25%">父ID:</th>
		<td>${comment.parentId}</td>
	</tr>					
	<tr>
		<th>path:</th>
		<td>${comment.path}</td>
	</tr>					
	<tr>
		<th>业务类型:</th>
		<td>${comment.busiType}</td>
	</tr>					
	<tr>
		<th>业务标志:</th>
		<td>${comment.busiKey}</td>
	</tr>					
	<tr>
		<th>评论人编号:</th>
		<td>${comment.userNo}</td>
	</tr>					
	<tr>
		<th>评论人用户名:</th>
		<td>${comment.userName}</td>
	</tr>					
	<tr>
		<th>标题:</th>
		<td>${comment.title}</td>
	</tr>					
	<tr>
		<th>评论内容:</th>
		<td>${comment.content}</td>
	</tr>					
	<tr>
		<th>点赞数:</th>
		<td>${comment.thumbsup}</td>
	</tr>					
	<tr>
		<th>是否置顶:</th>
		<td>${comment.sticky.message}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${comment.status.message}</td>
	</tr>					
	<tr>
		<th>create_time:</th>
		<td><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>update_time:</th>
		<td><fmt:formatDate value="${comment.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>comments:</th>
		<td>${comment.comments}</td>
	</tr>					
</table>
</div>
