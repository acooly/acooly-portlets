<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<c:if test="${initParam['ssoEnable']=='true'}">
    <%@ include file="/WEB-INF/jsp/manage/common/ssoInclude.jsp" %>
</c:if>
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_noticeInfo_searchform','manage_noticeInfo_datagrid');
});

function formatBool(value){
    if(value){
        return "是";
    }
    else{
        return "否";
    }
}

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_noticeInfo_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
                发送时间: <input size="10" type="text" class="text" id="search_GTE_sendTime" name="search_GTE_sendTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                至 <input size="10" class="text" type="text" id="search_LTE_sendTime" name="search_LTE_sendTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                接收人: <input type="text" class="text" size="15" name="search_LIKE_sender"/>
				消息分组：
                <c:if test="${messageGroups == null}">
                    <input type="text" class="text" size="15" name="search_RLIKE_customGroup"/>
                </c:if>
                <c:if test="${messageGroups != null}">
                    <select name="search_RLIKE_customGroup" editable="false" panelHeight="auto" class="easyui-combobox" data-options="required:true">
                        <option value="">所有</option>
                        <c:forEach items="${messageGroups}" var="e">
                            <option value="${e.key}">${e.value}</option>
                        </c:forEach>
                    </select>
                </c:if>
                状态: <select style="width:80px;height:27px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allStatuss}"><option value="${e.key}" ${param.search_EQ_status == e.key?'selected':''}>${e.value}</option></c:forEach></select>
				发送类型: <select style="width:80px;height:27px;" name="search_EQ_type" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${allTypes}"><option value="${e.key}" ${param.search_EQ_type == e.key?'selected':''}>${e.value}</option></c:forEach></select>
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
            <th field="sendTime" formatter="dateTimeFormatter">发送时间</th>
            <th field="title">标题</th>
            <th field="content" formatter="contentFormatter">内容</th>
            <th field="customGroup">分组</th>
            <th field="context" formatter="jsonFormatter">会话参数</th>
            <th field="type" formatter="mappingFormatter">类型</th>
            <th field="sender">发送人</th>
            <th field="receiver">接收人</th>
            <th field="readed" formatter="formatBool">已读</th>
            <th field="push" formatter="formatBool">需要推送</th>
            <th field="status" formatter="mappingFormatter">推送状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
			<th field="comments">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_noticeInfo_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_noticeInfo_action" style="display: none;">
      <a onclick="$.acooly.framework.show('/manage/portlets/notice/noticeInfo/show.html?id={0}',600,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_noticeInfo_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true"
         onclick="$.acooly.framework.create({url:'/manage/portlets/notice/noticeInfo/create.html',entity:'noticeInfo',width:600,height:550,title:'推送消息',addButton:'推送',reload:true})"><i
              class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>推送消息</a>
    </div>
  </div>

</div>
