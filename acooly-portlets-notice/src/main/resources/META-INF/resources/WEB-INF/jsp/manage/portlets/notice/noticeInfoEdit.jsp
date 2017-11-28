<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_noticeInfo_editform" action="${pageContext.request.contextPath}/manage/portlets/notice/noticeInfo/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="noticeInfo" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">消息标题：</th>
				<td><input type="text" name="title" size="48" placeholder="请输入消息标题..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>消息内容：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入消息内容..." style="width:300px;" name="content" class="easyui-validatebox" data-options="validType:['length[1,1024]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>内容类型：</th>
				<td><select name="contentType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allContentTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>会话（附带的自定义结构体）：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入会话（附带的自定义结构体）..." style="width:300px;" name="context" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>定时发送时间：</th>
				<td><input type="text" name="schedulerTime" size="20" placeholder="请输入定时发送时间..." class="easyui-validatebox text" value="<fmt:formatDate value="${noticeInfo.schedulerTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /></td>
			</tr>					
			<tr>
				<th>发送时间：</th>
				<td><input type="text" name="sendTime" size="20" placeholder="请输入发送时间..." class="easyui-validatebox text" value="<fmt:formatDate value="${noticeInfo.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /></td>
			</tr>					
			<tr>
				<th>发送人：</th>
				<td><input type="text" name="sender" size="48" placeholder="请输入发送人..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>发送总数：</th>
				<td><input type="text" name="sendTotal" size="48" placeholder="请输入发送总数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]']"/></td>
			</tr>					
			<tr>
				<th>已读总数：</th>
				<td><input type="text" name="receivedTotal" size="48" placeholder="请输入已读总数..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]']"/></td>
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
				<th>消息类型：</th>
				<td><select name="type" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allTypes}" var="e">
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
