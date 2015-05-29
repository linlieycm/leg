<!DOCTYPE html>
<html lang="en">
<head>
  <title>销量确认</title>
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
    function doUpdate() {

        var userId = document.getElementById('userId').value;
        var workInfoId = document.getElementById('workInfoId').value;

        window.location.href = "/steer/steerMConfirmUpdate?workInfoId=" + workInfoId + "&userId=" + userId;
    }
</script>
<body class="bg-dark ">
<input type="hidden" id="userId" name="userId" value="${userId}">
<input type="hidden" id="workInfoId" name="workInfoId" value="${workInfoId}">
  <div class="p-20  clearfix">
    <label for="" class="block m-b-20">销量统计－交通大学店 张三</label>
    <div class="table-div m-b-20 m-t-10">
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
    </div>
      <div class="table-div m-b-20">
          <table class="w-full border-radius-none">
              <thead>
              <tr class="b-b">
                  <th>ID</th>
                  <th>赠品名称</th>
                  <th>数量</th>
              </tr>
              </thead>
              <tbody>
              <#list giftResultList as gift>
              <tr>
                  <td>${gift.get("giftId")}</td>
                  <td>${gift.get("giftName")}</td>
                  <td>${gift.get("num")}</td>
              </tr>
              </#list>
              </tbody>
          </table>
      </div>

    <label for="" class="block m-b-20 m-t-20">排面照片拍摄</label>
    <div class="upload m-b-20">
        <img src="/image/${oldRecord.get("PicIdList")}" alt="">
    </div>

      <table class="w-full border-radius-none">
          <thead>
          <tr class="b-b">
          </tr>
          </thead>
          <tbody>
          <tr>
              <td>产品库存情况</td>
              <td>
              <#if stockStatus == 1>
                  充裕
              </#if>
              <#if stockStatus == 2>
                  紧张
              </#if>
              <#if stockStatus == 3>
                  断货
              </#if>
              </td>
          </tr>
          </tbody>
      </table>

  </div>
  <div class="text-center m-b-100">
      <button class="btn btn-default radius m-t-10 p-lr-20" onclick="doUpdate()">确认销量</button>
  </div>
</body>
</html>