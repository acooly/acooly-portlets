<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_actionLog_editform" action="${pageContext.request.contextPath}/manage/portlets/alog/actionLog/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="actionLog" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">操作：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入操作..." style="width:300px;" name="actionKey" class="easyui-validatebox" data-options="validType:['length[1,512]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>操作名称：</th>
				<td><input type="text" name="actionName" size="48" placeholder="请输入操作名称..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>URL连接：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入URL连接..." style="width:300px;" name="actionUrl" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
			</tr>					
			<tr>
				<th>用户名：</th>
				<td><input type="text" name="userKey" size="48" placeholder="请输入用户名..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>渠道：</th>
				<td><select name="channel" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allChannels}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>渠道信息：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入渠道信息..." style="width:300px;" name="channelInfo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>渠道版本：</th>
				<td><input type="text" name="channelVersion" size="48" placeholder="请输入渠道版本..." class="easyui-validatebox text" data-options="validType:['length[1,16]']"/></td>
			</tr>					
			<tr>
				<th>访问IP：</th>
				<td><input type="text" name="userIp" size="48" placeholder="请输入访问IP..." class="easyui-validatebox text" data-options="validType:['length[1,16]']"/></td>
			</tr>					
			<tr>
				<th>运营商：</th>
				<td><input type="text" name="isp" size="48" placeholder="请输入运营商..." class="easyui-validatebox text" data-options="validType:['length[1,16]']"/></td>
			</tr>					
			<tr>
				<th>国家：</th>
				<td><input type="text" name="country" size="48" placeholder="请输入国家..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>省：</th>
				<td><input type="text" name="region" size="48" placeholder="请输入省..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>市：</th>
				<td><input type="text" name="city" size="48" placeholder="请输入市..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>经纬度：</th>
				<td><input type="text" name="lntLat" size="48" placeholder="请输入经纬度..." class="easyui-validatebox text" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>点击量：</th>
				<td><input type="text" name="pv" size="48" placeholder="请输入点击量..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]']"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><input type="text" name="comments" size="48" placeholder="请输入备注..." class="easyui-validatebox text" data-options="validType:['length[1,128]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
