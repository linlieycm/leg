<!DOCTYPE html>
<html lang="en">
<head>
    <title>培训课程中心</title>
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
<div class="grid-container clearfix">
    <#list recordList as record>
        <a href="${record.get("LinkPla")}" class="grid">
            <img src="/assets/course.png" alt="">
            <span>${record.get("Name")}</span>
        </a>
    </#list>


</div>
</body>
<script src="/js/initGrid.js"></script>
<script src="/js/prefixfree.min.js"></script>
</html>