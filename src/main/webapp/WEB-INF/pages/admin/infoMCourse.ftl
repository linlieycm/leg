<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>资讯-课程管理-查询</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<body >
<#include "/template.ftl">
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
  <ul class="nav-course">

      <#list recordList as record>
          <li><img src="/assets/course.png" alt=""><br><span><a href="${record.get("LinkPla")}">${record.get("Name")}</a></span></li>
      </#list>
  </ul>
<div class="btnss">
 <span class="btn btn-small" style="opacity: 1;">
      <input type="submit" id="searchsubmit2" value="课程管理" hidefocus="true" class="gradient" style="outline: none;" onclick="manage()">
  </span>
    </div>
  <#--<div class="btnss">-->
    <#--<button class="btn btn-default radius p-5 m-r-20" onclick="manage()">课程管理</button>-->
  <#--</div>-->
<#--</div>-->

</body>
<script>
    function manage() {
        window.location.href="/admin/infoMCourseManage";
    }
</script>

</html>