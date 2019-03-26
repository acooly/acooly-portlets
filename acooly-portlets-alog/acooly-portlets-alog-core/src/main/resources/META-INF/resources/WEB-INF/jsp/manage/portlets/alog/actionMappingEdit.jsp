<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_actionMapping_editform" action="${pageContext.request.contextPath}/manage/portlets/alog/actionMapping/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="actionMapping" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">映射类型：</th>
				<td><select name="mappingType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allMappingTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>行为Key：</th>
				<td><input type="text" name="actionKey" size="48" placeholder="请输入行为Key..." class="easyui-validatebox text" data-options="validType:['length[1,128]'],required:true"/></td>
			</tr>					
			<tr>
				<th>行为名称：</th>
				<td><input type="text" name="actionName" size="48" placeholder="请输入行为名称..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>
			<tr>
				<th>行为分组：</th>
				<td><input type="text" name="actionGroup" size="48" placeholder="请输入行为分组..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>
			<tr>
				<th>参数：</th>
				<td><input type="text" name="params" size="48" placeholder="需要收集的参数，多个使用逗号分隔..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><input type="text" name="comments" size="48" placeholder="请输入备注..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
