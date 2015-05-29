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
<form action="/promoter/salesOutReportInsert" method="post">
    <input type="hidden" name="userId" value="${userId}">
  <div class="p-15">
    <label for="" class="block m-b-20">门店：<span id="store">${storeName}</span></label>
    <label for="" class="block m-b-20">促销员：<span id="name">${name}</span></label>
    <label for="" class="inline-block ">断货时间：</label>
      <input type="date" id="queryDate" name="queryDate">
    <br>
      <br>
    <label for="" class="inline-block ">断货SKU：</label>
    <select name="outSKU" id="outSKU" class="w-80">
        <#list skuList as sku>
            <option value="${sku.get("SKU")}">${sku.get("SKU")}-${sku.get("ProductName")}</option>
        </#list>
    </select>

    <label for="" class="block m-t-30">备注：</label>
    <textarea name="remark" id="remark" cols="30" rows="10" class="border-radius w-full m-t-5">

    </textarea>
  </div>
  <button class="btn w-full btn-orange btn-sign" id="submit">
      提交
  </button>
</form>
</body>
</html>