<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销量管理-门店管理-查询</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function updStore(storeId) {
        if(!storeId) {
            return;
        }
        window.location.href="/admin/updSalesMStorePre?storeId=" + storeId;
    }

    function delStore(storeId) {
        if(!storeId) {
            return;
        }
        window.location.href="/admin/delSalesMStore?storeId=" + storeId;
    }

    function addStorePre() {
        window.location.href="/admin/addSalesMStorePre";
    }

    function onSearch() {
        var cityId   = document.getElementById('cityId').value;
        window.location.href="/admin/salesMStore?cityId=" + cityId;
    }
    function exlDown() {
        var cityId   = document.getElementById('cityId').value;
        window.location.href="/admin/salesMStoreDown?cityId=" + cityId;
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
    <#--<li class="active">门店管理</li>-->
      <#--<li><a href="/admin/salesMGift">赠品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMGiftDownPre">赠品查询</a></li>-->
      <#--<li><a href="/admin/salesMSale">产品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMSaleDownPre">销量查询</a></li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption>
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
              <input type="submit" id="searchsubmit2" value="查询" hidefocus="true" class="gradient" style="outline: none;" onclick="onSearch()">
          </span>
          <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="导出报表" hidefocus="true" class="gradient" style="outline: none;" onclick="exlDown()">
          </span>
          <span class="btn btn-small" style="opacity: 1;">
              <input type="submit" id="searchsubmit2" value="新增门店" hidefocus="true" class="gradient" style="outline: none;" onclick="addStorePre()">
          </span>
          <#--<button class="btn btn-default radius p-5" onclick="onSearch()">查询</button>-->
          <#--<button class="btn btn-default radius p-5" onclick="exlDown()">导出报表</button>-->
          <#--<button class="btn btn-default radius p-5" onclick="addStorePre()">新增门店</button>-->
      </caption>
    <thead>
      <tr>
        <td>城市</td>
        <td>门店</td>
        <td>门店地址</td>
        <td>促销员</td>
        <td>督导</td>
        <td>操作</td>
      </tr>
    </thead>
    <tbody>
    <#if resultStoreList ??>
    <#list resultStoreList as record>
      <tr>
        <td>${record.get("CityName")}</td>
        <td>${record.get("StoreName")}</td>
        <td>${record.get("StoreAddress")}</td>
        <td>${record.get("UserName")}</td>
        <td>${record.get("SuperiorName")}</td>
        <td>
            <a href="#" onclick="updStore(${record.get("ID")})">修改</a>
            <a href="#" onclick="delStore(${record.get("ID")})">删除</a>
        </td>
      </tr>
    </#list>
    </#if>

    </tbody>
  </table>

<#--</div>-->

</body>
</html>