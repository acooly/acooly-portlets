<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<script>
    function manage_fqa_editform_askType_onLoadSuccess(row, data) {
        $(".tree-icon,.tree-file").removeClass("tree-file");
        $(".tree-icon,.tree-folder").removeClass("tree-folder-open tree-folder");
        var choosedId = "${treeType.id}";
        if (choosedId != null && choosedId != "") {
            $('#manage_fqa_editform_askTypeId').combotree('setValue', choosedId);
        }
        for (var i = 0; i < data.length; i++) {
            var node = $("#manage_fqa_editform_askTypeId").combotree('tree').tree("find", data[i].id);
            $("#manage_fqa_editform_askTypeId").combotree('tree').tree('expandAll', node.target);//展开所有节点
        }
    }
</script>
<div style="margin-top: 20px;">
    <form id="manage_fqa_editform" action="/manage/portlets/fqa/fqa/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="fqa" scope="request">
            <input name="id" type="hidden"/>
            <table class="tableForm" width="100%">
                <tr>
                    <th width="10%">分类：</th>
                    <td>
                        <select id="manage_fqa_editform_askTypeId" class="easyui-combotree" name="askTypeId" style="width:235px;"
                                data-options="url:'/manage/module/treeType/treeType/loadTree.html?theme=FQA',
                                loadFilter:function(data){ return data.rows;},
                                formatter:function(row){var fa='fa-file-o'; if(row.subCount > 0){fa='fa-folder-o';}  return '<i class=\'fa '+fa+'\' aria-hidden=\'true\'></i> ' + row.text;},
                                onLoadSuccess:manage_fqa_editform_askType_onLoadSuccess,
                                required:true">
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>问题：</th>
                    <td><textarea rows="2" cols="40" style="width: 90%;" placeholder="请输入问题(最大128字符)..." name="question" class="easyui-validatebox"></textarea></td>
                </tr>
                <tr>
                    <th>答案：</th>
                    <td><textarea id="manage_fqa_editform_answer" rows="12" cols="40" name="answer"></textarea></td>
                </tr>
                <tr>
                    <th>作者：</th>
                    <td><input type="text" name="author" style="width:260px;" <#if action=='create'>value="${user.username}"</#if>  placeholder="请输入作者..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
                </tr>

                <tr>
                    <th>状态：</th>
                    <td><select name="status" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;">
					<#list allStatuss as k,v>
                        <option value="${k}">${v}</option></#list>
                    </select></td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td><input type="text" style="width: 90%;" name="comments" placeholder="请输入备注..." class="easyui-validatebox" data-options="validType:['length[1,45]']"/></td>
                </tr>
            </table>
        </@jodd.form>
    </form>
    <script type="text/javascript">
        $(function () {
            var token = $("meta[name='X-CSRF-TOKEN']").attr("content");// 从meta中获取token
            var ke = $.acooly.framework.kingEditor({
                uploadUrl: '/ofile/kindEditor.html?_csrf=' + token,
                minHeight: '310',
                textareaId: 'manage_fqa_editform_answer'
            });
        });
    </script>
</div>

