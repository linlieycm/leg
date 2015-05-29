<!DOCTYPE html>
<html lang="en">
<head>
  <title>断货警报</title>
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
<body class="bg-dark">
<input type="hidden" id="userId" name="userId" value="${userId}">
  <div class="p-15">
      <table class="w-full bg-none">
          <tbody>
              <tr>
                  <td class="text-left">门店</td>
                  <td class="text-left">${outStock.get("storeName")}</td>
              </tr>
              <tr>
                  <td class="text-left">促销员</td>
                  <td class="text-left">${outStock.get("userName")}</td>
              </tr>
              <tr>
                  <td class="text-left">断货时间</td>
                  <td class="text-left">${outStock.get("reportDate")}</td>
              </tr>
              <tr>
                  <td class="text-left">断货SKU</td>
                  <td class="text-left">${outStock.get("outSKU")}--${outStock.get("productName")}</td>
              </tr>
              <tr>
                  <td class="text-left">备注</td>
                  <td class="text-left">${outStock.get("remark")}</td>
              </tr>
          </tbody>
      </table>
    </textarea>
  </div>
  <button class="btn w-full btn-orange btn-sign" id="submit" onclick="onUpdate(${outStock.get("id")})">
      确认
  </button>

</body>
<script src="/js/initDate.js"></script>
<script>
function onUpdate(stockId) {
    var userId   = document.getElementById('userId').value;
    window.location.href="/steer/steerMOutWarnUpdate?userId=" + userId + "&stockId=" + stockId;
}
</script>
</html>