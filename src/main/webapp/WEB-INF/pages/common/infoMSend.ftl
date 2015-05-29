<!DOCTYPE html>
<html lang="en">
<head>
    <title>最新通知</title>
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-title" content="">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="p-15">
    <table border="1">
        <thead>
        <tr>
            <td>通知标题</td>
            <td>上传时间</td>
        </tr>
        </thead>
        <tbody>
        <#list recordList as record>
        <tr>
            <td><a href="#" class="m-r-10" onclick="infoMSendCheck(${record.get("ID")})">${record.get("Name")}</a></td>
            <td>${record.get("CreateTime")}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
<script>
    function infoMSendCheck(SendId) {
        window.location.href="/common/infoMSendCheck?sendId=" + SendId;
    }
</script>
</html>