<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>课程-课程管理-更新</title>
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
    <#--<li class="active">课程管理</li>-->
    <#--<li ><a href="/admin/infoMSendInit">发布通知</a></li>-->
  <#--</ul>-->
<#include "/template.ftl">
    <table class="nav-table m-b-0 " border="1">
        <caption>
            <span class="btn btn-small" style="opacity: 1;">
                  <input type="submit" id="searchsubmit2" value="新增课程" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMCourseAdd()">
              </span>
        </caption>
        <thead>
        <tr>
            <td>课程名</td>
            <td>查看</td>
            <td>上传时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list recordList as record>
        <tr>
            <td>${record.get("Name")}</td>
            <td><a href="${record.get("LinkPla")}">查看</a></td>
            <td>${record.get("CreateTime")}</td>
            <td>
                <a href="#" class="m-r-10" onclick="infoMCourseDel(${record.get("ID")})">删除</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <#--<div class="btnss">-->
        <#--<button class="btn btn-default radius p-5 m-r-20" onclick="infoMCourseAdd()">新增课程</button>-->
    <#--</div>-->
<#--</div>-->

</body>
<script>
function infoMCourseDel(courseId) {
    window.location.href="/admin/infoMCourseDel?courseId=" + courseId;
}
function infoMCourseAdd() {
    window.location.href="/admin/infoMCourseAddPre";
}
</script>

</html>