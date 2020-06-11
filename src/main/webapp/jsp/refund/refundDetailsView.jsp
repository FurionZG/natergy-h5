<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>退货明细</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!--标准mui.css-->
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <!--App自定义的css-->
    <!--<link rel="stylesheet" type="text/css" href="../css/app.css"/>-->
    <style>
        label {
            font-size: 14px;

        }

        .table {
            table-layout: fixed;
        }

        .table th {
            height: 30px;
        }

        .table td,
        .table th {
            border: 1px solid #cad9ea;
            text-align: center;
            height: 20px;
        }
    </style>

</head>

<body>

<form class="mui-input-group" style="margin: 10px 5px 0px 5px; border-radius: 10px;">

    <hr>
    <label class="hd">客户名称：</label> <label class="context" id="customerName"></label>
    <hr>

    <label class="hd">退货地址： </label> <label class="context" id="refundAddress"></label>
    <hr>

    <label class="hd">退货联系人：</label> <label class="context" id="refundContacts"></label>
    <hr>

    <label class="hd">退至仓库：</label> <label class="context" id="toDepot"></label>
    <hr>

</form>

<div id="slider" class="mui-slider"></div>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript " charset="utf-8 ">
    mui.init({
        swipeBack: false //关闭右滑关闭功能
    });
</script>
<script type="text/javascript " charset="utf-8 ">
    function child(json) {
        var data = eval("(" + json + ")")
        var refundDetails = eval(data.refundDetails);
        $("#customerName").text(data.customerName);
        $("#refundAddress").text(data.refundAddress);
        $("#refundContacts").text(data.refundContacts);
        $("#toDepot").text(data.toDepot);
        var htmlText = "";

        console.log(refundDetails);
        htmlText = htmlText + "<div class='mui-slider-group mui-slider-loop'>";
        htmlText = htmlText + "<div class='mui-slider-item mui-slider-item-duplicate'><a href='#'>";
        //在第一个轮播之前添加最后一个轮播
        htmlText = htmlText + "<div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'>";
        htmlText = htmlText + "<tr><th width=50%>规格(mm)</th><th width=50%>退货数量(kg)</th></tr><tr><td>" + refundDetails[refundDetails.length - 1].size + "</td><td>" + refundDetails[refundDetails.length - 1].totalWeight + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + refundDetails[refundDetails.length - 1].innerWrapper + "</td><td>" + refundDetails[refundDetails.length - 1].outWrapper + "</td></tr><tr><th width=50%>单价</td><th width=50%>是否含税</td></tr><tr><td>"+refundDetails[refundDetails.length - 1].price+"</td><td width=50%>"+refundDetails[refundDetails.length - 1].tax+"</td></tr>";
        htmlText = htmlText + "</table></div></a></div>";

        for(var i in refundDetails) {
            htmlText = htmlText + "<div class='mui-slider-item'><a href='#'><div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'><tr><th width=50%>规格(mm)</th><th width=50%>退货数量(kg)</th></tr><tr><td>" + refundDetails[i].size + "</td><td>" + refundDetails[i].totalWeight + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + refundDetails[i].innerWrapper + "</td><td>" + refundDetails[i].outWrapper + "</td></tr><tr><th width=50%>单价</td><th width=50%>是否含税</td></tr><tr><td>"+refundDetails[i].price+"</td><td width=50%>"+refundDetails[i].tax+"</td></tr></table></div></a></div>";

            //console.log(orderDetails[i]);
        }
        htmlText = htmlText + "<div class='mui-slider-item mui-slider-item-duplicate'><a href='#'><div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'><tr><th width=50%>规格(mm)</th><th width=50%>退货数量(kg)</th></tr><tr><td>" + refundDetails[0].size + "</td><td>" + refundDetails[0].totalWeight + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + refundDetails[0].innerWrapper + "</td><td>" + refundDetails[0].outWrapper + "</td></tr><tr><th width=50%>单价</td><th width=50%>是否含税</td></tr><tr><td>"+refundDetails[0].price+"</td><td width=50%>"+refundDetails[0].tax+"</td></tr></table></div></a></div></div>";

        htmlText = htmlText + "<div class='mui-slider-indicator'><div class='mui-indicator mui-active'></div>";
        for(var i = 0; i < refundDetails.length - 1; i++) {
            htmlText = htmlText + "<div class='mui-indicator'></div>";
        }
        htmlText = htmlText + "</div></div>";
        $("#slider").html(htmlText).trigger('create');
        var gallery = mui('.mui-slider');
        gallery.slider({
            interval: 0 //自动轮播周期，若为0则不自动播放，默认为0；
        });
        if(1 == refundDetails.length) {

            mui('.mui-slider').slider().stopped = true;
        }

    }
</script>

</body>

</html>
<body>

</body>
</html>
