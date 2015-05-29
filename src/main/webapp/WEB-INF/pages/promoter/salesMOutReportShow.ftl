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
  <div class="p-15">
    <label for="" class="block m-b-20">门店：<span id="store">上海交通大学教育学校</span></label>
    <label for="" class="block m-b-20">促销员：<span id="name">养xx</span></label>
    <label for="" class="inline-block ">断货时间：</label>
    <div class="inline-block m-b-20" id="selector-date">
      <select name="" id="year"></select>
      <select name="" id="month"></select>
      <select name="" id="day"></select>
    </div>

    <br>

    <div class="inline-block m-b-20 p-l-74" id="selector-time">
      <select name="" id="hour"></select>
      <select name="" id="minute"></select>
    </div>
    <br>

    <label for="" class="inline-block ">断货SKU：</label>
    <select name="" id="" class="w-80">
        <#list skuList as sku>
            <option value="${sku}">${sku}</option>
        </#list>
    </select>

    <label for="" class="block m-t-30">备注：</label>
    <textarea name="" id="" cols="30" rows="10" class="border-radius w-full m-t-5">

    </textarea>
  </div>
  <button class="btn w-full btn-orange btn-sign" id="submit">
      提交
  </button>

</body>
<script src="/js/initDate.js"></script>
<script>
var a = new DateSelector({
  yearEle   : document.getElementById('year'),
  monthEle  : document.getElementById('month'),
  dayEle    : document.getElementById('day'),
  hourEle   : document.getElementById('hour'),
  minuteEle : document.getElementById('minute')
});
</script>
</html>