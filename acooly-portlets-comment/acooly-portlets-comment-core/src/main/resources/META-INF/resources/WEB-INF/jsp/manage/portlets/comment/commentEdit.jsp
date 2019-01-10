<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_comment_editform" action="${pageContext.request.contextPath}/manage/portlets/comment/comment/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="comment" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">父ID：</th>
				<td>${comment.parentId}</td>
			</tr>
			<tr>
				<th>业务：</th>
				<td>${comment.busiType}: ${comment.busiKey}</td>
			</tr>
			<tr>
				<th>评论人：</th>
				<td>${comment.userNo}/${comment.userName}</td>
			</tr>					
			<tr>
				<th>评论人IP：</th>
				<td>${comment.userIp}</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>${comment.status.message}</td>
			</tr>
			<c:if test="${comment.title != null}">
			<tr>
				<th>标题：</th>
				<td>${comment.title}</td>
			</tr>
			</c:if>
			<c:if test="${comment.originContent != null}">
				<tr>
					<th>原始评论内容：</th>
					<td>${comment.originContent}</td>
				</tr>
			</c:if>
			<tr>
				<th>评论内容：</th>
				<td><textarea rows="5" placeholder="请输入评论内容..." style="width:340px;" name="content" class="easyui-validatebox"
							  data-options="validType:['length[1,1024]'],required:true"></textarea></td>
			</tr>
			<tr>
				<th>状态：</th>
				<td><select name="status" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" >
					<c:forEach items="${allStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><input type="text" name="comments" size="48" placeholder="请输入备注..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
