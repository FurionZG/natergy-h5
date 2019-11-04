<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="https://cdn.bootcss.com/mui/3.7.1/css/mui.min.css" rel="stylesheet">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />
		<link href="<%=request.getContextPath()%>/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/mui.poppicker.css" rel="stylesheet" />
		<!--<link rel="stylesheet" type="text/css" href="../css/mui.picker.min.css" />-->

		<style>
			#showUserPicker {
				font-size: 16px;
				padding: 8px;
				margin: 3px;
			}
			
			h5.mui-content-padded {
				margin-left: 0px;
				!important margin-top: 20px !important;
			}
			
			h5.mui-content-padded:first-child {
				margin-top: 12px !important;
			}
			
			.ui-alert {
				text-align: center;
				padding: 20px 10px;
				font-size: 16px;
			}
		</style>

	</head>

	<body>
		<div class="mui-content-padded" style="margin: 15px;">
			<button id='showUserPicker' class="mui-btn mui-btn-block" type='button'>产品规格</button>
			<!--<div id='userResult' class="ui-alert"></div>-->
		</div>
		<div class="mui-card">
			<div class="mui-card" style="margin: 10px;width: 25%;float: left">
				<div class="mui-card-header">规格</div>
				<div class="mui-card-content">
					<div class="mui-card-content-inner" id="size">

					</div>
				</div>
			</div>

			<div class="mui-card" style="margin: 9px;width: 30%; float: left;">
				<div class="mui-card-header">外包装</div>
				<div class="mui-card-content">
					<div class="mui-card-content-inner" id="outWrapper">

					</div>
				</div>
			</div>
			<div class="mui-card" style="margin: 8px;width: 27%;float: left;">
				<div class="mui-card-header">内包装</div>
				<div class="mui-card-content">
					<div class="mui-card-content-inner" id="innerWrapper">

					</div>
				</div>
			</div>
		</div>
		<div id="num-content" class="mui-content">
			<div id="num-card" class="mui-card">
				<div class="mui-input-row">
					<label>数量</label>
					<input id="count" type="text" placeholder="请输入数量" class="num">
				</div>

				<div class="mui-input-row">
					<label>价格</label>
					<input id="price" type="text" placeholder="请输入价格" class="num">
				</div>

				<div class="mui-input-row">
					<label>回扣</label>
					<input id="rebate" type="text" placeholder="" class="num" value="0" >
				</div>
			</div>

			<div class="mui-content">
				<h5 class="mui-content-padded">是否含税</h5>
				<div class="mui-card">
					<form class="mui-input-group">
						<div class="mui-input-row mui-radio mui-left">
							<label>含税</label> <input name="radio1" type="radio" value="是" checked>
						</div>
						<div class="mui-input-row mui-radio mui-left">
							<label>不含税</label> <input name="radio1" type="radio" value="否">
						</div>

					</form>
				</div>
			</div>

		</div>
	</body>
	<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
	<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
	
	<!--
    	作者：240232995@qq.com
    	时间：2019-08-24
    	描述：级联
    -->
	<script>
		(function($, doc) {
			$.init();
			$.ready(function() {
				/**
				 * 获取对象属性的值
				 * 主要用于过滤三级联动中，可能出现的最低级的数据不存在的情况，实际开发中需要注意这一点；
				 * @param {Object} obj 对象
				 * @param {String} param 属性名
				 */
				var _getParam = function(obj, param) {
					return obj[param] || '';
				};

				var cityPicker3 = new $.PopPicker({
					layer: 3
				});
				var data = ${result	};
				cityPicker3.setData(data);
				var showCityPickerButton = doc.getElementById('showUserPicker');
				//				var cityResult3 = doc.getElementById('userResult');
				showCityPickerButton.addEventListener('tap', function(event) {
					cityPicker3.show(function(items) {
						//						cityResult3.innerText = "你选择的城市是:" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
						doc.getElementById('size').innerText = _getParam(items[0], 'text');
						doc.getElementById('outWrapper').innerText = _getParam(items[1], 'text')
						doc.getElementById('innerWrapper').innerText = _getParam(items[2], 'text')
						//返回 false 可以阻止选择框的关闭
						//return false;
					});
				}, false);

			});
		})(mui, document);
	</script>
	<!--
    	作者：240232995@qq.com
    	时间：2019-08-24
    	描述：子页面的回调函数
    -->
	<script type="text/javascript">
		var callbackdata = function() {
			var data = {
				size: $('#size').text(),
				outWrapper: $('#outWrapper').text(),
				innerWrapper: $('#innerWrapper').text(),
				count: $('#count').val(),
				price: $('#price').val(),
				rebate: $('#rebate').val(),
				tax: $("input:radio:checked").val()

			};
			if(""!=$('#size').text() && ""!=$('#outWrapper').text() && ""!=$('#count').val()&& ""!= $('#price').val() && ""!= $('#rebate').val()){
				return data;
			}else{
				mui.toast('请完善订单明细...');
			}
			
		}

		/*function check(obj) {
			var son = $(obj).parent();
			var id = son.next('input').val();
			var title = son.parent().next('td').find('a').html();
			$('#couponsID').val(id);
			$('#couponsName').val(title);
		}*/
	</script>
	<!--
    	作者：240232995@qq.com
    	时间：2019-08-24
    	描述：checkbox文字变化
   
	<script>
		mui.init({
			swipeBack: true //启用右滑关闭功能
		});
		mui('.mui-input-row').on('change', 'input', function() {
			var value = this.checked ? "含税" : "不含税";
			this.previousElementSibling.innerText = "是否含税：" + value;
		});
	</script> -->
	<!--
    	作者：240232995@qq.com
    	时间：2019-08-24
    	描述：数字检验
    -->
	<script>
		$(".num").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9.]/g, ''));
		}).bind("paste", function() { //CTR+V事件处理  
			$(this).val($(this).val().replace(/[^0-9.]/g, ''));
		}).css("ime-mode", "disabled"); //CSS设置输入法不可用
	</script>

</html>