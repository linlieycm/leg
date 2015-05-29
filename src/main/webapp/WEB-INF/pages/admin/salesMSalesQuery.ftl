<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销量管理-销量查询-查询</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function exlDown() {
        var beginDate = document.getElementById('beginDate').value;
        if(!beginDate) {
            return;
        }
        var endDate = document.getElementById('endDate').value;
        if(!endDate) {
            return;
        }
        var cityId = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        window.location.href="/admin/staffSalesMSaleDown?beginDate=" + beginDate + "&endDate=" + endDate +"&cityId=" + cityId;
    }
    function exlQuery() {
        var beginDate = document.getElementById('beginDate').value;
        if(!beginDate) {
            return;
        }
        var endDate = document.getElementById('endDate').value;
        if(!endDate) {
            return;
        }
        var cityId = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        window.location.href="/admin/staffSalesMSaleDownQuery?beginDate=" + beginDate + "&endDate=" + endDate +"&cityId=" + cityId;
    }
</script>
<body >
<#--<div class="pc-init border relative text-center" >-->
  <#--<ul class="nav-header clearfix">-->
    <#--<li ><a href="/admin/staffMPromoter">员工管理</a></li>-->
    <#--<li><a href="/admin/infoMCourseInit">资讯</a></li>-->
    <#--<li class="active">销量管理</li>-->
  <#--</ul>-->
  <#--<ul class="nav-left ">-->
      <#--<li ><a href="/admin/salesMStore">门店管理</a></li>-->
      <#--<li><a href="/admin/salesMGift">赠品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMGiftDownPre">赠品查询</a></li>-->
      <#--<li><a href="/admin/salesMSale">产品管理</a></li>-->
      <#--<li class="active"><a href="/admin/staffSalesMSaleDownPre">销量查询</a></li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption >
          开始日期
          <input id="beginDate" name="beginDate" type="text" onClick="WdatePicker()" value="${beginDate}"/>
          结束日期
          <input id="endDate" name="endDate" type="text" onClick="WdatePicker()"  value="${endDate}"/>
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
          <#--<button class="btn btn-default radius p-5" onclick="exlQuery()">查询</button>-->
          <#--<button class="btn btn-default radius p-5" onclick="exlDown()">导出报表</button>-->
      </caption>
        <thead>
          <tr class="b-b">
              <th>上报时间</th>
              <th>门店名</th>
              <th>产品名称</th>
              <th>单价</th>
              <th>数量</th>
              <th>金额</th>
          </tr>
        </thead>
        <tbody>
          <#list giftResultList as gift>
          <tr>
              <td>${gift.get("reportDate")}</td>
              <td>${gift.get("storeName")}</td>
              <td>${gift.get("giftName")}</td>
              <td>${gift.get("price")}</td>
              <td>${gift.get("number")}</td>
              <td>${gift.get("amount")}</td>
          </tr>
          </#list>
        </tbody>
  </table>

<#--</div>-->

</body>
</html>