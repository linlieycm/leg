<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>销量管理-门店管理-更新</title>
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function updStore() {
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
        var storeId   = document.getElementById('storeId').value;
        if(!storeId) {
            return;
        }
        var userSel   = document.getElementById('userSel').value;
        if(!userSel) {
            return;
        }
        window.location.href="/admin/updSalesMStore?storeName=" + encodeURI(encodeURI(storeName)) +
                "&cityId=" + cityId +
                "&storeAddress=" + encodeURI(encodeURI(storeAddress)) +
                "&storeId=" + storeId +
                "&userSel=" + userSel;
    }
</script>
<body >
<input type="hidden" name="storeId" id="storeId" value="${record.get("ID")}">
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
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="更新" hidefocus="true" class="gradient" style="outline: none;" onclick="updStore()">
              </span>
        </caption>
        <tbody>
        <tr>
            <td>门店</td>
            <td><input id="storeName" name="storeName" value="${record.get("StoreName")}"></td>
        </tr>
        <tr>
            <td>门店地址</td>
            <td><input id="storeAddress" name="storeAddress" value="${record.get("StoreAddress")}"></td>
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
    <#--<button class="btn btn-default radius p-5" onclick="updStore()">更新</button>-->
<#--</div>-->

</body>
</html>