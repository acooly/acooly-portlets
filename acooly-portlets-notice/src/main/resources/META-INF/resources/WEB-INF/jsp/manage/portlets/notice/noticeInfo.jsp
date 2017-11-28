<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_noticeInfo_searchform','manage_noticeInfo_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_noticeInfo_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					消息标题: <input type="text" class="text" size="15" name="search_LIKE_title"/>
				内容类型: <select style="width:80px;height:27px;" name="search_EQ_contentType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allContentTypes}"><option value="${e.key}" ${param.search_EQ_contentType == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					定时发送时间: <input size="15" class="text" id="search_GTE_schedulerTime" name="search_GTE_schedulerTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_schedulerTime" name="search_LTE_schedulerTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					发送时间: <input size="15" class="text" id="search_GTE_sendTime" name="search_GTE_sendTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_sendTime" name="search_LTE_sendTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					发送人: <input type="text" class="text" size="15" name="search_LIKE_sender"/>
					发送总数: <input type="text" class="text" size="15" name="search_EQ_sendTotal"/>
					已读总数: <input type="text" class="text" size="15" name="search_EQ_receivedTotal"/>
				状态: <select style="width:80px;height:27px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStatuss}"><option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option></c:forEach></select>
				消息类型: <select style="width:80px;height:27px;" name="search_EQ_type" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allTypes}"><option value="${e.key}" ${param.search_EQ_type == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					更新时间: <input size="15" class="text" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_noticeInfo_searchform','manage_noticeInfo_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_noticeInfo_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/portlets/notice/noticeInfo/listJson.html" toolbar="#manage_noticeInfo_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">ID</th>
			<th field="title">消息标题</th>
			<th field="content">消息内容</th>
			<th field="contentType" formatter="mappingFormatter">内容类型</th>
			<th field="context">会话（附带的自定义结构体）</th>
		    <th field="schedulerTime" formatter="dateTimeFormatter">定时发送时间</th>
		    <th field="sendTime" formatter="dateTimeFormatter">发送时间</th>
			<th field="sender">发送人</th>
			<th field="sendTotal" >发送总数</th>
			<th field="receivedTotal" >已读总数</th>
			<th field="status" formatter="mappingFormatter">状态</th>
			<th field="type" formatter="mappingFormatter">消息类型</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
			<th field="comments">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_noticeInfo_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_noticeInfo_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/portlets/notice/noticeInfo/edit.html',id:'{0}',entity:'noticeInfo',width:500,height:400});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/portlets/notice/noticeInfo/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/portlets/notice/noticeInfo/deleteJson.html','{0}','manage_noticeInfo_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_noticeInfo_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/notice/noticeInfo/create.html',entity:'noticeInfo',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/notice/noticeInfo/deleteJson.html','manage_noticeInfo_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_noticeInfo_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_noticeInfo_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/portlets/notice/noticeInfo/exportXls.html','manage_noticeInfo_searchform','公告消息')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/portlets/notice/noticeInfo/exportCsv.html','manage_noticeInfo_searchform','公告消息')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/portlets/notice/noticeInfo/importView.html',uploader:'manage_noticeInfo_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
