var acoolyAlogClass = {

    log: function (opts) {
        $.ajax({
            url: '/acooly/alog/log.html',
            data: opts,
            error: function () {
                //ig;
            }
        });
    },


    auto: function () {
        var path = window.location.pathname;
        var opts = {
            actionKey: path,
            actionUrl: path
        }
        this.log(opts);
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