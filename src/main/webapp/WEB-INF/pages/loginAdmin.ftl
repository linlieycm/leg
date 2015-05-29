<!DOCTYPE html>
<html lang="en">
<head>
  <title>登陆</title>
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
<body class="bg-gray" onload="loadMessage()">
    <form action="/loginAdminIn" method="post">
        <div class="sign-form">
        <input type="hidden" id="message" name="message" value="${message}">
        <label for="userid" class="inline-block m-t-25">登陆ID</label><br>
        <input type="text" id="userid" name="userid" class="block w-full" >

        <label for="password">密码</label><br>
        <input type="password" id="password" name="password" class="block w-full" >

        </div>
        <button class="btn w-full btn-orange btn-sign" id="submit">
        <img src="/assets/aim.png" class="icon-location"></img>登陆
        </button>
    </form>
</body>
<script src="/js/zepto/zepto.min.js"></script>
<script>
    $('#submit').on('click', function() {
      alert('submit');
    });
</script>

<script>
    function loadMessage(){
//        var v = document.getElementById("message").value;
//        if(!v) {
//            alert(v);
//        }
    }
</script>

</html>