<!DOCTYPE html>
<html lang="en">
<head>
  <title>离岗报表</title>
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
    function cal(price, num , total){
        var re = /^[0-9]+.?[0-9]*$/;
        var nPrice = Number(document.getElementById(price).value);
        if (!re.test(nPrice)) {
            alert("请输入数字");
            document.getElementById(price).value = "";
            return;
        }
        var nNum = Number(document.getElementById(num).value);
        if (!re.test(nNum)) {
            alert("请输入数字");
            document.getElementById(num).value = "0";
            return;
        }
        var nTotal = nPrice * nNum;
        document.getElementById(total).value = nTotal.toFixed(2);
    }

    function checkNum(giftId) {
        var re = /^[0-9]+.?[0-9]*$/;
        var nPrice = Number(document.getElementById(giftId).value);
        if (!re.test(nPrice)) {
            alert("请输入数字");
            document.getElementById(giftId).value = "0";
            return;
        }
    }

    function tableToJson(giftTable, saleTable) {
        var gifttxt = "{";
        var gifttable = document.getElementById(giftTable);
        var giftrow = gifttable.getElementsByTagName("tr");
        var giftcol = giftrow[0].getElementsByTagName("th");
        var gifttds = gifttable.getElementsByTagName("input");
        for (var giftj = 1; giftj < giftrow.length; giftj++) {
            var giftr = "";
            var giftss = giftrow[giftj].getElementsByTagName("td");
            giftr += "\"" + giftss[0].innerHTML + "\":\"" + gifttds[giftj-1].value + "\",";
            giftr = giftr.substring(0, giftr.length - 1);
            giftr += ",";
            gifttxt += giftr;
        }
        gifttxt = gifttxt.substring(0, gifttxt.length - 1);
        gifttxt += "}";

        document.getElementById("giftJson").value = gifttxt;

        var saletxt = "{";
        var saletable = document.getElementById(saleTable);
        var salerow = saletable.getElementsByTagName("tr");
        var salecol = salerow[0].getElementsByTagName("th");
        var saletds = saletable.getElementsByTagName("input");
        for (var salej = 2; salej < salerow.length; salej += 2) {
            var saler = "";
            var saless = salerow[salej].getElementsByTagName("td");
            saler += "\"" + saless[0].innerHTML + "1\":\"" + saletds[3*salej/2-3].value + "\",";
            saler = saler.substring(0, saler.length - 1);
            saler += ",";
            saler += "\"" + saless[0].innerHTML + "2\":\"" + saletds[3*salej/2-2].value + "\",";
            saler = saler.substring(0, saler.length - 1);
            saler += ",";
            saler += "\"" + saless[0].innerHTML + "3\":\"" + saletds[3*salej/2-1].value + "\",";
            saler = saler.substring(0, saler.length - 1);
            saler += ",";
            saletxt += saler;
        }
        saletxt = saletxt.substring(0, saletxt.length - 1);
        saletxt += "}";

        document.getElementById("saleJson").value = saletxt;
    }
</script>
<body class="bg-dark">
<form action="/promoter/salesMDownInsert"  enctype="multipart/form-data" method="post">
    <input type="hidden" name="userId" value="${userId}">
    <input type="hidden" name="giftJson" id="giftJson">
    <input type="hidden" name="saleJson" id="saleJson">
  <div class="p-20 m-b-100 clearfix">
    <label for="" class="block m-b-20">销量统计</label>
    <div class="table-div m-b-20">
      <table class="w-full border-radius-none" id="saleTable">
        <thead>
          <tr class="b-b">
            <th>ID</th>
            <th>单价</th>
            <th>数量</th>
            <th>金额</th>
          </tr>
        </thead>
        <tbody>
        <#list saleList as sale>
        <tr>
            <td colspan="4">${sale.get("ID")}-${sale.get("ProductName")}</td>
        </tr>
        <tr>
            <td>${sale.get("ID")}</td>
            <td><input class="w-50" id="price${sale.get("ID")}" name="price${sale.get("ID")}" onkeyup="cal('price${sale.get("ID")}','num${sale.get("ID")}','total${sale.get("ID")}')" value="0"></td>
            <td><input class="w-50" id="num${sale.get("ID")}" name="num${sale.get("ID")}" onkeyup="cal('price${sale.get("ID")}','num${sale.get("ID")}','total${sale.get("ID")}')" value="0"></td>
            <td><input class="w-50" id="total${sale.get("ID")}" name="total${sale.get("ID")}" value="0" readonly="true"></td>
        </tr>
        </#list>
        </tbody>
      </table>
    </div>
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
                <td><input type="text" id="gift_${gift.get("ID")}" name="gift_${gift.get("ID")}" value="0" class="w-50" onkeyup="checkNum('gift_${gift.get("ID")}')"></td>
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
    <button class="btn w-full btn-orange btn-sign" id="submit" onclick="tableToJson('giftTable', 'saleTable')">
        提交
    </button>
</form>
</body>
</html>