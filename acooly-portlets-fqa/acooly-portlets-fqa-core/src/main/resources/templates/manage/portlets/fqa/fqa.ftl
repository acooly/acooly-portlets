<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
        <form id="manage_fqa_searchform" onsubmit="return false">
            <table class="tableForm" width="100%">
                <tr>
                    <td align="left">
                        <div>
                            问题分类:
                            <select id="manage_fqa_searchform_askTypeId" class="easyui-combotree" name="askTypeId" style="width:100px;"
                                    data-options="url:'/manage/module/treeType/treeType/loadTree.html?theme=FQA',
                                    loadFilter:function(data){var rows=data.rows; rows.unshift({id:null,text:'所有',subCount:0}); return rows;},
                                    formatter:function(row){var fa='fa-file-o'; if(row.subCount > 0){fa='fa-folder-o';}  return '<i class=\'fa '+fa+'\' aria-hidden=\'true\'></i> ' + row.text;},
                                    onLoadSuccess:manage_fqa_searchform_askType_onLoadSuccess">
                                <option>所有</option>
                            </select>
                            状态: <select style="width:80px;height:27px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox">
                            <option value="">所有</option><#list allStatuss as k,v>
                            <option value="${k}">${v}</option></#list></select>
                            创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                            至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_fqa_searchform','manage_fqa_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false">
        <table id="manage_fqa_datagrid" class="easyui-datagrid" url="/manage/portlets/fqa/fqa/listJson.html" toolbar="#manage_fqa_toolbar" fit="true" border="false" fitColumns="false"
               pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
            <thead>
            <tr>
                <th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
                <th field="id" sortable="true" sum="true">ID</th>
                <th field="ask" formatter="contentFormatter">问题</th>
                <th field="author">作者</th>
                <th field="askTypeName">问题分类</th>
                <th field="hits" sortable="true">访问量</th>
                <th field="status" formatter="mappingFormatter">状态</th>
                <th field="createTime" formatter="dateFormatter">创建时间</th>
                <th field="updateTime" formatter="dateFormatter">更新时间</th>
                <th field="comments">备注</th>
                <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_fqa_action',value,row)}">动作</th>
            </tr>
            </thead>
        </table>

        <!-- 每行的Action动作模板 -->
        <div id="manage_fqa_action" style="display: none;">
            <a onclick="$.acooly.framework.edit({url:'/manage/portlets/fqa/fqa/edit.html',id:'{0}',entity:'fqa',width:800,height:600});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.show('/manage/portlets/fqa/fqa/show.html?id={0}',800,600);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
            <a onclick="$.acooly.framework.remove('/manage/portlets/fqa/fqa/deleteJson.html','{0}','manage_fqa_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
        </div>

        <!-- 表格的工具栏 -->
        <div id="manage_fqa_toolbar">
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/fqa/fqa/create.html',entity:'fqa',width:800,height:600})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/fqa/fqa/deleteJson.html','manage_fqa_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
            <a href="#" class="easyui-menubutton" data-options="menu:'#manage_fqa_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
            <div id="manage_fqa_exports_menu" style="width:150px;">
                <div onclick="$.acooly.framework.exports('/manage/portlets/fqa/fqa/exportXls.html','manage_fqa_searchform','p_fqa')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
                <div onclick="$.acooly.framework.exports('/manage/portlets/fqa/fqa/exportCsv.html','manage_fqa_searchform','p_fqa')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
            </div>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/portlets/fqa/fqa/importView.html',uploader:'manage_fqa_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $.acooly.framework.registerKeydown('manage_fqa_searchform', 'manage_fqa_datagrid');
    });

    function manage_fqa_searchform_askType_onLoadSuccess(row, data) {
        $(".tree-icon,.tree-file").removeClass("tree-file");
        $(".tree-icon,.tree-folder").removeClass("tree-folder-open tree-folder");
        for (var i = 0; i < data.length; i++) {
            if (data[i].id) {
                var node = $("#manage_fqa_searchform_askTypeId").combotree('tree').tree("find", data[i].id);
                $("#manage_fqa_searchform_askTypeId").combotree('tree').tree('expandAll', node.target);//展开所有节点
            }
        }
    }
</script>