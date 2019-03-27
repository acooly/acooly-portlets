/**
 * alog日志收集脚本
 * @type {{host: string, log: acoolyAlogClass.log, auto: acoolyAlogClass.auto}}
 *
 * useage：
 *
 * <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
 * <script src="http://127.0.0.1:8080/acooly/alog/acooly.alog.js"></script>
 * <script>
 *  $(function () {
 *       // 行为日志收集的服务器域名配置
 *       $.acooly.alog.host = "http://127.0.0.1:8080";
 *       // 注意：被收集的网站的域名需要在后台配置注册。例如被收集的网站域名是：http://acooly.cn，则需要在日志收集服务器配置参数：
 *       // acooly.alog.allowOrigins=http://acooly.cn,如果非跨站收集，则忽略
 *       $.acooly.alog.log();
 *   });
 * </script>
 *
 */
var acoolyAlogClass = {

    host: '',

    /**
     * 收集行为日志
     * @param url
     * @param opts
     */
    log: function (url, opts) {
        if (!url) {
            url = '/acooly/alog/log.html';
        }
        if (url.indexOf("http") != 0) {
            url = this.host + url;
        }
        var path = window.location.pathname;
        var options = $.extend({
            actionGroup: null,
            actionKey: path,
            actionUrl: path
            // ... 请参考：ActionLogInfo对象属性 ...
        }, opts);
        $.ajax({
            url: url,
            data: options,
            error: function () {
                //ig;
            }
        });
    },


    /**
     * 自动收集（本服务内）
     */
    auto: function () {
        var path = window.location.pathname;
        var opts = {
            actionKey: path,
            actionUrl: path
        }
        this.log(null, opts);
    }
};

$(function () {
    if (!$.acooly) {
        $.acooly = {};
    }
    $.extend($.acooly, {
        alog: acoolyAlogClass
    });
});