<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th>id:</th>
		<td>${commentAttach.id}</td>
	</tr>					
	<tr>
		<th width="25%">评论ID:</th>
		<td>${commentAttach.commentId}</td>
	</tr>					
	<tr>
		<th>评论编码:</th>
		<td>${commentAttach.commentNo}</td>
	</tr>					
	<tr>
		<th>附件类型:</th>
		<td>${commentAttach.attachType.message()}</td>
	</tr>					
	<tr>
		<th>缩略图:</th>
		<td>${commentAttach.thumbnail}</td>
	</tr>					
	<tr>
		<th>附件地址:</th>
		<td>${commentAttach.attachPath}</td>
	</tr>					
	<tr>
		<th>排序值:</th>
		<td>${commentAttach.sortTime}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td>${commentAttach.createTime}</td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td>${commentAttach.updateTime}</td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${commentAttach.comments}</td>
	</tr>					
</table>
</div>