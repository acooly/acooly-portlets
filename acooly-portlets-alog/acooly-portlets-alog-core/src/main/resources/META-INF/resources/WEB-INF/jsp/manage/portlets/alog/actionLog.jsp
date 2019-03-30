<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_actionLog_searchform','manage_actionLog_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_actionLog_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					访问Key: <input type="text" class="text" size="15" name="search_LIKE_actionKey"/>
					用户: <input type="text" class="text" size="15" name="search_LIKE_userKey"/>
					平台: <select style="width:80px;height:27px;" name="search_EQ_os" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allOss}"><option value="${e.key}" ${param.search_EQ_os == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					渠道: <select style="width:80px;height:27px;" name="search_EQ_channel" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allChannels}"><option value="${e.key}" ${param.search_EQ_channel == e.key?'selected':''}>${e.value}</option></c:forEach></select>
					IP: <input type="text" class="text" size="15" name="search_LIKE_userIp"/>
					时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_actionLog_searchform','manage_actionLog_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_actionLog_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/portlets/alog/actionLog/listJson.html" toolbar="#manage_actionLog_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">ID</th>
			<th field="actionKey">访问Key</th>
			<th field="actionName">访问名</th>
			<th field="userKey">用户</th>
            <th field="cookies">会话</th>
			<th field="os" formatter="mappingFormatter">平台</th>
			<th field="channel" formatter="mappingFormatter">渠道</th>
			<th field="referer">来源</th>
			<th field="userIp">访问IP</th>
		    <th field="actionTime" formatter="dateTimeFormatter">时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_actionLog_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_actionLog_action" style="display: none;">
      <a onclick="$.acooly.framework.show('/manage/portlets/alog/actionLog/show.html?id={0}',600,600);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_actionLog_toolbar">
      <%--<a href="#" class="easyui-menubutton" data-options="menu:'#manage_actionLog_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>--%>
      <%--<div id="manage_actionLog_exports_menu" style="width:150px;">--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/portlets/alog/actionLog/exportXls.html','manage_actionLog_searchform','访问日志')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>--%>
        <%--<div onclick="$.acooly.framework.exports('/manage/portlets/alog/actionLog/exportCsv.html','manage_actionLog_searchform','访问日志')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>--%>
      <%--</div>--%>
    </div>
  </div>

</div>
