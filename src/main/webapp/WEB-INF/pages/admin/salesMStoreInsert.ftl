<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销量管理-门店管理-新增</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function addStore() {
        var cityId   = document.getElementById('cityId').value;
        if(!cityId) {
            return;
        }
        var storeName   = document.getElementById('storeName').value;
        if(!storeName) {
            return;
        }
        var storeAddress   = document.getElementById('storeAddress').value;
        if(!storeAddress) {
            return;
        }
        var userSel   = document.getElementById('userSel').value;
        if(!userSel) {
            return;
        }
        window.location.href="/admin/addSalesMStore?storeName=" + encodeURI(encodeURI(storeName)) +
                "&cityId=" + cityId +
                "&userSel=" + userSel +
                "&storeAddress=" + encodeURI(encodeURI(storeAddress))
                ;
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
      <#--<li class="active"><a href="/admin/salesMGift">赠品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMGiftDownPre">赠品查询</a></li>-->
      <#--<li><a href="/admin/salesMSale">产品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMSaleDownPre">销量查询</a></li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption>
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="addStore()">
              </span>
      </caption>
    <tbody>
      <#--<tr>-->
          <#--<td>城市</td>-->
          <#--<td><input id="storeCity" name="storeCity"></td>-->
      <#--</tr>-->
      <tr>
          <td>门店</td>
          <td><input id="storeName" name="storeName"></td>
      </tr>
      <tr>
          <td>门店地址</td>
          <td><input id="storeAddress" name="storeAddress"></td>
      </tr>
      <tr>
          <td>督导</td>
          <td>
          <select name="userSel" id="userSel">
              <#list resultUserList as resultUser>
                  <option value="${resultUser.get("ID")}">${resultUser.get("Name")}</option>
              </#list>
          </select>
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
    </tbody>
  </table>
  <#--<button class="btn btn-default radius p-5" onclick="addStore()">新增</button>-->
<#--</div>-->

</body>
</html>