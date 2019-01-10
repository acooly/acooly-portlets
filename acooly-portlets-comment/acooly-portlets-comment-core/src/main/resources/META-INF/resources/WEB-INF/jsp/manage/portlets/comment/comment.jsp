<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_comment_searchform','manage_comment_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_comment_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                父ID: <input type="text" class="text" size="10" name="search_EQ_parentId"/>
                业务标志: <input type="text" class="text" size="10" name="search_LIKE_busiKey"/>
                用户标志: <input type="text" class="text" size="10" name="search_LIKE_userNo"/>
				<%--置顶: <select name="search_EQ_sticky" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStickys}"><option value="${e.key}" ${param.search_EQ_sticky == e.key?'selected':''}>${e.value}</option></c:forEach></select>--%>
				状态: <select name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStatuss}"><option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option></c:forEach></select>
                时间: <input size="10" type="text" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至<input size="10" type="text" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_comment_searchform','manage_comment_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_comment_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/portlets/comment/comment/listJson.html" toolbar="#manage_comment_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sum="true">ID</th>
			<th field="parentId" sum="true">父ID</th>
			<th field="busiType">业务类型</th>
			<th field="busiKey">业务标志</th>
			<th field="userNo">用户标志</th>
			<th field="userName">用户名</th>
            <th field="userIp">用户IP</th>
			<th field="content" formatter="contentFormatter">评论内容</th>
			<th field="thumbsup" >点赞数</th>
            <th field="repeats" >复评数</th>
			<%--<th field="sticky" formatter="mappingFormatter">置顶</th>--%>
			<th field="status" formatter="mappingFormatter">状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">评论时间</th>
          	<th field="rowActions" formatter="manage_comment_actionFormatter">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_comment_action" style="display: none;">
      <a onclick="$.acooly.framework.show('/manage/portlets/comment/comment/show.html?id={0}',500,400);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_comment_toolbar">
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_comment_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_comment_exports_menu" style="width:150px;">
            <div onclick="$.acooly.framework.exports('/manage/portlets/comment/comment/exportXls.html','manage_comment_searchform','p_comment')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
            <div onclick="$.acooly.framework.exports('/manage/portlets/comment/comment/exportCsv.html','manage_comment_searchform','p_comment')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
        </div>
    </div>
  </div>

</div>
<script>
    function manage_comment_actionFormatter(value, row, index){
        var html = "";
        if(row.status != 'disable'){
            html = "<a onclick=\"$.acooly.framework.edit({url:'/manage/portlets/comment/comment/edit.html',id:'{0}',entity:'comment',width:600,height:600});\" href=\"#\" title=\"编辑\">" +
                "<i class=\"fa fa-pencil fa-lg fa-fw fa-col\"></i>" +
                "</a>";
        }
        html = html + $('#manage_comment_action').html();
        return formatString(html, row.id)
    }
</script>
