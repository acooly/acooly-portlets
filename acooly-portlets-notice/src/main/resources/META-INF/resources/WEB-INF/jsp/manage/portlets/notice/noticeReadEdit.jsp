<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_noticeRead_editform" action="${pageContext.request.contextPath}/manage/portlets/notice/noticeRead/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="noticeRead" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">接收人：</th>
				<td><input type="text" name="receiver" size="48" placeholder="请输入接收人..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>广播已读：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入广播已读..." style="width:300px;" name="messageRead" class="easyui-validatebox" data-options="validType:['length[1,2048]']"></textarea></td>
			</tr>					
			<tr>
				<th>群发未读：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入群发未读..." style="width:300px;" name="messageUnread" class="easyui-validatebox" data-options="validType:['length[1,2048]']"></textarea></td>
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
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
