<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_commentAttach_searchform','manage_commentAttach_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_commentAttach_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					评论编码: <input type="text" class="text" size="15" name="search_LIKE_commentNo"/>
				附件类型: <select style="width:80px;height:27px;" name="search_EQ_attachType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allAttachTypes as k,v><option value="${k}">${v}</option></#list></select>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_createTime"/>
					更新时间: <input type="text" class="text" size="15" name="search_LIKE_updateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_commentAttach_searchform','manage_commentAttach_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_commentAttach_datagrid" class="easyui-datagrid" url="/manage/portlets/comment/commentAttach/listJson.html" toolbar="#manage_commentAttach_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">id</th>
			<th field="commentId" sortable="true" sum="true">评论ID</th>
			<th field="commentNo">评论编码</th>
			<th field="attachType" formatter="mappingFormatter">附件类型</th>
			<th field="thumbnail" formatter="contentFormatter">缩略图</th>
			<th field="attachPath" formatter="contentFormatter">附件地址</th>
			<th field="sortTime" sortable="true" sum="true">排序值</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
			<th field="comments" formatter="contentFormatter">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_commentAttach_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_commentAttach_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/portlets/comment/commentAttach/edit.html',id:'{0}',entity:'commentAttach',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/portlets/comment/commentAttach/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/portlets/comment/commentAttach/deleteJson.html','{0}','manage_commentAttach_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_commentAttach_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/comment/commentAttach/create.html',entity:'commentAttach',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/comment/commentAttach/deleteJson.html','manage_commentAttach_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_commentAttach_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_commentAttach_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/portlets/comment/commentAttach/exportXls.html','manage_commentAttach_searchform','评论附件')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/portlets/comment/commentAttach/exportCsv.html','manage_commentAttach_searchform','评论附件')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/portlets/comment/commentAttach/importView.html',uploader:'manage_commentAttach_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
