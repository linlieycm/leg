<!DOCTYPE html>
<html lang="en">
<head>
  <title>Document</title>
  <title>员工信息变更</title>
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
    <div class="table-div  p-lr-20-i m-t-25 border-radius maxH-300">
      <table class="w-full border-radius-none">
        <thead>
          <tr class="b-b">
            <td colspan="2">员工评分</td>
          </tr>
        </thead>
        <tbody>
        <#list evaluationComonList as evaluationComon>
          <tr class="b-b-d-i">
            <td colspan="1" class="text-left p-b-0-i" id="${evaluationComon.get("ID")}" name="${evaluationComon.get("ID")}">${evaluationComon.get("EvaluationName")}</td>
            <td colspan="1">
                <select name="storeId" id="storeId">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
    </div>
    <div class="m-t-20 m-b-20 text-center">
      <button class="btn btn-green radius b-n">保存并查看</button>
    </div>
  </div>
</body>
<script>
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