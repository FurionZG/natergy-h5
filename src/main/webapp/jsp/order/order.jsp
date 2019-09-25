<%@page contentType="text/html"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css" />
		<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

		<title></title>

		<style>
			/* pop层样式 */
			
			.mui-plus .plus {
				display: inline;
			}
			
			.plus {
				display: none;
			}
			
			#topPopover {
				position: fixed;
				top: 16px;
				right: 6px;
			}
			
			#topPopover .mui-popover-arrow {
				left: auto;
				right: 6px;
			}
			
			p {
				text-indent: 22px;
			}
			
			span.mui-icon {
				font-size: 16px;
				color: #007aff;
				margin-left: 3px;
				padding-right: 10px;
			}
			
			.mui-popover {
				height: 140px;
				width: 100px;
			}
			
			.mui-content {
				padding: 10px;
			}
			
			.mui-content>.mui-table-view:first-child {
				margin-top: -1px;
			}
			
			selector {
				cursor: pointer;
			}
		</style>
	</head>

	<body>

		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">订单</h1>
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>

		</header>
		<!--右上角弹出菜单-->
		<div id="topPopover" class="mui-popover">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul class="mui-table-view">
						<li class="mui-table-view-cell">
							<a href="javascript:;" onclick="funAdd()" id = "addFollow"> <span class="mui-icon iconfont icon-add"></span>添加
							</a>
						</li>

						<li class="mui-table-view-cell">
							<a href="javascript:;" onclick="funFilter()"> <span class="mui-icon iconfont icon-shaixuan"></span>筛选
							</a>
						</li>

						<li class="mui-table-view-cell">
							<a href="javascript:;" onclick="funRefresh()"> <span class="mui-icon iconfont icon-shuaxin"></span>刷新

							</a>
						</li>
					</ul>
				</div>
			</div>

		</div>
		<!--下拉刷新容器-->
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<!-- limit-->
				<input type="hidden" id="limit" value="" />
				<!--数据列表-->
				<ul class="mui-table-view mui-table-view-chevron" id="orderList">

				</ul>
			</div>
		</div>
		<!--<div style="margin-top: 30px;">
			<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul class="mui-table-view mui-table-view-chevron" id="orderList">

					</ul>
				</div>
			</div>

		</div>-->
		<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/layui/layui.all.js"></script>
		<script type="text/javascript">
			/** 添加订单 **/
			function funAdd() {
				mui.toast('添加订单记录');
				window.location.href = "<%=request.getContextPath()%>/jsp/order/orderAdd.jsp";
			}
			/** 筛选订单 **/
			function funFilter() {
				mui.toast('待开发...');
			}
			/** 刷新订单 **/
			function funRefresh() {
				mui.toast('待开发...');
			}
		</script>

		<script>
			$(document).ready(function loading() {

				var orderInfoListByUser = ${orderInfoListByUser};
				//console.log(orderInfoListByUser);
				//console.log(orderInfoListByUser[0]);
				for(var i = 0; i < orderInfoListByUser.length; i++) { //循环数据
					$("#orderList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
							orderInfoListByUser[i].orderDetails[0].orderNumber +
						"<p class='mui-ellipsis'>" + orderInfoListByUser[i].customerName + "</p>" +
						"<p class='mui-ellipsis'>" + orderInfoListByUser[i].orderTime + "</p>" +
						"<p class='mui-ellipsis'>" + orderInfoListByUser[i].state + "</p>" +
						"<input type='hidden' value ='" + JSON.stringify(orderInfoListByUser[i]) + "'/></div></a></li>");

				}

				mui('body').on('tap', '#orderList li', function() {
					clickLi(this)
				});
				mui('body').on('tap', '#addOrder', function() {
					funAdd()
				});

			});
		</script>
		<script>
			mui.init({
				pullRefresh: {
					container: '#pullrefresh',
					down: {
						style: 'circle',
						callback: pulldownRefresh
					},
					up: {
						auto: false,
						contentrefresh: '正在加载...',
						callback: pullupRefresh
					}
				}
			});

			var count = 0;

			function pullupRefresh() {
				setTimeout(function() {
					$.ajax({
						url: "/natergy-h5/order/reload",
						contentType: "application/x-www-form-urlencoded:charset=UTF-8",
						type: "get",
						timeout: "1000",
						dataType: "json",
						data: {
							"limit": $("#orderList li").length
						},
						success: function(orderList) {
							var lastLi = $("#orderList li:last-child .mui-media-body");
							for(var i = 0; i < orderList.length; i++) { //循环数据
								if(orderList[i].orderDetails[0].orderNumber != lastLi.text().substr(0, 15)) {
									//这个if判断是判断上拉加载的第一个订单的订单号是否等于当前ul中最后一个li的订单号，因为使用limit可能导致一个订单的订单明细被分开，如果两者相等，则不动态生成li
									$("#orderList").append("<li class='mui-table-view-cell mui-media'><a class='mui-navigate-right'><div class='mui-media-body'>" +
										orderList[i].orderDetails[0].orderNumber +
										"<p class='mui-ellipsis'>" + orderList[i].customerName + "</p>" +
										"<p class='mui-ellipsis'>" + orderList[i].orderTime + "</p>" +
										"<p class='mui-ellipsis'>" + orderList[i].state + "</p>" +
										"<input type='hidden' value ='" + JSON.stringify(orderList[i]) + "'/></div></a></li>");
								}
							}
							// mui('body').on('tap', 'li', function() {
							// 	clickLi(this)
							// });
							mui.toast("已为您加加载" + orderList.length + "条...");
						},
						complete: function(XMLHttpRequest, status) {　　　　
							if(status == 'timeout') {　　　　
								mui.toast("请求超时...");　　　　
							}　　
						}

					});
					mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 10)); //参数为true代表没有更多数据了。
				}, 1000);
			}

			/**
			 * 下拉刷新具体业务实现
			 */
			function pulldownRefresh() {
				setTimeout(function() {
					$.ajax({
						url: "/natergy-h5/order/refresh",
						contentType: "application/x-www-form-urlencoded:charset=UTF-8",
						type: "get",
						dataType: "json",
						data: "",
						success: function(orderList) {
							count=0;
							$("#orderList li").remove()
							$("#limit").val(orderList.limit);
							for(var i = 0; i < orderList.length; i++) { //循环数据
								$("#orderList").append("<li class='mui-table-view-cell mui-media' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
									orderList[i].orderDetails[0].orderNumber +
									"<p class='mui-ellipsis'>" + orderList[i].customerName + "</p>" +
									"<p class='mui-ellipsis'>" + orderList[i].orderTime + "</p>" +
									"<p class='mui-ellipsis'>" + orderList[i].state + "</p>" +
									"<input type='hidden' value ='" + JSON.stringify(orderList[i]) + "'/></div></a></li>");
							}

						}
					});
					// mui('body').on('tap', 'li', function() {
					// 	clickLi(this)
					// });
					mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
					mui.toast("已刷新...");
				}, 1000);
			}
		</script>
		<script>
			//查看订单

			function clickLi(obj) {

				var $orderInfo = $(obj).find("input:hidden");
				var json = $orderInfo.val();
				var data = eval("(" + json + ")")
				var orderDetails = eval(data.orderDetails);
				//console.log($orderInfo.val());
				layer
					.open({
						type: 2,
						title: '订单明细',
						shadeClose: true,
						shade: 0.8,
						area: ['90%', '90%'],
						data: obj,
						content: "<%=request.getContextPath()%>/jsp/order/orderDetailView.jsp", //iframe的url
						btn: ['返回'],
						success: function(layero, index) {
							var iframe = window['layui-layer-iframe' + index];
							//调用子页面的全局函数
							iframe.child(json);

						},

						cancel: function() {
							//右上角关闭回调
						}

					});
			};
		</script>

	</body>

</html>