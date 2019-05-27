<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_commentAttach_editform" action="/manage/portlets/comment/commentAttach/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="commentAttach" scope="request">
        <input name="id" type="hidden" />
        <table class="editForm" width="100%">
			<tr>
				<th width="25%">评论ID：</th>
				<td><input type="text" name="commentId" placeholder="请输入评论ID..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]'],required:true"/></td>
			</tr>					
			<tr>
				<th>评论编码：</th>
				<td><input type="text" name="commentNo" placeholder="请输入评论编码..." class="easyui-validatebox" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>附件类型：</th>
				<td><select name="attachType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allAttachTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>缩略图：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入缩略图..." name="thumbnail" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>附件地址：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入附件地址..." name="attachPath" class="easyui-validatebox" data-options="validType:['length[1,128]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>排序值：</th>
				<td><input type="text" name="sortTime" placeholder="请输入排序值..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
