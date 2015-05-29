<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>员工管理-督导管理-更新</title>
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">
</head>
<script>
    function updObserver(saleId) {
        var storeCity   = document.getElementById('storeCity').value;
        var storeName   = document.getElementById('storeName').value;
        var storeAddress   = document.getElementById('storeAddress').value;
        window.location.href="/admin/updSalesMObserver?storeCity=" + storeCity + "&storeName=" + storeName + "&storeAddress=" + storeAddress;
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
                  <input type="submit" id="searchsubmit2" value="更新" hidefocus="true" class="gradient" style="outline: none;" onclick="updObserver(record.get("ID"))">
              </span>
      </caption>
    <tbody>
      <tr>
          <td>督导</td>
          <td>
              <input id="storeCity" name="storeCity" value="${record.get("StoreCity")}">
          </td>
      </tr>
      <tr>
          <td>门店</td>
          <td>
              <select>
                  <#list recordStoreList as cityRecord>
                    <option value="${cityRecord.get("ID")}">${cityRecord.get("StoreName")}</option>
                  </#list>
              </select>
          </td>
      </tr>
    </tbody>
  </table>
    <#--<button class="btn btn-default radius p-5" onclick="updObserver(record.get("ID"))">更新</button>-->
<#--</div>-->

</body>
</html>