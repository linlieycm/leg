<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-督导管理-查询</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function exlDown() {
        var cityName = document.getElementById('cityName').value;
        if(!cityName) {
            return;
        }
        window.location.href="/admin/staffMObserverDown?cityName=" + cityName;
    }
    function exlQuery() {
        var cityId = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        window.location.href="/admin/salesMObserverQuery?cityId=" + cityId;
    }
    function infoMObserverDel(userId) {
        window.location.href="/admin/delSalesMObserver?userId=" + userId;
    }
    function infoMObserverAdd() {
        window.location.href="/admin/addSalesMObserverPre";
    }
    function infoMObserverCheck(userId) {
        window.location.href="/admin/infoMObserverCheck?userId=" + userId;
    }

</script>
<body >
<#--<div class="pc-init border relative text-center" >-->
  <#--<ul class="nav-header clearfix">-->
    <#--<li class="active">员工管理</li>-->
      <#--<li><a href="/admin/infoMCourseInit">资讯</a></li>-->
      <#--<li ><a href="/admin/salesMStore">销量管理</a></li>-->
  <#--</ul>-->
  <#--<ul class="nav-left ">-->
      <#--<li ><a href="/admin/staffMPromoter">促销员管理</a></li>-->
      <#--<li class="active">督导管理</li>-->
      <#--<li><a href="/admin/staffMCheckingInit">考勤报表</a></li>-->
      <#--<li><a href="/admin/staffMRoundInit">巡店报表</a></li>-->
      <#--<li>员工名单</li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table" border="1">
      <caption>
          城市
          <select name="cityId" id="cityId">
          <#list cityRecordList as record>
              <option value="${record.get("ID")}">${record.get("CityName")}</option>
          </#list>
          </select>
          <span class="btn btn-small" style="opacity: 1;">
            <input type="submit" id="searchsubmit2" value="查询" hidefocus="true" class="gradient" style="outline: none;" onclick="exlQuery()">
          </span>
          <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMObserverAdd()">
          </span>
          <#--城市-->
          <#--<input id="cityName" name="cityName" type="text" value="${cityName}"/>-->
          <#--人员类型-->
          <#--<select id="userType" name="userType">-->
              <#--<option value="0">全部</option>-->
              <#--<option value="1">促销员</option>-->
              <#--<option value="2">督导</option>-->
          <#--</select>-->
          <#--<button class="btn btn-default radius p-5" onclick="exlQuery()">查询</button>-->
      </caption>
    <thead>
      <tr>
          <td>ID</td>
          <td>姓名</td>
          <td>密码</td>
          <td>操作</td>
      </tr>
    </thead>
    <tbody>
    <#if recordList ??>
      <#list recordList as record>
      <tr>
          <td>${record.get("ID")}</td>
          <td>${record.get("Name")}</td>
          <td>${record.get("Password")}</td>
          <td>
              <#--<a href="#" onclick="infoMObserverCheck(${record.get("ID")})">修改</a>-->
              <a href="#" onclick="infoMObserverDel(${record.get("ID")})">删除</a>
          </td>
      </tr>
      </#list>
    </#if>
    </tbody>
  </table>

<#--</div>-->

</body>
</html>