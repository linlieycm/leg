<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-督导管理-新增</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function addObserver() {
        var userName   = document.getElementById('userName').value;
        var contact   = document.getElementById('contact').value;
        window.location.href="/admin/addSalesMObserver?userName=" + encodeURI(encodeURI(userName)) + "&contact=" + contact;
    }
</script>
<body >
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
                  <input type="submit" id="searchsubmit2" value="新增" hidefocus="true" class="gradient" style="outline: none;" onclick="addObserver()">
              </span>
      </caption>
    <tbody>
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
    </tbody>
  </table>
    <#--<button class="btn btn-default radius p-5" onclick="addObserver()">新增</button>-->
<#--</div>-->

</body>
</html>