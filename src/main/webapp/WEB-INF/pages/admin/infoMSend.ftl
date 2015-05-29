<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>资讯-通知管理-查询</title>
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
      <#--<li ><a href="/admin/infoMCourseInit">课程管理</a></li>-->
      <#--<li class="active">通知管理</li>-->
  <#--</ul>-->
<#include "/template.ftl">
    <table class="nav-table m-b-0 " border="1">
        <caption>
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="新增通知" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMSendAdd()">
              </span>
        </caption>
        <thead>
        <tr>
            <td>通知标题</td>
            <td>上传时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list recordList as record>
        <tr>
            <td>${record.get("Name")}</td>
            <td>${record.get("CreateTime")}</td>
            <td>
                <a href="#" class="m-r-10" onclick="infoMSendCheck(${record.get("ID")})">查看</a>
                <a href="#" class="m-r-10" onclick="infoMSendDel(${record.get("ID")})">删除</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <#--<div class="btnss">-->
        <#--<button class="btn btn-default radius p-5 m-r-20" onclick="infoMSendAdd()">新增通知</button>-->
    <#--</div>-->
<#--</div>-->

</body>
<script>
    function infoMSendDel(SendId) {
        window.location.href="/admin/infoMSendDel?sendId=" + SendId;
    }
    function infoMSendAdd() {
        window.location.href="/admin/infoMSendAddPre";
    }
    function infoMSendCheck(SendId) {
        window.location.href="/admin/infoMSendCheck?sendId=" + SendId;
    }
</script>

</html>