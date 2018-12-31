<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<script type="text/javascript">
	function test(args){
		var values = args[0];
		var type;
		var sendConfirm= false;


		try{
            for(i = 0;i < values.length;i ++){
                console.log(JSON.stringify(values));
                if(values[i].name=="type"){
                    type = values[i].value;
                }
            }
		}
		catch (err){
		    console.error(err);
		}

	    if(type =="broadcast") {
            var confirm = $.messager.confirm("发送确认广播", "您正在发送广播,发送后<font style='color:red;font-weight: 200'>所有用户将会收到消息</font>，请慎重操作，确认发送么？", function (r) {
                if (r) {
                    sendConfirm = true;
                }
            });

            console.log(confirm);
        }
        else{
            sendConfirm = true;
		}

        console.log(sendConfirm);

        return sendConfirm;
	}
</script>

<div>
    <form id="manage_noticeInfo_editform" action="${pageContext.request.contextPath}/manage/portlets/notice/noticeInfo/push.html" method="post">
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
				<th>会话（附带的自定义结构体）json格式：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入会话（附带的自定义结构体）..." style="width:300px;" name="context" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>
			<tr>
				<th>消息类型：</th>
				<td><select name="type" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<option value="">--请选择--</option>
					<c:forEach items="${allTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>接收人(多个使用逗号分隔)：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入消息接收人..." style="width:300px;" name="receiver" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>
			<c:if test="{pushProvider == 'UMENG'}">
				<tr>
					<th>设备类型：</th>
					<td><select name="deviceType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
						<c:forEach items="${allDeviceTypes}" var="e">
							<option value="${e.key}">${e.value}</option>
						</c:forEach>
					</select></td>
				</tr>
			</c:if>
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
