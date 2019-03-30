<div class="easyui-layout" data-options="fit:true,border:false" xmlns:c="http://www.w3.org/1999/html">
    <!-- 查询条件 -->
    <#if realTime! == ''>
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;border-bottom: 1px solid #ddd;" align="left">
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
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="manage_actionAnalysisVisits_search();"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    </#if>

    <div data-options="region:'center',fit:true,border:false">
        <div id="manage_actionAnalysisVisits_pic" style="height:80%; width: 100%;padding: 15px;margin-top: 20px;"></div>
    </div>
    <script src="/js/echarts.common.min.js"/>
    <script>
        function manage_actionAnalysisVisits_search() {
            var queryParams = serializeObject($('#manage_actionAnalysisVisits_searchform'));
            <#if realTime??>
            var today = $.acooly.format.date(new Date(),'yyyy-MM-dd');
            queryParams = {search_EQ_analysisPeriod:'HOUR',search_GTE_period:today,search_LTE_period:today};
            </#if>
            $.ajax({
                url: '/manage/alog/analysis/visits.html',
                data: queryParams,
                success: function (result) {
                    if (result.success) {
                        manage_actionAnalysisVisits_echarts(result, queryParams);
                    } else {
                        $.acooly.msgrb(result.message, result.success)
                    }
                },
                error: function (r, e) {
                    $.acooly.msgrb(e, false);
                }

            });
        }

        function manage_actionAnalysisVisits_echarts(data, params) {
            var subText = "按"+data.data.allPeriods[params.search_EQ_analysisPeriod];
            if (params.search_GTE_period == params.search_LTE_period) {
                subText += "：" + params.search_GTE_period
            }else{
                subText += "：" + params.search_GTE_period + " ~ " + params.search_LTE_period;
            }
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('manage_actionAnalysisVisits_pic'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: "访问量分析",
                    subtext:subText,
                    padding: [3, 120]
                },
                legend: {
                    data: ['点击量PV', '用户量UV', '独立IP数']
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
                    source: data.rows
                },
                // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
                xAxis: {type: 'category'},
                // 声明一个 Y 轴，数值轴。
                yAxis: {},
                // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
                series: [
                    {name: '点击量PV', type: 'line'},
                    {name: '用户量UV', type: 'line'},
                    {name: '独立IP数', type: 'line'}
                ]
            }

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

        <#if realTime??>
        $(function(){ manage_actionAnalysisVisits_search();});
        </#if>

    </script>
</div>
