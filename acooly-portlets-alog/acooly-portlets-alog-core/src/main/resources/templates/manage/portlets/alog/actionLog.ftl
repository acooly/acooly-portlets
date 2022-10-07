<#if ssoEnable><#include "/manage/common/ssoInclude.ftl"></#if>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
        <form id="manage_actionLog_searchform" class="form-inline ac-form-search" onsubmit="return false">
            <div class="form-group">
                <label class="col-form-label">访问Key：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_actionKey"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">平台：</label>
                <select name="search_EQ_os" class="form-control input-sm select2bs4">
                    <option value="">所有</option>
                    <#list allOss as k,v>
                        <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">渠道：</label>
                <select name="search_EQ_os" class="form-control input-sm select2bs4">
                    <option value="">所有</option>
                    <#list allChannels as k,v>
                        <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">IP：</label>
                <input type="text" class="form-control form-control-sm" name="search_LIKE_userIp"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">时间：</label>
                <input type="text" class="form-control form-control-sm" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_actionLog_searchform','manage_actionLog_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
        </form>
    </div>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false">
<#--        toolbar="#manage_actionLog_toolbar"-->
        <table id="manage_actionLog_datagrid" class="easyui-datagrid" url="${pageContext.request.contextPath}/manage/portlets/alog/actionLog/listJson.html" fit="true" border="false" fitColumns="false"
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
        <div id="manage_actionLog_toolbar"></div>
    </div>

    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_actionLog_searchform', 'manage_actionLog_datagrid');
        });
    </script>

</div>
