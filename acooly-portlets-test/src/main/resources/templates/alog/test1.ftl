<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test1</title>
</head>
<body>
<h1>这是alog测试页面：test1</h1>
<@includePage path="/alog/test/footer.html" />
<script>
    console.info("url",window.location.pathname);
    console.info("queryString",window.location.search);
</script>
</body>
</html>