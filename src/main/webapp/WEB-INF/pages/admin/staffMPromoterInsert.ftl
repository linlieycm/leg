<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-督导管理-新增</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function addPromoter1() {
        var userName   = document.getElementById('userName').value;
        if(!userName) {
            return;
        }
        var contact   = document.getElementById('contact').value;
        if(!contact) {
            return;
        }
        var cityId   = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        window.location.href="/admin/addSalesMPromoter?userName=" + encodeURI(encodeURI(userName)) +
                "&contact=" + contact +
                "&cityId=" + cityId;
    }

    function addPromoter2() {
        var userIdIns   = document.getElementById('userIdIns').value;
        if(!userIdIns) {
            alert("1");
            return;
        }

        var storeId   = document.getElementById('storeId').value;
        if(!storeId) {
            alert("1");
            return;
        }
        window.location.href="/admin/addSalesMPromoterTwo?userIdIns=" + userIdIns +
                "&storeId=" + storeId;
    }

</script>
<body >
<input type="hidden" id="userIdIns" name="userIdIns" value="${userIdIns}">
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
            <span class="btn btn-small" style="opacity: 1;">
            <#if userIdIns ??>
                <input type="submit" id="searchsubmit2" value="新增-2" hidefocus="true" class="gradient" style="outline: none;" onclick="addPromoter2()">
            <#else>
                <input type="submit" id="searchsubmit2" value="新增-1" hidefocus="true" class="gradient" style="outline: none;" onclick="addPromoter1()">

            </#if>
              </span>
      </caption>
    <tbody>
    <#if userIdIns ??>
    <tr>
        <td>门店</td>
        <td>
            <select name="storeId" id="storeId">
                <#list storeRecordList as record>
                    <option value="${record.get("ID")}">${record.get("StoreName")}</option>
                </#list>
            </select>
        </td>
    </tr>
    <#else>
    <tr>
        <td>姓名</td>
        <td>
            <input id="userName" name="userName">
        </td>
    </tr>
    <tr>
        <td>联系方式</td>
        <td>
            <input id="contact" name="contact">
        </td>
    </tr>
    <tr>
        <td>城市</td>
        <td>
            <select name="cityId" id="cityId">
                <#list cityRecordList as record>
                    <option value="${record.get("ID")}">${record.get("CityName")}</option>
                </#list>
            </select>
        </td>
    </tr>
    </#if>
    </tbody>
  </table>
    <#--<button class="btn btn-default radius p-5" onclick="addObserver()">新增</button>-->
<#--</div>-->

</body>
</html>