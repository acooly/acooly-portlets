<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_successStory_editform" class="form-horizontal" action="/manage/portlets/successtory/successStory/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post" enctype="multipart/form-data">
		<@jodd.form bean="successStory" scope="request">
        <input name="id" type="hidden" />
		<div class="card-body">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">案例名称</label>
				<div class="col-sm-10">
					<input type="text" name="title" placeholder="请输入案例名称..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">案例简介</label>
				<div class="col-sm-10">
					<textarea rows="5" cols="40" placeholder="请输入案例简介..." name="discn" class="easyui-validatebox form-control" data-options="validType:['text','length[1,255]'],required:true"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">上线日期</label>
				<div class="col-sm-10">
					<input type="text" name="onlineDate" placeholder="请选择上线日期..." class="easyui-validatebox form-control"  value="<#if successStory.onlineDate??>${successStory.onlineDate?string('yyyy-MM-dd')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">案例类型</label>
				<div class="col-sm-10">
					<input type="text" name="type" placeholder="请输入项目类型..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,16]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">行业</label>
				<div class="col-sm-10">
					<input type="text" name="industry" placeholder="请输入行业..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,16]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">图标</label>
				<div class="col-sm-10">
						<#if successStory != null && successStory.logo != ''>
							<div class="row col-form-content">
								<div class="col-sm-6"><a href="javascript:;" onclick="$.acooly.file.play('${serverRoot}${successStory.logo}');">查看</a></div>
								<div class="col-sm-6" style="text-align: right;"><a href="javascript:;" onclick="$('#manage_successStory_logo_container').toggle();">重新上传</a></div>
							</div>
						</#if>
						<div class="custom-file" id="manage_successStory_logo_container" <#if successStory != null && successStory.logo != ''>style="display: none;"</#if>>
							<input type="file" name="logo_uploadFile" class="custom-file-input">
							<label class="custom-file-label">请选择上传的文件</label>
						</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">网址</label>
				<div class="col-sm-10">
					<input type="text" name="website" placeholder="请输入网址..." class="easyui-validatebox form-control" data-inputmask="'alias':'url'" data-mask data-options="validType:['url','length[1,128]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">备注</label>
				<div class="col-sm-10">
					<input type="text" name="comments" placeholder="请输入备注..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,128]']"/>
				</div>
			</div>
        </div>
      </@jodd.form>
    </form>
</div>
