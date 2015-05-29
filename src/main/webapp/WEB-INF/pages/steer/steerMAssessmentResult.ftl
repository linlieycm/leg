<!DOCTYPE html>
<html lang="en">
<head>
  <title>Document</title>
  <title>员工评价</title>
  <meta name="Keywords" content=""/>
  <meta name="Description" content=""/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <meta name="apple-mobile-web-app-status-bar-style" content="black" />
  <meta name="format-detection" content="telephone=no" />
  <meta name="format-detection" content="email=no" />
  <meta name="apple-mobile-web-app-title" content="">
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">
</head>
<body class="bg-gray">
<input type="hidden" id="userId" name="userId" value="${userId}">
  <div class="p-20">
    <div class="p-lr-20">
        <table class="w-full border-radius-none">
            <tr class="b-b">
                <td><a href="/steer/steerMInfoAdjust">员工信息</a></td>
                <td><a href="/steer/steerMAddStaff">新增员工</a></td>
            </tr>
        </table>
    </div>
    <div class="table-div  p-lr-20-i m-t-25 border-radius maxH-300 l-h-20">
      <p class="text-center b-b">
        该员工评价
      </p>
      <p class="b-b p-l-5">
        主动接触： 8 <br>
        熟练掌握产品卖点: 8 <br>
        送别时保持微笑: 8 <br>
      </p>
      <p>总分：24</p>
    </div>
    <div class="m-t-20 m-b-20 text-center">
      <button class="btn btn-default p-lr-20 m-r-20 radius">修改</button>
      <button class="btn btn-green p-lr-20 radius b-n">退出</button>

    </div>
  </div>
</body>
<script>
    function steerMInfoAdjust() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMInfoAdjust?userId=" + userId;
    }

    function steerMAddStaff() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMAddStaff?userId=" + userId;
    }
</script>
</html>