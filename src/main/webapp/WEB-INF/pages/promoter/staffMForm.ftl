<!DOCTYPE html>
<html lang="en">
<head>
  <title>签到</title>
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
<body class="bg-gray">
    <form action="/promoter/attendance" enctype="multipart/form-data" method="post">
        <div class="sign-form">
            <input type="hidden" name="userId" value="${userId}">
            <input type="hidden" name="storeId" value="${storeId}">
            <input type="hidden" name="form" value="1">

        <label for="type">签到原因</label>
        <select name="type" id="type">
            <option value="1">上岗签到</option>
            <option value="2">离岗签到</option>
        <#--<#if checkedUp="no">-->
           <#---->
        <#--</#if>-->
        <#--<#if checkedDown="no">-->
            <#---->
        <#--</#if>-->
        </select>
        <br>

        <label for="staffinfo" class="inline-block m-t-25">员工信息</label><br>
        <input type="text" id="staffinfo"   name="staffinfo" class="block w-full" value="${name}-${userId}">

        <label for="address">拍照</label><br>
        <input type="file" name="image" id="file" class="block w-full" />
        <#--<input type="text" id="address" name="address" class="block w-full" value="testaddressONSKJDSK">-->

        <label for="signdate">签到时间</label><br>
        <input type="text" id="signdate"    name="signdate" class="block w-full" value="${.now}" readonly="true">

        </div>
        <button class="btn w-full btn-orange btn-sign" id="submit">
        <img src="/assets/aim.png" class="icon-location">打卡，签到
        </button>
    </form>
</body>
<script src="/js/zepto/zepto.min.js"></script>
<script>
    $('#submit').on('click', function() {
        var value = $('#type').val();
        if (!value) {
            return;
        }
    });
</script>
</html>