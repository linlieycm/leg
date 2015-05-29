<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-促销员管理</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function exlQuery() {
        var cityId   = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        window.location.href="/admin/staffMPromoterQuery?cityId=" + cityId;
    }
    function infoMSaleAdd() {
        window.location.href="/admin/addSalesMPromoterPre";
    }

    function agreeUser(userId) {
        window.location.href="/admin/staffMPromoterAgreeUser?userId=" + userId;
    }
    function refuseUser(userId) {
        window.location.href="/admin/staffMPromoterRefuseUser?userId=" + userId;
    }

</script>
<body >
<#--<div class="pc-init border relative text-center" >-->
  <#--<ul class="nav-header clearfix">-->
    <#--<li class="active">员工管理</li>-->
    <#--<li><a href="/admin/infoMCourseInit">资讯</a></li>-->
    <#--<li><a href="/admin/salesMStore">销量管理</a></li>-->
  <#--</ul>-->
  <#--<ul class="nav-left ">-->
    <#--<li class="active">促销员管理</li>-->
      <#--<li ><a href="/admin/salesMObserver">督导管理</a></li>-->
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
            <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMSaleAdd()">
          </span>
      </caption>
    <thead>
      <tr>
        <#--<td>状态</td>-->
        <td>ID</td>
        <td>促销员</td>
        <td>密码</td>
        <#--<td>原门店</td>-->
        <td>所属门店</td>
        <td>督导</td>
        <#--<td>操作</td>-->
      </tr>
    </thead>
    <tbody>
    <#list recordList as record>
        <tr>
            <#--<td>新增</td>-->
            <td>${record.get("id")}</td>
            <td>${record.get("name")}</td>
            <td>${record.get("password")}</td>
            <#--<td>-</td>-->
            <td>${record.get("storeName")}</td>
            <td>${record.get("superName")}</td>
            <#--<td><a href="#" class="m-r-10" onclick="agreeUser(${record.get("id")})">同意</a>-->
                <#--<a href="#" class="m-r-10" onclick="refuseUser(${record.get("id")})">驳回</a>-->
            <#--</td>-->
        </tr>
    </#list>

      <#--<tr>-->
        <#--<td>新增</td>-->
        <#--<td>bb</td>-->
        <#--<td>-</td>-->
        <#--<td>中山公园</td>-->
        <#--<td>xxx</td>-->
        <#--<td><a href="#" class="m-r-10">同意</a><a href="">驳回</a></td>-->
      <#--</tr>-->
    </tbody>
  </table>

<#--</div>-->

</body>
</html>