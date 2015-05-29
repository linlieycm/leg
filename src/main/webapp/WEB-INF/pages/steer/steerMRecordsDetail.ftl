<!DOCTYPE html>
<html lang="en">
<head>
  <title>考勤查询</title>
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
    function gradeChange() {
        var checkUserId   = document.getElementById('checkUserId').value;
        if(!checkUserId) {
            return;
        }
        var queryDate   = document.getElementById('queryDate').value;
        if(!queryDate) {
            return;
        }
        window.location.href="/steer/attendanceQuery?queryDate=" + queryDate + "&checkUserId=" + checkUserId;
    }
</script>
<body class="bg-gray">
<form action="/steer/attendanceQuery">
    <input type="hidden" id="userId" name="userId" value="${userId}">
  <div class="records-detail m-b-20 ">
    <table class="w-full bg-none">
      <thead>
        <tr>
          <td class="text-left">日期:</td>
          <td class="text-left">员工:</td>
          <td class="text-left"></td>
        </tr>
      </thead>
      <tbody>
        <tr>
            <td><input type="date" id="queryDate" name="queryDate" value="${queryDate}"></td>
            <td>
                <select name="checkUserId" id="checkUserId">
                    <option value="${checkUserId}">${checkUserId}</option>
                    <option value="${userId}">自己</option>
                <#list subUserList as subUser>
                    <option value="${subUser.get("userId")}">${subUser.get("name")}</option>
                </#list>
                </select>
            </td>
            <td><button onclick="gradeChange()">查询</button></td>
        </tr>
      </tbody>
    </table>
    <div class="records-detail-info border-radius bg-white">
      <table class="w-full">
        <thead>
          <tr class="b-b">
            <td>门店</td>
            <td>员工</td>
            <td>上下班打卡</td>
          </tr>
        </thead>
          <tbody>
          <tr class="b-b">
              <td id="store">${store}</td>
              <td id="name">${name}</td>
              <td id="sign">${minTime}<br>${maxTime}</td>
          </tr >
          </tbody>
      </table>
      <ul>
          <#if maxTime = "无记录">
              <li>没有打卡记录</li>
          </#if>
          <#if maxTime != "无记录">
              <#list recordList as record>
                  <li>${record.get("attTime")}
                    <span>
                    <#if record.get("type") = "1">
                        到岗
                    </#if>
                    <#if record.get("type") = "2">
                        离岗
                    </#if>
                    </span>
                  </li>
              </#list>
          </#if>
      </ul>

    </div>
  </div>
    </form>
</body>
</html>