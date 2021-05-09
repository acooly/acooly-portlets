<#if ssoEnable><#include "/manage/common/ssoInclude.ftl"></#if>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
        <form id="manage_successStory_searchform" class="form-inline ac-form-search" onsubmit="return false">
            <div class="form-group">
                <label class="col-form-label">案例名称：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_title"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">案例类型：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_type"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">行业：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_industry"/>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_successStory_searchform','manage_successStory_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
        </form>
    </div>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false">
        <table id="manage_successStory_datagrid" class="easyui-datagrid" url="/manage/portlets/successtory/successStory/listJson.html" toolbar="#manage_successStory_toolbar" fit="true" border="false" fitColumns="false"
                pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
            <thead>
            <tr>
                <th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
                <th field="logo" formatter="successtoryLogoFormatter">图标</th>
                <th field="title">案例名称</th>
                <th field="discn" width="300" formatter="contentFormatter">案例简介</th>
                <th field="type">案例类型</th>
                <th field="industry">行业</th>
                <th field="website" formatter="successtoryLinkFormatter">网址</th>
                <th field="onlineDate" formatter="dateFormatter">上线日期</th>
<#--                <th field="createTime" formatter="dateTimeFormatter">创建时间</th>-->
                <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_successStory_action',value,row)}">动作</th>
            </tr>
            </thead>
        </table>

        <!-- 每行的Action动作模板 -->
        <div id="manage_successStory_action" style="display: none;">
            <a onclick="$.acooly.framework.edit({url:'/manage/portlets/successtory/successStory/edit.html',id:'{0}',entity:'successStory',width:800,height:600});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.move('/manage/portlets/successtory/successStory/topJson.html','{0}','manage_successStory_datagrid');" href="#" title="置顶"><i class="fa fa fa-chevron-circle-up fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.move('/manage/portlets/successtory/successStory/upJson.html','{0}','manage_successStory_datagrid');" href="#" title="上移动"><i class="fa fa-arrow-circle-up fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.show('/manage/portlets/successtory/successStory/show.html?id={0}',800,600);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.remove('/manage/portlets/successtory/successStory/deleteJson.html','{0}','manage_successStory_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
        </div>

        <!-- 表格的工具栏 -->
        <div id="manage_successStory_toolbar">
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/successtory/successStory/create.html',entity:'successStory',width:800,height:600})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/successtory/successStory/deleteJson.html','manage_successStory_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
<#--            <a href="#" class="easyui-menubutton" data-options="menu:'#manage_successStory_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>-->
<#--            <div id="manage_successStory_exports_menu" style="width:150px;">-->
<#--                <div onclick="$.acooly.framework.exports('/manage/portlets/successtory/successStory/exportXls.html','manage_successStory_searchform','p_success_story')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>-->
<#--                <div onclick="$.acooly.framework.exports('/manage/portlets/successtory/successStory/exportCsv.html','manage_successStory_searchform','p_success_story')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>-->
<#--            </div>-->
<#--            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/portlets/successtory/successStory/importView.html',uploader:'manage_successStory_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>-->
        </div>
    </div>
    <script type="text/javascript">

        function successtoryLogoFormatter(value, row, index, data) {
            let url = row.logo;
            if (!url || url == '') return null;
            if (data && data.data && data.data.serverRoot) {
                url = data.data.serverRoot + url;
            }
            let html = "<div style='display: block;height: 40px;line-height: 40px;'><a href='javascript:;' onclick=\"$.acooly.file.play('" + url + "')\"><img src='" + url + "' style='width: 100px;'></a></div>"
            return html;
        }

        function successtoryLinkFormatter(value) {
            let url = value;
            if (!url || url == '')
                return '';
            let label = url;
            let html = '<a href="' + url + '" title="' + url + '" target="_blank">' + label + '</a>';
            return html;
        }

        $(function () {
            $.acooly.framework.initPage('manage_successStory_searchform', 'manage_successStory_datagrid');
        });
    </script>
</div>
