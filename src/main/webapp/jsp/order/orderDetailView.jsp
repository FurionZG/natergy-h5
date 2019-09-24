<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mui.min.css">
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

			<label class="hd">收货人：&nbsp &nbsp </label> <label class="context" id="consignee"></label>
			<hr>

			<label class="hd">收货地址：</label> <label class="context" id="receivingAddress"></label>
			<hr>

			<label class="hd">收票人：&nbsp &nbsp</label> <label class="context" id="collector"></label>
			<hr>

			<label class="hd">收票地址：</label> <label class="context" id="invoiceAddress"></label>
			<hr>

			<label class="hd">注意事项：</label> <label class="context" id="attention"></label>
			<hr>

			<label class="hd">发票事项：</label><label class="context" id="invoiceAttention"></label>
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
				var orderDetails = eval(data.orderDetails);
				$("#customerName").text(data.customerName);
				$("#customerName").text(data.customerName);
				$("#consignee").text(data.consignee);
				$("#receivingAddress").text(data.receivingAddress);
				$("#collector").text(data.collector);
				$("#invoiceAddress").text(data.invoiceAddress);
				$("#attention").text(data.attention);
				$("#invoiceAttention").text(data.invoiceAttention);
				var htmlText = "";

				htmlText = htmlText + "<div class='mui-slider-group mui-slider-loop'>";
				htmlText = htmlText + "<div class='mui-slider-item mui-slider-item-duplicate'><a href='#'>";
				//在第一个轮播之前添加最后一个轮播
				htmlText = htmlText + "<div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'>";
				htmlText = htmlText + "<tr><th width=50%>规格</th><th width=50%>生产商</th></tr><tr><td>" + orderDetails[orderDetails.length - 1].size + "</td><td>" + data.producer + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + orderDetails[orderDetails.length - 1].innerWrapper + "</td><td>" + orderDetails[orderDetails.length - 1].outwrapper + "</td></tr><tr><th width=50%>单价</th><th width=50%>数量</th></tr><tr><td>" + orderDetails[orderDetails.length - 1].price + "</td><td>" + orderDetails[orderDetails.length - 1].count + "</td></tr><tr><th width=50%>回扣</th><th width=50%>是否含税</th></tr><tr><td>" + orderDetails[orderDetails.length - 1].rebate + "</td><td>" + orderDetails[orderDetails.length - 1].tax + "</td></tr><tr><th width=50%>净重</th><th width=50%>金额</th></tr><tr><td>" + orderDetails[orderDetails.length - 1].totalWeight + "</td><td>" + orderDetails[orderDetails.length - 1].totalPrice + "</td></tr>";
				htmlText = htmlText + "</table></div></a></div>";

				for(var i in orderDetails) {
					htmlText = htmlText + "<div class='mui-slider-item'><a href='#'><div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'><tr><th width=50%>规格</th><th width=50%>生产商</th></tr><tr><td>" + orderDetails[i].size + "</td><td>" + data.producer + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + orderDetails[i].innerWrapper + "</td><td>" + orderDetails[i].outwrapper + "</td></tr><tr><th width=50%>单价</th><th width=50%>数量</th></tr><tr><td>" + orderDetails[i].price + "</td><td>" + orderDetails[i].count + "</td></tr><tr><th width=50%>回扣</th><th width=50%>是否含税</th></tr><tr><td>" + orderDetails[i].rebate + "</td><td>" + orderDetails[i].tax + "</td></tr><tr><th width=50%>净重</th><th width=50%>金额</th></tr><tr><td>" + orderDetails[i].totalWeight + "</td><td>" + orderDetails[i].totalPrice + "</td></tr></table></div></a></div>";

					//console.log(orderDetails[i]);
				}
				htmlText = htmlText + "<div class='mui-slider-item mui-slider-item-duplicate'><a href='#'><div style='margin-left: 15px; margin-right: 15px;'><table width='100%' class='table' id='tablevalue'><tr><th width=50%>规格</th><th width=50%>生产商</th></tr><tr><td>" + orderDetails[0].size + "</td><td>" + data.producer + "</td></tr><tr><th width=50%>内包装</th><th width=50%>外包装</th></tr><tr><td>" + orderDetails[0].innerWrapper + "</td><td>" + orderDetails[0].outwrapper + "</td></tr><tr><th width=50%>单价</th><th width=50%>数量</th></tr><tr><td>" + orderDetails[0].price + "</td><td>" + orderDetails[0].count + "</td></tr><tr><th width=50%>回扣</th><th width=50%>是否含税</th></tr><tr><td>" + orderDetails[0].rebate + "</td><td>" + orderDetails[0].tax + "</td></tr><tr><th width=50%>净重</th><th width=50%>金额</th></tr><tr><td>" + orderDetails[0].totalWeight + "</td><td>" + orderDetails[0].totalPrice + "</td></tr></table></div></a></div></div>";

				htmlText = htmlText + "<div class='mui-slider-indicator'><div class='mui-indicator mui-active'></div>";
				for(var i = 0; i < orderDetails.length - 1; i++) {
					htmlText = htmlText + "<div class='mui-indicator'></div>";
				}
				htmlText = htmlText + "</div></div>";
				$("#slider").html(htmlText).trigger('create');
				var gallery = mui('.mui-slider');
				gallery.slider({
					interval: 0 //自动轮播周期，若为0则不自动播放，默认为0；
				});
				if(1 == orderDetails.length) {

					mui('.mui-slider').slider().stopped = true;
				}

			}
		</script>

	</body>

</html>