<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_feedback_editform" action="${pageContext.request.contextPath}/manage/portlets/feedback/feedback/handle.html" method="post">
      <jodd:form bean="feedback" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="20%">类型：</th>
				<td>${feedback.type.message}</td>
			</tr>					
			<tr>
				<th>标题：</th>
				<td>${feedback.title}</td>
			</tr>					
			<tr>
				<th>内容：</th>
				<td>${feedback.content}</td>
			</tr>
			<tr>
				<th>业务分类:</th>
				<td>${feedback.busiCode} : ${feedback.busiName}</td>
			</tr>
			<tr>
				<th>回复内容：</th>
				<td><textarea rows="6" cols="40" placeholder="请输入回复内容..." style="width:380px;" name="replyContent" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
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
				<th>用户：</th>
				<td>${feedback.userKey} / ${feedback.userName}</td>
			</tr>
			<tr>
				<th>用户IP：</th>
				<td>${feedback.userIp}</td>
			</tr>					
			<tr>
				<th>联系电话：</th>
				<td>${feedback.phoneNo}</td>
			</tr>
			<tr>
				<th>联系邮箱：</th>
				<td>${feedback.mail}</td>
			</tr>					
			<tr>
				<th>联系地址：</th>
				<td>${feedback.address}</td>
			</tr>					
			<tr>
				<th>联系信息：</th>
				<td>${feedback.contactInfo}</td>
			</tr>					

			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
