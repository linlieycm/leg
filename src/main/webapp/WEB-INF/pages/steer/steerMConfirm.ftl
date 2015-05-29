<!DOCTYPE html>
<html lang="en">
<head>
  <title>Document</title>
  <title>销量确认</title>
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
<script>
    function checkInfo(workInfoId) {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMConfirmDetail?workInfoId=" + workInfoId + "&userId=" + userId;
    }
</script>
<body class="bg-gray">
<input type="hidden" id="userId" name="userId" value="${userId}">
  <div class="p-20">
    <div class="p-lr-20">
      <button class="btn btn-gray w-half">已提报</button><button class="btn btn-gray w-half">未提报</button>
    </div>
    <div class="table-div m-b-20 m-t-25 border-radius">
      <table class="w-full border-radius-none">
        <thead>
          <tr class="b-b">
            <td>门店</td>
            <td>员工</td>
            <td>日期</td>
            <td>详情</td>
          </tr>
        </thead>
        <tbody>
          <#list workinfoList as workinfo>
            <tr>
                <td>${workinfo.get("storeName")}</td>
                <td>${workinfo.get("userName")}</td>
                <td>${workinfo.get("reportDate")}</td>
                <td><span class="setting" onclick="checkInfo(${workinfo.get("workId")})">查看</span></td>
            </tr>
          </#list>

        </tbody>
      </table>
    </div>
  </div>
</body>
</html>