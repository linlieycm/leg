<!DOCTYPE html>
<html lang="en">
<head>
  <title>上岗报表</title>
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
    function tableToJson(tableid) {
        var txt = "{";
        var table = document.getElementById(tableid);
        var row = table.getElementsByTagName("tr");
        var col = row[0].getElementsByTagName("th");
        var tds = table.getElementsByTagName("input");
        for (var j = 1; j < row.length; j++) {
            var r = "";
            var ss = row[j].getElementsByTagName("td");
            r += "\"" + ss[0].innerHTML + "\":\"" + tds[j-1].value + "\",";
            r = r.substring(0, r.length - 1)
            r += ",";
            txt += r;
        }
        txt = txt.substring(0, txt.length - 1);
        txt += "}";

        document.getElementById("giftJson").value = txt;
    }
</script>
<body class="bg-dark ">
<form action="/promoter/salesMUpInsert" enctype="multipart/form-data" method="post">
    <input type="hidden" name="userId" value="${userId}">
    <input type="hidden" name="giftJson" id="giftJson">
  <div class="p-20 m-b-100 clearfix">
    <label for="" class="block m-b-20">促销品库存统计</label>
    <div class="table-div m-b-20">
      <table class="w-full border-radius-none" id="giftTable">
      <thead>
        <tr class="b-b">
            <th>ID</th>
            <th>赠品名称</th>
            <th>数量</th>
        </tr>
      </thead>
      <tbody>
        <#list giftList as gift>
            <tr>
                <td>${gift.get("ID")}</td>
                <td>${gift.get("GiftName")}</td>
                <td><input type="text" id="gift_${gift.get("ID")}" name="gift_${gift.get("ID")}" class="w-80" value="0"></td>
            </tr>
        </#list>
      </tbody>
      </table>
    </div>

    <label for="" class="block m-b-20">排面照片拍摄</label>
    <div class="upload m-b-20">
        <input type="file" name="image" id="file" style="width:160px;" />
    </div>

    <label for="" class="inline-block">产品库存情况</label>
    <select name="stockStatus" id="stockStatus" class="pull-right w-150">
      <option value="1">充裕</option>
      <option value="2">紧张</option>
      <option value="3">断货</option>
    </select>
  </div>
  <button class="btn w-full btn-orange btn-sign" id="submit" onclick="tableToJson('giftTable')">
     提交
  </button>
</form>
</body>
</html>