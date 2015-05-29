<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>资讯-课程管理-新增</title>
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
        <thead>
            <tr>
                <td>条目</td>
                <td>值</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>课程名称</td>
                <td><input id="name" name="name"></td>
            </tr>
            <tr>
                <td>课程简介</td>
                <td><input id="introduce" name="introduce"></td>
            </tr>
            <tr>
                <td>链接地址</td>
                <td><input id="linkPla" name="linkPla"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                  <span class="btn btn-small" style="opacity: 1;">
                      <input type="submit" id="searchsubmit2" value="课程新增" hidefocus="true" class="gradient" style="outline: none;" onclick="infoMCourseAdd()">
                  </span>
                </td>
            </tr>
        </tbody>
    </table>
  <#--<div class="btnss">-->
    <#--<button class="btn btn-default radius p-5 m-r-20" onclick="infoMCourseAdd()">课程新增</button>-->
  <#--</div>-->
<#--</div>-->

</body>
<script>
    function infoMCourseAdd() {
        var name   = document.getElementById('name').value;
        if(!name) {
            return;
        }
        var introduce   = document.getElementById('introduce').value;
        if(!introduce) {
            return;
        }
        var linkPla   = document.getElementById('linkPla').value;
        if(!linkPla) {
            return;
        }
        window.location.href="/admin/infoMCourseAdd?name=" + encodeURI(encodeURI(name)) +
                "&introduce=" + encodeURI(encodeURI(introduce)) +
                "&linkPla=" + linkPla;
    }
</script>

</html>