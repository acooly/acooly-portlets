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
<div style="margin-top: 10px;">
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
                    <td><input type="text" style="width: 90%;" placeholder="请输入问题(最大128字符)..." name="question" class="easyui-validatebox"/></td>
                </tr>
                <tr>
                    <th>答案：</th>
                    <td><textarea id="manage_fqa_editform_answer" rows="16" cols="40" name="answer"></textarea></td>
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
            var options = {
                minHeight: '400px',
                resizeType: 1, // 2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动;默认值:2
                allowFileManager: true, // true时显示浏览远程服务器按钮 ;默认值: false
                allowMediaUpload: false, // true时显示视音频上传按钮。默认值: true
                allowFlashUpload: false, // true时显示Flash上传按钮;默认值: true
                items: ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat',
                    'lineheight', '|', 'justifyleft', 'justifycenter', 'justifyright', 'anchor', 'plainpaste', 'wordpaste', 'clearhtml',
                    'quickformat', 'insertorderedlist', 'insertunorderedlist', '|', 'image', 'multiimage', 'source', 'preview'],
                // 上传的url
                uploadJson: '/ofile/kindEditor.html?_csrf=' + token,
                // 加载完成后改变皮肤
                afterCreate: function () {
                    var color = $('.panel-header').css('background-color');
                    $('.ke-toolbar').css('background-color', color);
                },
                // 失去焦点时，保存
                afterBlur: function () {
                    this.sync();
                }
            };
            KindEditor.options.filterMode = false;
            KindEditor.create('#manage_fqa_editform_answer', options);
        });
    </script>
</div>

