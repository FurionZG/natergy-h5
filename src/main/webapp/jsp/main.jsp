<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my001/iconfont.css" />
		<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

		<title></title>
	</head>

	<body>

		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">Natergy Integration</h1>
		</header>

		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" href="#tabbar1">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item" href="#tabbar2">
				<span class="mui-icon mui-icon-email"><!--<span class="mui-badge">43</span>--></span>
				<span class="mui-tab-label">消息</span>
			</a>
			<a class="mui-tab-item" href="#tabbar3">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">客户</span>
			</a>
			<a class="mui-tab-item" href="#tabbar4">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">设置</span>
			</a>
		</nav>

		<div class="mui-content">
			<div id="tabbar1" class="mui-control-content mui-active">
				<ul class="mui-table-view mui-grid-view mui-grid-9">
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funOrder()">
							<span class="mui-icon iconfont icon-dingdan"></span>
							<div class="mui-media-body">订单</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funRefund()">
							<span class="mui-icon iconfont icon-refund"></span>
							<div class="mui-media-body">退款</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funVisit()">
							<span class="mui-icon iconfont icon-baifang"></span>
							<div class="mui-media-body">拜访</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funWork()">
							<span class="mui-icon iconfont icon-gongzuo1"></span>
							<div class="mui-media-body">工作</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funReport()">
							<span class="mui-icon iconfont icon-tongjis"></span>
							<div class="mui-media-body">统计</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="funFollowUp()">
							<span class="mui-icon iconfont icon-qiyegenjinguanli"></span>
							<div class="mui-media-body">地产跟进</div>
						</a>
					</li>
					<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
						<a href="javascript:;" onclick="test()">
							<span class="mui-icon iconfont icon-dd_active"></span>
							<div class="mui-media-body">测试</div>
						</a>
					</li>
				</ul>
			</div>

			<div id="tabbar2" class="mui-control-content">
				<ul class="mui-table-view mui-table-view-chevron">
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">部门调整</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">安环办检查通知</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">人事任命调整</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">人事任命调整</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">年假通知</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">培训通知</a>
					</li>
					<li class="mui-table-view-cell">
						<a href="" class="mui-navigate-right">会议通知</a>
					</li>
				</ul>

				<!--<div style="margin-right: 10px;">
					<button type="button" class="mui-btn mui-btn-link">更多消息...<span class="mui-badge">43</span></button>
				</div>-->
			</div>

			<div id="tabbar3" class="mui-control-content">
				第三页
			</div>

			<div id="tabbar4" class="mui-control-content">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						<a class="mui-navigate-right">
							设置A
						</a>
					</li>
					<li class="mui-table-view-cell">
						<a class="mui-navigate-right">
							设置B
						</a>
					</li>
					<li class="mui-table-view-cell">
						<a class="mui-navigate-right">
							设置C
						</a>
					</li>
				</ul>
				<ul class="mui-table-view" style="margin-top: 25px;">
					<li class="mui-table-view-cell">
						<a class="mui-navigate-right">
							关于NI
						</a>
					</li>
				</ul>
				<ul class="mui-table-view" style="margin-top: 25px;">
					<li class="mui-table-view-cell">
						<a href="javascript:;" onclick="funExit()" style="text-align: center;color: #FF3B30;">
							退出登录
						</a>
					</li>
				</ul>
			</div>
		</div>

		<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
		<script type="text/javascript">
			/** 订单 **/
			function funOrder() {
				window.location.href = "/natergy-h5/order/init";
			}
			function funRefund() {
				window.location.href = "/natergy-h5/refund/init";
			}

			/** 拜访 **/
			function funVisit() {
				mui.toast('拜访客户-待开放...');
			}

			/** 工作 **/
			function funWork() {
				mui.toast('工作日报-待开放...');
			}

			/** 统计报表 **/
			function funReport() {
				mui.toast('统计报表-待开放...');
			}
			/** 地产跟进 **/
			function funFollowUp() {
				window.location.href = "/natergy-h5/followUp/init";
			}


			function test() {
				window.location.href = "<%=request.getContextPath()%>/xx";
			}
			/** 退出系统，返回登录界面 **/
			function funExit() {
				window.location.href = "login.html";
			}

			/** 禁止微信下拉动作 **/
			document.getElementsByTagName("body")[0].style.height = document.body.scrollHeight + "px";
			//document.getElementsByTagName("body")[0].style.width  = document.body.scrollWidth+"px";

			document.body.addEventListener('touchmove', function(e) {
				e.preventDefault(); //阻止默认的处理方式(阻止下拉滑动的效果)				
			}, {
				passive: false
			}); //passive 参数不能省略，用来兼容ios和android
		</script>

	</body>

</html>