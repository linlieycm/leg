<!DOCTYPE html>
<html lang="en">
<head>
  <title>新增巡店报告</title>
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
<body class="bg-dark ">
<form action="/steer/roundNewReportDo" enctype="multipart/form-data" method="post">
<input type="hidden" id="userId" name="userId" value="${userId}">
<div class="p-lr-20">
    <table class="w-full border-radius-none">
        <tr class="b-b">
            <td><a href="/steer/roundNewReport">巡店新增</a></td>
            <td><a href="/steer/roundOldReport">历史查询</a></td>
        </tr>
    </table>
</div>
  <div class="p-20">
    <label for="" class="block m-b-20">新增巡店报告</label>
    <div class="border-radius bg-white m-b-20 p-10">
      <label for="" class="block m-b-10">选择门店</label>
        <select name="storeId" id="storeId">
        <#list storeinfoList as storeinfo>
            <option value="${storeinfo.get("ID")}">${storeinfo.get("StoreName")}</option>
        </#list>
        </select>
    </div>
    <label for="" class="block m-b-10">日期</label>
    <div class="text-center btns-3">
        ${.now}
    </div>

    <label for="" class="block m-b-20 m-t-20">排面照片拍摄</label>
    <div class="upload m-b-20">
        <input type="file" name="image" id="file" style="width:160px;" />
    </div>

    <label for="" class="block m-b-10">备注</label>
    <textarea name="remark" id="remark" cols="30" rows="10" class="w-full border-radius ">

    </textarea>
    <div class="text-center">
      <button class="btn btn-green radius b-n m-t-10">保存并提交</button>
    </div>
  </div>
</form>
</body>
<script>
//    function saveReport() {
//        var userId   = document.getElementById('userId').value;
//        var storeId   = document.getElementById('storeId').value;
//        var remark   = document.getElementById('remark').value;
//        window.location.href="/steer/roundNewReportDo?userId=" + userId + "&storeId=" + storeId + "&remark=" + remark;
//    }
</script>
</html>