<div class="easyui-layout" style="background-color: #ecf0f5;" data-options="fit:true,border:false" xmlns:c="http://www.w3.org/1999/html">
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;background-color: #ecf0f5;"" align="left">
        <section class="content-header" style="padding-top: 5px;display: flex">
            <h1 style="flex:1">
                <small id="manage_analysis_widget_visits_total_period"></small>
            </h1>
            <div>
                <a href="javascript:;" title="清理缓存(1小时)"><i class="fa fa-eraser" onclick="manage_actionAnalysisVisits_cacheClear()"></i></a>
            </div>
        </section>
        <section class="content" style="min-height: 10px; padding-top: 5px">
            <div class="row">
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-hand-pointer-o"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">今日点击数 PV</span>
                            <span class="info-box-number"><span id="manage_analysis_widget_visits_total_pv"></span><small>次</small></span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-users"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">今日用户数 UV</span>
                            <span class="info-box-number"><span id="manage_analysis_widget_visits_total_uv"></span><small>位</small></span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>

                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="fa fa-map-marker"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">今日独立IP数</span>
                            <span class="info-box-number"><span id="manage_analysis_widget_visits_total_ip"></span><small>个</small></span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
    </div>

    <div data-options="region:'center',fit:true,border:false" style="background-color: #ecf0f5;">
        <div style="height: 100%; width: 100%;padding: 0 20px;">
        <div class="box box-info" style="height: 90%; width: 100%;">
            <div class="box-header with-border">
                <h3 class="box-title">访问量实时分</h3>

                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <#--<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>-->
                </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="height: 80%; width: 100%; padding: 0 20px;">
                    <div id="manage_analysis_widget_visits_echart"
                         style="height: 100%; width: 100%;background-color: #fff;padding:30px 15px;"></div>
            </div>
        </div>
        </div>



    </div>
    <script src="/js/echarts.common.min.js"/>
    <script>
        function manage_actionAnalysisVisits_today() {
            var today = $.acooly.format.date(new Date(),'yyyy-MM-dd');
            var queryParams = {search_EQ_analysisPeriod:'HOUR',search_GTE_period:today,search_LTE_period:today};
            $.ajax({
                url: '/manage/alog/analysis/visits.html',
                data: queryParams,
                success: function (result) {
                    if (result.success) {
                        $('#manage_analysis_widget_visits_total_period').html(result.data.visitsTotal.period);
                        $('#manage_analysis_widget_visits_total_pv').html(result.data.visitsTotal.pv);
                        $('#manage_analysis_widget_visits_total_uv').html(result.data.visitsTotal.uv);
                        $('#manage_analysis_widget_visits_total_ip').html(result.data.visitsTotal.ip);
                        manage_actionAnalysisVisits_today_echarts(result, queryParams);
                    } else {
                        $.acooly.msgrb(result.message, result.success)
                    }
                },
                error: function (r, e) {
                    $.acooly.msgrb(e, false);
                }
            });
        }

        function manage_actionAnalysisVisits_today_echarts(data, params) {
            var subText = "按"+data.data.allPeriods[params.search_EQ_analysisPeriod];
            if (params.search_GTE_period == params.search_LTE_period) {
                subText += "：" + params.search_GTE_period
            }else{
                subText += "：" + params.search_GTE_period + " ~ " + params.search_LTE_period;
            }
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('manage_analysis_widget_visits_echart'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    subtext:subText
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

        function manage_actionAnalysisVisits_cacheClear(){
            $.ajax({
                url: "/manage/alog/analysis/widget/visits/cacheClear.html",
                success:function(){
                    manage_actionAnalysisVisits_today();
                    $.acooly.alert("清理成功");
                }
            });
        }


        $(function(){ manage_actionAnalysisVisits_today();});

    </script>
</div>
