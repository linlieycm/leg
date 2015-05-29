<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-考勤报表</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function exlDown() {
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
        window.location.href="/admin/staffMCheckingDown?beginDate=" + beginDate + "&endDate=" + endDate + "&cityId=" + cityId;
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
        window.location.href="/admin/staffMCheckingQuery?beginDate=" + beginDate + "&endDate=" + endDate + "&cityId=" + cityId;
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
    <#--<li class="active"><a href="/admin/staffMCheckingInit">考勤报表</a></li>-->
    <#--<li><a href="/admin/staffMRoundInit">巡店报表</a></li>-->
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
        <select name="cityId" id="cityId">
        <#list cityRecordList as record>
            <option value="${record.get("ID")}">${record.get("CityName")}</option>
        </#list>
        </select>
        <span class="btn btn-small" style="opacity: 1;">
            <input type="submit" id="searchsubmit2" value="查询" hidefocus="true" class="gradient" style="outline: none;" onclick="exlQuery()">
        </span>
        <#--<button onclick="exlQuery()">查询</button>-->
        <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="导出报表" hidefocus="true" class="gradient" style="outline: none;" onclick="exlDown()">
          </span>
        <#--<button onclick="exlDown()">导出报表</button>-->
    </caption>
    <thead>
      <tr>
        <td>日期</td>
        <td>城市</td>
        <td>门店</td>
        <td>促销员</td>
        <#--<td>到岗时间</td>-->
        <#--<td>离岗时间</td>-->
        <td>所辖督导</td>
        <td>时间</td>
        <td>上下岗</td>
      </tr>
    </thead>
    <tbody>
    <#list recordlist as record>
    <tr>
        <td>${record.get("creaDay")}</td>
        <td>${record.get("city")}</td>
        <td>${record.get("storeName")}</td>
        <td>${record.get("name")}</td>
        <#--<td>${record.get("minTime")}</td>-->
        <#--<td>${record.get("maxTime")}</td>-->
        <td>${record.get("usName")}</td>
        <td>${record.get("CreateTime")}</td>
        <td>${record.get("attType")}
        <#--<#if record.get("attType") = 1>-->
            <#--上岗-->
        <#--<#elseif record.get("attType") = 2>-->
            <#--离岗-->
        <#--</#if>-->
        </td>
    </tr>
    </#list>
    </tbody>
  </table>
<#--</div>-->
</body>
</html>