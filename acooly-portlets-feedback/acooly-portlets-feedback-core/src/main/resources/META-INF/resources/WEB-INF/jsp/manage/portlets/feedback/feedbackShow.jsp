<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${feedback.id}</td>
	</tr>					
	<tr>
		<th width="25%">类型:</th>
		<td>${feedback.type.message}</td>
	</tr>					
	<tr>
		<th>标题:</th>
		<td>${feedback.title}</td>
	</tr>					
	<tr>
		<th>内容:</th>
		<td>${feedback.content}</td>
	</tr>
	<tr>
		<th>业务分类:</th>
		<td>${feedback.busiCode} : ${feedback.busiName}</td>
	</tr>
	<tr>
		<th>用户标志:</th>
		<td>${feedback.userKey}</td>
	</tr>					
	<tr>
		<th>用户名:</th>
		<td>${feedback.userName}</td>
	</tr>					
	<tr>
		<th>用户IP:</th>
		<td>${feedback.userIp}</td>
	</tr>					
	<tr>
		<th>联系电话:</th>
		<td>${feedback.phoneNo}</td>
	</tr>					
	<tr>
		<th>联系邮箱:</th>
		<td>${feedback.mail}</td>
	</tr>					
	<tr>
		<th>联系地址:</th>
		<td>${feedback.address}</td>
	</tr>					
	<tr>
		<th>联系信息:</th>
		<td>${feedback.contactInfo}</td>
	</tr>					
	<tr>
		<th>回复内容:</th>
		<td>${feedback.replyContent}</td>
	</tr>					
	<tr>
		<th>回复人:</th>
		<td>${feedback.replyUser}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${feedback.status.message}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td><fmt:formatDate value="${feedback.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>修改时间:</th>
		<td><fmt:formatDate value="${feedback.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${feedback.comments}</td>
	</tr>					
</table>
</div>
