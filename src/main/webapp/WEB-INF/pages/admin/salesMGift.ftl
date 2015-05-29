<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销量管理-赠品管理-管理</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function infoMGiftDel(giftId) {
        window.location.href="/admin/delSalesMGift?giftId=" + giftId;
    }
    function infoMGiftAdd() {
        window.location.href="/admin/addSalesMGiftPre";
    }
    function infoMGiftCheck(giftId) {
        window.location.href="/admin/infoMGiftCheck?giftId=" + giftId;
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
    <#--<li class="active"><a href="/admin/salesMGift">赠品管理</a></li>-->
    <#--<li><a href="/admin/staffSalesMGiftDownPre">赠品查询</a></li>-->
      <#--<li><a href="/admin/salesMSale">产品管理</a></li>-->
      <#--<li><a href="/admin/staffSalesMSaleDownPre">销量查询</a></li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption>
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMGiftAdd()">
              </span>
      </caption>
    <thead>
      <tr>
        <td>ID</td>
        <td>礼品名</td>
        <td>创建时间</td>
        <td>操作</td>
      </tr>
    </thead>
    <tbody>
        <#list recordList as record>
          <tr>
            <td>${record.get("ID")}</td>
            <td>${record.get("GiftName")}</td>
            <td>${record.get("CreateTime")}</td>
            <td>
                <a href="#" class="m-r-10" onclick="infoMGiftCheck(${record.get("ID")})">查看</a>
                <a href="#" class="m-r-10" onclick="infoMGiftDel(${record.get("ID")})">删除</a>
            </td>
          </tr>
        </#list>
    </tbody>
  </table>
    <#--<button class="btn btn-default radius p-5" onclick="infoMGiftAdd()">新增</button>-->
<#--</div>-->

</body>
</html>