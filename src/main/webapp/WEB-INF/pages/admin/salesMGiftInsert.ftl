<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>销量管理-赠品管理-新增</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function addGift() {
        var giftName   = document.getElementById('giftName').value;
        if(!giftName) {
            return;
        }
        window.location.href="/admin/addSalesMGift?giftName=" + encodeURI(encodeURI(giftName));
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
                  <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="addGift()">
              </span>
      </caption>
    <tbody>
      <tr>
          <td>礼品名</td>
          <td><input id="giftName" name="giftName"></td>
      </tr>
    </tbody>
  </table>
  <#--<button class="btn btn-default radius p-5" onclick="addGift()">新增</button>-->
<#--</div>-->

</body>

</html>