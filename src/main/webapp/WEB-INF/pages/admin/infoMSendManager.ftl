<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>资讯-通知管理-更新</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<body >
<#--<div class="pc-init border relative text-center" >-->
  <#--<ul class="nav-header clearfix">-->
    <#--<li ><a href="/admin/staffMPromoter">员工管理</a></li>-->
    <#--<li class="active">资讯</li>-->
    <#--<li ><a href="/admin/salesMStore">销量管理</a></li>-->
  <#--</ul>-->
  <#--<ul class="nav-left ">-->
      <#--<li ><a href="/admin/infoMCourseInit">员工管理</a></li>-->
      <#--<li class="active">通知管理</li>-->
  <#--</ul>-->
<#include "/template.ftl">
    <table class="nav-table m-b-0 " border="1">
        <caption>
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="删除通知" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMSendDel(${record.get("ID")})">
              </span>
        </caption>
        <thead>
            <tr>
                <td>条目</td>
                <td>值</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>通知名称</td>
                <td>${record.get("Name")}</td>
            </tr>
            <tr>
                <td>通知内容</td>
                <td>
                ${record.get("Introduce")}
                </td>
            </tr>
        </tbody>
    </table>
  <#--<div class="btnss">-->
    <#--<button class="btn btn-default radius p-5 m-r-20" onclick="infoMSendDel(${record.get("ID")})">删除通知</button>-->
  <#--</div>-->
<#--</div>-->

</body>
<script>
    function infoMSendDel(SendId) {
        window.location.href="/admin/infoMSendDel?sendId=" + SendId;
    }
</script>

</html>