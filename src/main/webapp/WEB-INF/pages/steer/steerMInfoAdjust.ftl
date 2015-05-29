<!DOCTYPE html>
<html lang="en">

<head>
  <title>Document</title>
  <title>人员信息调整</title>
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
<input type="hidden" id="userId" name="userId" value="${userId}">
  <div class="p-20">
    <div class="p-lr-20">
        <table class="w-full border-radius-none">
            <tr class="b-b">
                <td><a href="/steer/steerMInfoAdjust">员工信息</a></td>
                <td><a href="/steer/steerMAddStaff">新增员工</a></td>
            </tr>
        </table>
    </div>
    <div class="table-div m-b-20 m-t-25 border-radius">
      <table class="w-full border-radius-none">
        <thead>
          <tr class="b-b">
            <td>门店</td>
            <td>员工</td>
            <td>设置</td>
          </tr>
        </thead>
        <tbody>
           <#list subUserList as subUser>
            <tr>
                <td>${subUser.get("storeName")}</td>
                <td>${subUser.get("name")}</td>
                <td>
                    <span class="setting" onclick="onchanged(${subUser.get("userId")});">变更</span>
                    <span class="setting" onclick="onjudaged(${subUser.get("userId")});">评价</span>
                </td>
            </tr>
           </#list>
        </tbody>
      </table>
    </div>
  </div>
</body>

<script src="/js/zepto/zepto.min.js"></script>
<script src="/js/zepto/zepto.js"></script>
<script>
    function onchanged(subUserId) {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMInfoChange?subUserId=" + subUserId + "&userId=" + userId;
    }
    function onjudaged(subUserId) {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMAssessment?subUserId=" + subUserId + "&userId=" + userId;
    }

    function steerMInfoAdjust() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMInfoAdjust?userId=" + userId;
    }

    function steerMAddStaff() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMAddStaff?userId=" + userId;
    }

</script>
</html>