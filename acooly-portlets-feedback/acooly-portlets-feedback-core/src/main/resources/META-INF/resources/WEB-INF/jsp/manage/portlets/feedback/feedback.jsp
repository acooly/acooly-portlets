<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_feedback_searchform','manage_feedback_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_feedback_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                类型: <select style="width:80px;height:27px;" name="search_EQ_type" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allTypes}"><option value="${e.key}" ${param.search_EQ_type == e.key?'selected':''}>${e.value}</option></c:forEach></select>
                用户标志: <input type="text" class="text" size="10" name="search_LIKE_userKey"/>
				电话: <input type="text" class="text" size="10" name="search_LIKE_phoneNo"/>
                回复人: <input type="text" class="text" size="10" name="search_LIKE_replyUser"/>
				状态: <select style="width:80px;height:27px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStatuss}"><option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option></c:forEach></select>
                提交时间: <input size="10" type="text" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至 <input size="10" type="text" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_feedback_searchform','manage_feedback_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_feedback_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/portlets/feedback/feedback/listJson.html" toolbar="#manage_feedback_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">id</th>
            <th field="createTime" formatter="dateTimeFormatter">提交时间</th>
			<th field="type" formatter="mappingFormatter">类型</th>
			<th field="content" formatter="contentFormatter">内容</th>
			<th field="userKey" data-options="formatter:function(v,r,i){ var info = v; if(r.userName != null){info=info+'/'+r.userName;} return info;}">用户</th>
			<th field="phoneNo">联系电话</th>
			<th field="replyContent" formatter="contentFormatter">回复内容</th>
			<th field="replyUser">回复人</th>
			<th field="status" formatter="mappingFormatter">状态</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_feedback_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_feedback_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/portlets/feedback/feedback/edit.html',id:'{0}',entity:'feedback',width:600,height:500,title:'处理'});" href="#" title="处理"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/portlets/feedback/feedback/show.html?id={0}',600,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/portlets/feedback/feedback/deleteJson.html','{0}','manage_feedback_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_feedback_toolbar">
      <%--<a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/portlets/feedback/feedback/create.html',entity:'feedback',width:500,height:400})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>--%>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/portlets/feedback/feedback/deleteJson.html','manage_feedback_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_feedback_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_feedback_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/portlets/feedback/feedback/exportXls.html','manage_feedback_searchform','客户反馈')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/portlets/feedback/feedback/exportCsv.html','manage_feedback_searchform','客户反馈')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
    </div>
  </div>

</div>
