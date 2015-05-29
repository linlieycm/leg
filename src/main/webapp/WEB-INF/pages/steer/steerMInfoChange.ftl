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
<input type="hidden" id="subUserId" name="subUserId" value="${userRecrod.get("ID")}">
<div class="p-20">
    <div class="p-lr-20">
        <table class="w-full border-radius-none">
            <tr class="b-b">
                <td><a href="/steer/steerMInfoAdjust">员工信息</a></td>
                <td><a href="/steer/steerMAddStaff">新增员工</a></td>
            </tr>
        </table>
    </div>
    <div class="table-div m-b-20 p-lr-20-i m-t-25 border-radius relative">
        <div class="dialog w-200 hide">
            <div class="border-radius bg-white p-20">
                <label for="" class="inline-block">离职时间：</label>
                <div class="inline-block m-b-20" id="selector-date">
                    <input type="date" id="queryDate" name="queryDate">
                </div>
                <br><br>
                <h3 class="text-center">确定终止账号?</h3>
                <div class="text-center">
                    <button class="btn btn-default radius m-r-10" id="confirm">确定</button>
                    <button class="btn btn-default radius" id="cancel">取消</button>
                </div>
            </div>
        </div>
        <table class="w-full border-radius-none">
            <thead>
            <tr class="b-b">
                <td class="text-left">信息变更</td>
                <td><button class="btn radius btn-black b-n p-lr-10" id="submit">离职</button></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="text-left">姓名：${userRecrod.get("Name")}</td>
                <td></td>
            </tr>
            <tr>
                <td class="text-left">联系方式：${userRecrod.get("Contact")}</td>
                <td><button class="btn radius btn-orange b-n p-lr-10" onclick="onchanged(${userRecrod.get("ID")})">变更</button></td>
            </tr>
            <tr>
                <td class="text-left">门店：${storeRecord.get("StoreName")}</td>
                <td><button class="btn radius btn-orange b-n p-lr-10"  onclick="onchanged(${userRecrod.get("ID")})">变更</button></td>
            </tr>
            </tbody>
        </table>
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
        var userId = document.getElementById('userId').value;
        var subUserId = document.getElementById('subUserId').value;
        var queryDate = document.getElementById('queryDate').value;
        window.location.href="/steer/steerMInfoQuit?userId=" + userId +
                "&subUserId=" + subUserId +
                "&queryDate=" + queryDate;

    }, false);

    cancel.addEventListener('click', function() {
        dialog.style.display = 'none';
    }, false);
    function onchanged(subUserId) {
        var userId   = document.getElementById('userId').value;
        window.location.href="/steer/steerMInfoChangeEdit?subUserId=" + subUserId + "&userId=" + userId;
    }
</script>
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