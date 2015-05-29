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
  <div class="p-10">
    <div class="p-lr-20">
        <table class="w-full border-radius-none">
            <tr class="b-b">
                <td><a href="/steer/steerMInfoAdjust">员工信息</a></td>
                <td><a href="/steer/steerMAddStaff">新增员工</a></td>
            </tr>
        </table>
    </div>
      <div class="table-div m-b-20 m-t-25 border-radius">
          <table class="w-full border-radius-none">
              <tr>
                  <td>姓名</td>
                  <td><input type="text" id="name" name="name"></td>
              </tr>
              <tr>
                  <td>电话</td>
                  <td><input type="text" id="contact" name="contact"></td>
              </tr>
              <tr>
                  <td>到岗日</td>
                  <td>
                      <input type="date" id="queryDate" name="queryDate">
                  </td>
              </tr>
              <tr>
                  <td>分配门店</td>
                  <td>
                      <select name="storeId" id="storeId">
                      <#list storeinfoList as storeinfo>
                          <option value="${storeinfo.get("ID")}">${storeinfo.get("StoreName")}</option>
                      </#list>
                      </select>
                  </td>
              </tr>
              <tr>
                  <td><button class="btn btn-green m-r-10 radius b-n" onclick="saveStaff()">保存提交</button></td>
                  <td><button class="btn btn-default radius pull-right" onclick="onceagain()">重置</button></td>
              </tr>
          </table>
      </div>
    <div class="p-5 m-t-20 border-radius-5 bg-dark2 text-center">
      新增员工成功后，账号会发送至<br>您的微信，注意查收！
    </div>
  </div>
</body>
<script src="/js/zepto/zepto.js"></script>
<script>
    function saveStaff() {
        var storeId   = document.getElementById('storeId').value;
        if(!storeId) {
            return;
        }
        var queryDate = document.getElementById('queryDate').value;
        if(!queryDate) {
            return;
        }
        var name   = document.getElementById('name').value;
        if(!name) {
            return;
        }
        var contact   = document.getElementById('contact').value;
        if(!contact) {
            return;
        }
        var userId   = document.getElementById('userId').value;
        if(!userId) {
            return;
        }
        window.location.href="/steer/steerMAddStaffDo?storeId=" + storeId
                + "&queryDate=" + queryDate
                + "&name=" + encodeURI(encodeURI(name))
                + "&contact=" + contact
                + "&userId=" + userId;
    }

    function onceagain() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMAddStaff?userId=" + userId;
    }

    function steerMInfoAdjust() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMInfoAdjust?userId=" + userId;
    }

    function steerMAddStaff() {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMAddStaff?userId=" + userId;
//        $.ajax({
//            url: '/steer/steerMAddStaff',
//            data: {
//                useId: userId
//            },
//            success: function(result) {
//                document.getElementById('userId').value = result.userId;
//                //location.reload();
//            }
//        });
    }

</script>
</html>