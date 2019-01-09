<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_commentLog_editform" action="${pageContext.request.contextPath}/manage/portlets/comment/commentLog/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="commentLog" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">所属评论：</th>
				<td><input type="text" name="commentId" size="48" placeholder="请输入所属评论..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]'],required:true"/></td>
			</tr>					
			<tr>
				<th>操作人：</th>
				<td><input type="text" name="userNo" size="48" placeholder="请输入操作人..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>操作人用户名：</th>
				<td><input type="text" name="userName" size="48" placeholder="请输入操作人用户名..." class="easyui-validatebox text" data-options="validType:['length[1,45]']"/></td>
			</tr>					
			<tr>
				<th>操作类型：</th>
				<td><select name="actionType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allActionTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>操作IP：</th>
				<td><input type="text" name="actionIp" size="48" placeholder="请输入操作IP..." class="easyui-validatebox text" data-options="validType:['length[1,45]']"/></td>
			</tr>					
			<tr>
				<th>相关内容：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入相关内容..." style="width:300px;" name="actionContent" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><input type="text" name="comments" size="48" placeholder="请输入备注..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
