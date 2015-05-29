<!DOCTYPE html>
<html lang="en">
<head>
  <title>销量查询</title>
  <meta name="Keywords" content=""/>
  <meta name="Description" content=""/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta name="apple-mobile-web-app-status-bar-style" content="black" />
  <meta name="format-detection" content="telephone=no" />
  <meta name="format-detection" content="email=no" />
  <meta name="apple-mobile-web-app-title" content="">
  <link rel="stylesheet" href="/css/normalize.css">
  <link rel="stylesheet" href="/css/main.css">
</head>
<script>
    function gradeChange() {
        var subUserId   = document.getElementById('subUserId').value;
        if(!subUserId) {
            return;
        }
        var storeId   = document.getElementById('storeId').value;
        if(!storeId) {
            return;
        }
        var queryDate   = document.getElementById('queryDate').value;
        if(!queryDate) {
            return;
        }
        window.location.href="/steer/steerMCheckQuery?subUserId=" + subUserId + "&storeId=" + storeId + "&queryDate=" + queryDate;
    }
</script>
<body class="bg-dark ">
  <div class="p-20 m-b-100 clearfix">

      <table class="w-full bg-none">
          <thead>
              <tr>
                  <td class="text-left">门店:<select name="storeId" id="storeId" class="w-full">
                  <#list storeInfoList as storeInfo>
                      <option value="${storeInfo.get("ID")}">${storeInfo.get("StoreName")}</option>
                  </#list>
                  </select>
                  </td>
              </tr>
          </thead>
          <tbody>
              <tr>
                <td class="text-left">促销员:<select name="subUserId" id="subUserId" class="w-full">
                    <#list userinfoList as userinfo>
                        <option value="${userinfo.get("userId")}">${userinfo.get("name")}</option>
                    </#list>
                    </select>
                </td>
              </tr>
              <tr>
                  <td class="text-left">
                      日期:<input type="date" value="${queryDate}" id="queryDate" name="queryDate">
                  </td>
              </tr>
              <tr>
                  <td class="text-left">
                      <button onclick="gradeChange()">查询</button>
                  </td>
              </tr>
          </tbody>
      </table>

    <div class="table-div m-b-20 m-t-10">
        <#if saleResultList??>
        <table class="w-full border-radius-none">
            <thead>
            <tr class="b-b">
                <th>ID</th>
                <td>产品单价</td>
                <td>单价</td>
                <td>数量</td>
                <td>金额</td>
            </tr>
            </thead>
            <tbody>
            <#list saleResultList as sale>
            <tr>
                <td>${sale.get("saleId")}</td>
                <td>${sale.get("saleName")}</td>
                <td>${sale.get("price")}</td>
                <td>${sale.get("num")}</td>
                <td>${sale.get("total")}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        <#else>
            <label for="" class="block m-b-10 ">没有记录</label>
        </#if>
    </div>
  </div>

</body>
</html>