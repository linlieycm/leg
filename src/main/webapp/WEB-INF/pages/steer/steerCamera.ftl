<!DOCTYPE html>
<html lang="en">
<head>
  <title>签到</title>
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
<body class="bg-gray">
  <p class="location-fail">读取地理位置信息失败，<br>
      请使用照相机拍摄现场图片签到
  </p>
  <img src="/assets/camera.png" class="w-full" alt="">
  <button class="btn w-full btn-orange btn-sign" id="submit">
    <img src="/assets/aim.png" class="icon-location">打卡，签到
  </button>
</body>
<script src="/js/zepto/zepto.min.js"></script>
<script>
$('#submit').on('click', function() {
  alert('submit');
});
</script>
</html>