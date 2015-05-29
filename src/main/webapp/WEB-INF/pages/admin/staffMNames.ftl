<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-员工名单</title>
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<body >
<#--<div class="pc-init border relative text-center" >-->
  <#--<ul class="nav-header clearfix">-->
    <#--<li class="active">员工管理</li>-->
      <#--<li><a href="/admin/infoMCourseInit">资讯</a></li>-->
      <#--<li ><a href="/admin/salesMStore">销量管理</a></li>-->
  <#--</ul>-->
  <#--<ul class="nav-left ">-->
      <#--<li ><a href="/admin/staffMPromoter">促销员管理</a></li>-->
      <#--<li ><a href="/admin/salesMObserver">督导管理</a></li>-->
      <#--<li><a href="/admin/staffMCheckingInit">考勤报表</a></li>-->
      <#--<li><a href="/admin/staffMRoundInit">巡店报表</a></li>-->
    <#--<li class="active">员工名单</li>-->
  <#--</ul>-->
<#include "/template.ftl">
  <table class="nav-table m-b-0 " border="1">
      <caption >
          开始日期
          <input id="beginDate" name="beginDate" type="text" onClick="WdatePicker()"/>
          结束日期
          <input id="endDate" name="endDate" type="text" onClick="WdatePicker()"/>
          <button class="btn btn-default radius p-5" onclick="exlQuery()">查询</button>
          <button class="btn btn-default radius p-5">导出报表</button>
      </caption>
    <thead>
      <tr>
        <td>城市</td>
        <td>门店</td>
        <td>姓名</td>
        <td>工号</td>
        <td>联系方式</td>
        <td>职位</td>
        <td>上级</td>
        <td>入职时间</td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
      </tr>
    </tbody>
  </table>

<#--</div>-->
<hr>
</body>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</html>