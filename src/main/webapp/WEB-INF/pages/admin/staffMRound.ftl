<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-巡店报表</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function exlDown() {
        window.location.href="/admin/staffMRoundDown";
    }
    function exlQuery() {
        var beginDate   = document.getElementById('beginDate').value;
        if(!beginDate) {
            return;
        }
        var endDate   = document.getElementById('endDate').value;
        if(!endDate) {
            return;
        }
        var cityId   = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        //window.location.href="/admin/staffMRoundQuery?beginDate=" + beginDate + "&endDate=" + endDate + "&cityName=" + cityName;
        window.location.href="/admin/staffMRoundQuery?beginDate=" + beginDate + "&endDate=" + endDate + "&cityId=" + cityId;
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
      <#--<li ><a href="/admin/staffMPromoter">促销员管理</a></li>-->
      <#--<li ><a href="/admin/salesMObserver">督导管理</a></li>-->
      <#--<li><a href="/admin/staffMCheckingInit">考勤报表</a></li>-->
      <#--<li class="active"><a href="/admin/staffMRoundInit">巡店报表</a></li>-->
      <#--<li>员工名单</li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption >
          开始日期
          <input id="beginDate" name="beginDate" type="text" value="${beginDate}" onClick="WdatePicker()"/>
          结束日期
          <input id="endDate" name="endDate" type="text" value="${endDate}" onClick="WdatePicker()"/>
          城市
          <#--<input id="cityName" name="cityName" type="text" value="${cityName}"/>-->
          <select name="cityId" id="cityId">
              <#if cityId ??>
                  <option value="${cityId}">${cityName}</option>
              </#if>
              <#list cityRecordList as record>
                  <option value="${record.get("ID")}">${record.get("CityName")}</option>
              </#list>
          </select>
          <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="查询" hidefocus="true" class="gradient" style="outline: none;" onclick="exlQuery()">
          </span>
          <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="导出报表" hidefocus="true" class="gradient" style="outline: none;" onclick="exlDown()">
          </span>
      </caption>
    <thead>
      <tr>
        <td>巡店日期</td>
        <#--<td>城市</td>-->
        <td>门店</td>
        <td>督导</td>
        <#--<td>巡店评价</td>-->
      </tr>
    </thead>
    <tbody>
    <#list recordlist as record>
      <tr>
          <td>${record.get("reportDate")}</td>
          <#--<td>${record.get("city")}</td>-->
          <td>${record.get("storeName")}</td>
          <td>${record.get("usName")}</td>
      </tr>
    </#list>
    </tbody>
  </table>
<#--</div>-->

</body>

</html>