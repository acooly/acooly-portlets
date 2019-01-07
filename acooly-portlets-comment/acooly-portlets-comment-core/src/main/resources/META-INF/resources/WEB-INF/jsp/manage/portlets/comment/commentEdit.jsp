<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_comment_editform" action="${pageContext.request.contextPath}/manage/portlets/comment/comment/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="comment" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">父ID：</th>
				<td><input type="text" name="parentId" size="48" placeholder="请输入父ID..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
			<tr>
				<th>path：</th>
				<td><input type="text" name="path" size="48" placeholder="请输入path..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
			<tr>
				<th>业务类型：</th>
				<td><input type="text" name="busiType" size="48" placeholder="请输入业务类型..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>业务标志：</th>
				<td><input type="text" name="busiKey" size="48" placeholder="请输入业务标志..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>评论人编号：</th>
				<td><input type="text" name="userNo" size="48" placeholder="请输入评论人编号..." class="easyui-validatebox text" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>评论人用户名：</th>
				<td><input type="text" name="userName" size="48" placeholder="请输入评论人用户名..." class="easyui-validatebox text" data-options="validType:['length[1,45]']"/></td>
			</tr>					
			<tr>
				<th>标题：</th>
				<td><input type="text" name="title" size="48" placeholder="请输入标题..." class="easyui-validatebox text" data-options="validType:['length[1,45]']"/></td>
			</tr>					
			<tr>
				<th>评论内容：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入评论内容..." style="width:300px;" name="content" class="easyui-validatebox" data-options="validType:['length[1,1024]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>点赞数：</th>
				<td><input type="text" name="thumbsup" size="48" placeholder="请输入点赞数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]']"/></td>
			</tr>					
			<tr>
				<th>是否置顶：</th>
				<td><select name="sticky" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" >
					<c:forEach items="${allStickys}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
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
				<th>comments：</th>
				<td><input type="text" name="comments" size="48" placeholder="请输入comments..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
