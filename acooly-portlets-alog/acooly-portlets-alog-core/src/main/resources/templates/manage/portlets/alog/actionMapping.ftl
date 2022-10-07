<#if ssoEnable><#include "/manage/common/ssoInclude.ftl"></#if>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
        <form id="manage_actionMapping_searchform" class="form-inline ac-form-search" onsubmit="return false">
            <div class="form-group">
                <label class="col-form-label">行为类型：</label>
                <select name="search_EQ_mappingType" class="form-control input-sm select2bs4">
                    <option value="">所有</option>
                    <#list allMappingTypes as k,v><option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">行为Key：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_actionKey"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">行为分组：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_actionGroup"/>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_actionMapping_searchform','manage_actionMapping_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
        </form>
    </div>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false">
        <table id="manage_actionMapping_datagrid" class="easyui-datagrid" url="/manage/portlets/alog/actionMapping/listJson.html"
               toolbar="#manage_actionMapping_toolbar" fit="true" border="false" fitColumns="false"
               pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
            <thead>
            <tr>
                <th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
                <th field="id" sum="true">主键</th>
                <th field="actionKey">行为Key</th>
                <th field="actionName">行为名称</th>
                <th field="actionGroup">行为分组</th>
                <th field="params">参数</th>
                <th field="mappingType" formatter="mappingFormatter">映射类型</th>
                <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
                <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
                <th field="comments">备注</th>
                <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_actionMapping_action',value,row)}">动作</th>
            </tr>
            </thead>
        </table>

        <!-- 每行的Action动作模板 -->
        <div id="manage_actionMapping_action" style="display: none;">
            <a onclick="$.acooly.framework.edit({url:'/manage/portlets/alog/actionMapping/edit.html',id:'{0}',entity:'actionMapping',width:500,height:600});" href="#" title="编辑"><i
                        class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.show('/manage/portlets/alog/actionMapping/show.html?id={0}',500,600);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.remove('/manage/portlets/alog/actionMapping/deleteJson.html','{0}','manage_actionMapping_datagrid');" href="#" title="删除"><i
                        class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
        </div>

        <!-- 表格的工具栏 -->
        <div id="manage_actionMapping_toolbar">
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/alog/actionMapping/create.html',entity:'actionMapping',width:500,height:470})"><i
                        class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/alog/actionMapping/deleteJson.html','manage_actionMapping_datagrid')"><i
                        class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
            <a href="#" class="easyui-menubutton" data-options="menu:'#manage_actionMapping_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
            <div id="manage_actionMapping_exports_menu" style="width:150px;">
                <div onclick="$.acooly.framework.exports('/manage/portlets/alog/actionMapping/exportXls.html','manage_actionMapping_searchform','访问映射')"><i
                            class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel
                </div>
                <div onclick="$.acooly.framework.exports('/manage/portlets/alog/actionMapping/exportCsv.html','manage_actionMapping_searchform','访问映射')"><i
                            class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV
                </div>
            </div>
            <a href="#" class="easyui-linkbutton" plain="true"
               onclick="$.acooly.framework.imports({url:'/manage/portlets/alog/actionMapping/importView.html',uploader:'manage_actionMapping_import_uploader_file'});"><i
                        class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_actionMapping_searchform', 'manage_actionMapping_datagrid');
        });
    </script>
</div>
