<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="/acooly/alog/acooly.alog.js"></script>
<script>
    $(function () {
        var path = window.location.pathname;
        $.acooly.alog.log({
            actionKey: path,
            actionUrl: path
        });
    });
</script>