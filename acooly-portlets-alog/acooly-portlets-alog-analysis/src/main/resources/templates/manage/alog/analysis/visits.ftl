<script type="text/javascript">
    $(function () {
        $.acooly.framework.registerKeydown('manage_actionAnalysisVisits_searchform', 'manage_actionAnalysisVisits_datagrid');
    });

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
        <form id="manage_actionAnalysisVisits_searchform" onsubmit="return false">
            <table class="tableForm" width="100%">
                <tr>
                    <td align="left">
                        <div>
                            统计周期: <select name="search_EQ_analysisPeriod" class="easyui-combobox-ext">
                            <#list allPeriods as k,v>
                                <option value="${k}">${v}</option>
                            </#list>
                        </select>
                            日期段: <input size="15" class="text" id="search_GTE_period" name="search_GTE_period" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                            至<input size="15" class="text" id="search_LTE_period" name="search_LTE_period" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_actionAnalysisVisits_searchform','manage_actionAnalysisVisits_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div data-options="region:'center',fit:true,border:false">
        <div id="manage_actionAnalysisVisits_tabs" class="easyui-tabs" fit="true" data-options="onSelect:manage_actionAnalysisVisits_tabs_onSelect">
            <div title="表格数据" style="margin-left:0;">
                <!-- 表格的工具栏 -->
                <table id="manage_actionAnalysisVisits_datagrid" class="easyui-datagrid" url="/manage/alog/analysis/visits.html" toolbar="#manage_actionAnalysisVisits_toolbar" fit="true" border="false" fitColumns="false"
                       pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true"
                        data-options="onLoadSuccess:manage_actionAnalysisVisits_echarts">
                    <thead>
                    <tr>
                        <th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.period }">编号</th>
                        <th field="period">统计周期</th>
                        <th field="pv">PV</th>
                        <th field="uv">UV</th>
                        <th field="ip">IP</th>
                    </tr>
                    </thead>
                </table>
                <div id="manage_actionAnalysisVisits_toolbar"></div>
            </div>

            <div title="图表分析" style="margin-left:0;">
                <div id="manage_actionAnalysisVisits_pic"
                     style="height:600px; width: 100%;padding: 15px"></div>
            </div>
        </div>
    </div>
    <script src="/js/echarts.common.min.js"/>
    <script>

        function manage_actionAnalysisVisits_tabs_onSelect(title,index){
            if(index != 1){
                return;
            }
            manage_actionAnalysisVisits_echarts();
        }

        function manage_actionAnalysisVisits_echarts(){
            var data = $('#manage_actionAnalysisVisits_datagrid').datagrid('getData').rows;
            if(!data || data.length == 0){
                return;
            }
            var width = $('#manage_actionAnalysisVisits_datagrid').datagrid('options').width;
            $('#manage_actionAnalysisVisits_pic').css('width',width);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('manage_actionAnalysisVisits_pic'));
            // 指定图表的配置项和数据
            option = {
                legend: {
                    data:['点击量PV','用户量UV','独立IP数']
                },
                tooltip: {trigger: 'axis'},
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                dataset: {
                    dimensions: ['period', 'pv', 'uv', 'ip'],
                    // 提供一份数据。
                    source: data
                },
                // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
                xAxis: {type: 'category'},
                // 声明一个 Y 轴，数值轴。
                yAxis: {},
                // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
                series: [
                    {name:'点击量PV',type: 'line'},
                    {name:'用户量UV',type: 'line'},
                    {name:'独立IP数',type: 'line'}
                ]
            }

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    </script>
</div>
