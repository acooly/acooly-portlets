<div class="card-body">
	<dl class="row">
		<dt class="col-sm-2">ID:</dt>
		<dd class="col-sm-10">${successStory.id}</dd>
		<dt class="col-sm-2">案例名称:</dt>
		<dd class="col-sm-10">${successStory.title}</dd>
		<dt class="col-sm-2">案例简介:</dt>
		<dd class="col-sm-10">${successStory.discn}</dd>
		<dt class="col-sm-2">上线日期:</dt>
		<dd class="col-sm-10"><#if successStory.onlineDate??>${successStory.onlineDate?string('yyyy-MM-dd')}</#if></dd>
		<dt class="col-sm-2">案例类型:</dt>
		<dd class="col-sm-10">${successStory.type}</dd>
		<dt class="col-sm-2">行业:</dt>
		<dd class="col-sm-10">${successStory.industry}</dd>
		<dt class="col-sm-2">图标:</dt>
		<dd class="col-sm-10"><#if successStory.logo??><img src="${serverRoot}${successStory.logo}" style="max-width: 120px;"></#if></dd>
		<dt class="col-sm-2">网址:</dt>
		<dd class="col-sm-10">${successStory.website}</dd>
		<dt class="col-sm-2">备注:</dt>
		<dd class="col-sm-10">${successStory.comments}</dd>
		<dt class="col-sm-2">创建时间:</dt>
		<dd class="col-sm-10">${successStory.createTime?string('yyyy-MM-dd HH:mm:ss')}</dd>
		<dt class="col-sm-2">修改时间:</dt>
		<dd class="col-sm-10">${successStory.updateTime?string('yyyy-MM-dd HH:mm:ss')}</dd>
	</dl>
</div>
