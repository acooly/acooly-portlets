<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
	<tr>
		<th style="width: 20%;">ID:</th>
		<td>${fqa.id}</td>
	</tr>
    <tr>
        <th>分类:</th>
        <td>${fqa.typeName}</td>
		<th>作者:</th>
		<td>${fqa.author}</td>
	</tr>					

	<tr>
		<th>访问量:</th>
		<td>${fqa.hits}</td>
		<th>状态:</th>
		<td>${fqa.status.message()}</td>
		<th></th>
		<td></td>
	</tr>
						
	<tr>
		<th>创建时间:</th>
		<td>${fqa.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
		<th>更新时间:</th>
		<td>${fqa.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	</tr>
	<tr>
		<th>备注:</th>
		<td>${fqa.comments}</td>
	</tr>	
	<tr>
		<th>问题:</th>
		<td  colspan="3" style="color:red;font-weight:bold;font-size:17px;">${fqa.question}</td>
	</tr>				
	<tr>
		<th >答案:</th>
		<td  colspan="3"></td>
	</tr>
	<tr>
		<th ></th>
		<td  colspan="3">${fqa.answer}</td>
	</tr>					
</table>
</div>
