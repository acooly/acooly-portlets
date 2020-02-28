<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th style="width: 10%;">ID:</th>
		<td>${fqa.id}</td>
	</tr>
    <tr>
        <th>分类:</th>
        <td>${fqa.askTypeName}</td>
    </tr>
	<tr>
		<th>问题:</th>
		<td>${fqa.ask}</td>
	</tr>					
	<tr>
		<th>答案:</th>
		<td>${fqa.question}</td>
	</tr>					
	<tr>
		<th>作者:</th>
		<td>${fqa.author}</td>
	</tr>					

	<tr>
		<th>访问量:</th>
		<td>${fqa.hits}</td>
	</tr>					
	<tr>
		<th>状态:</th>
		<td>${fqa.status.message()}</td>
	</tr>					
	<tr>
		<th>创建时间:</th>
		<td>${fqa.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	</tr>					
	<tr>
		<th>更新时间:</th>
		<td>${fqa.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	</tr>					
	<tr>
		<th>备注:</th>
		<td>${fqa.comments}</td>
	</tr>					
</table>
</div>
