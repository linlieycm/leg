<!DOCTYPE html>
<html lang="en">
<head>
  <title>Document</title>
  <title>员工信息变更</title>
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
<input type="hidden" id="userId" name="userId" value="${userId}">
<input type="hidden" id="subUserId" name="subUserId" value="${subUserId}">
  <div class="p-10">
    <div class="p-lr-20">
        <table class="w-full border-radius-none">
            <tr class="b-b">
                <td><a href="/steer/steerMInfoAdjust">员工信息</a></td>
                <td><a href="/steer/steerMAddStaff">新增员工</a></td>
            </tr>
        </table>
    </div>
    <div class=" p-15 m-t-25 m-b-30 minH-200 border-radius maxH-300 bg-white relative">
      <div class="b-b clearfix">
        信息变更
      </div>
      <br>
      <label for="" class="inline-block">姓名：</label>
      <input type="text" readonly value="${userRecrod.get("Name")}">
      <br><br>

      <label for="" class="inline-block">电话：</label>
      <input type="text" name="contact" id="contact" value="${userRecrod.get("Contact")}">
      <button class="btn btn-green p-lr-5 radius pull-right  b-n" onclick="saveInfo()">保存</button>
      <br><br>

      <label for="" class="inline-block">门店：</label>
      <select name="storeId" id="storeId">
          <option value="${userRecrod.get("StoreId")}">${storeRecord.get("StoreName")}</option>
        <#list storeList as store>
            <option value="${store.get("ID")}">${store.get("StoreName")}</option>
        </#list>
      </select>
      <button class="btn btn-green p-lr-5 radius pull-right b-n" onclick="saveInfo()">保存</button>

    </div>
  </div>
</body>
<script>
  var submit    = document.getElementById('submit');
  var confirm   = document.getElementById('confirm');
  var cancel    = document.getElementById('cancel');
  var dialog    = document.getElementsByClassName('dialog')[0];
  submit.addEventListener('click', function(){
    dialog.style.display = 'block';
  },false);

  confirm.addEventListener('click', function() {
      dialog.style.display = 'none';
      var userId   = document.getElementById('userId').value;
      var subUserId   = document.getElementById('subUserId').value;
      var year = document.getElementById('year').value;
      var month = document.getElementById('month').value;
      var day = document.getElementById('day').value;
      window.location.href="/steer/steerMInfoQuit?userId=" + userId +
              "&subUserId=" + subUserId +
              "&year=" + year +
              "&month=" + month +
              "&day=" + day;
  }, false);

  cancel.addEventListener('click', function() {
    dialog.style.display = 'none';
  }, false);

    function saveInfo(){
        var contact   = document.getElementById('contact').value;
        var storeId   = document.getElementById('storeId').value;
        var userId   = document.getElementById('userId').value;
        var subUserId   = document.getElementById('subUserId').value;
        window.location.href="/steer/steerMInfoUpdate?userId=" + userId + "&subUserId=" + subUserId + "&contact=" + contact + "&storeId=" + storeId;
    }
</script>
<script src="/js/initDate.js"></script>
<script>
    var a = new DateSelector({
        yearEle   : document.getElementById('year'),
        monthEle  : document.getElementById('month'),
        dayEle    : document.getElementById('day'),
        hourEle   : document.getElementById('hour'),
        minuteEle : document.getElementById('minute')
    });

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