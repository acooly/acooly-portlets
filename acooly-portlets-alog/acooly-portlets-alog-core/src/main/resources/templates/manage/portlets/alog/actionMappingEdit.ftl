<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_actionMapping_editform" class="form-horizontal" action="/manage/portlets/alog/actionMapping/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
        <@jodd.form  bean="actionMapping" scope="request">
            <input name="id" type="hidden"/>
            <div class="card-body">
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">映射类型</label>
                    <div class="col-sm-9">
                        <select name="mappingType" class="form-control select2bs4" data-options="required:true">
                            <#list allMappingTypes as k,v>
                                <option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">行为Key
                        <a title="数据采集埋点的唯一标志" class="easyui-tooltip"><i class="fa fa-info-circle" aria-hidden="true"></i></a>
                    </label>
                    <div class="col-sm-9"><input type="text" name="actionKey" size="48" placeholder="请输入行为Key..." class="easyui-validatebox form-control" data-options="validType:['length[1,128]'],required:true"/></div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">行为名称
                        <a title="数据采集埋点中文名称，以便于理解。" class="easyui-tooltip"><i class="fa fa-info-circle" aria-hidden="true"></i></a>
                    </label>
                    <div class="col-sm-9"><input type="text" name="actionName" size="48" placeholder="请输入行为名称..." class="easyui-validatebox form-control" data-options="validType:['length[1,32]'],required:true"/></div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">行为分组
                        <a title="自定义业务分组，可选" class="easyui-tooltip"><i class="fa fa-info-circle" aria-hidden="true"></i></a>
                    </label>
                    <div class="col-sm-9"><input type="text" name="actionGroup" size="48" placeholder="请输入行为分组..." class="easyui-validatebox form-control" data-options="validType:['length[1,32]']"/></div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">参数
                        <a title="需要的参数的key值，多个使用逗号分隔" class="easyui-tooltip"><i class="fa fa-info-circle" aria-hidden="true"></i></a>
                    </label>
                    <div class="col-sm-9"><input type="text" name="params" size="48" placeholder="需要收集的参数，多个使用逗号分隔..." class="easyui-validatebox form-control" data-options="validType:['length[1,128]']"/></div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">备注</label>
                    <div class="col-sm-9"><input type="text" name="comments" size="48" placeholder="请输入备注..." class="easyui-validatebox form-control" data-options="validType:['length[1,128]']"/></div>
                </div>
            </div>
        </@jodd.form>
    </form>
</div>
