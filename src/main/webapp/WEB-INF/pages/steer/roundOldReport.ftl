<!DOCTYPE html>
<html lang="en">
<head>
  <title>巡店报告历史</title>
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
    function gradeChange() {
        var storeId   = document.getElementById('storeId').value;
        if(!storeId) {
            return;
        }
        var queryDate   = document.getElementById('queryDate').value;
        if(!queryDate) {
            return;
        }
        window.location.href="/steer/roundOldReport?queryDate=" + queryDate + "&storeId=" + storeId;
    }
</script>
<body class="bg-dark ">
<input type="hidden" id="userId" name="userId" value="${userId}">
<div class="p-lr-20">
    <table class="w-full border-radius-none">
        <tr class="b-b">
            <td><a href="/steer/roundNewReport">巡店新增</a></td>
            <td><a href="/steer/roundOldReport">历史查询</a></td>
        </tr>
    </table>
</div>
    <div class="p-20 m-b-100 clearfix">
        <label for="" class="block m-b-20">查看门店</label>
        <table class="w-full bg-none">
            <tbody>
                <tr>
                    <td class="text-left">门店：
                        <select name="storeId" id="storeId">
                            <option value="${storeId}">${storeId}</option>
                        <#if storeinfoList??>
                            <#list storeinfoList as storeinfo>
                                <option value="${storeinfo.get("ID")}">${storeinfo.get("StoreName")}</option>
                            </#list>
                        </#if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="text-left">日期：
                        <input type="date" value="${queryDate}" id="queryDate" name="queryDate" >
                    </td>
                </tr>
                <tr>
                    <td class="text-left">
                        <button onclick="gradeChange()">查询</button>
                    </td>
                </tr>
            </tbody>

        </table>
    <#if workInfo??>
    <label for="" class="block m-b-20 m-t-20">排面照片</label>

    <div class="upload-big m-b-20">
        <img src="/image/${workInfo.get("PicIdList")}" alt="">
    </div>

    <label for="" class="block m-b-10">备注</label>

      <label for="" class="block">${workInfo.get("Remark")}</label>
  <#else>
      <label for="" class="block">没有记录！</label>
  </#if>

  </div>

</body>
</html>