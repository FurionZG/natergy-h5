<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<%--
		<link href="https://cdn.bootcss.com/mui/3.7.1/css/mui.min.css" rel="stylesheet">
--%>
		<link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
		<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

		<title></title>
	</head>

	<body style="height: 100px;">

		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">请 登 录</h1>
		</header>

		<div class="mui-content" style="margin-top: 10px;">

			<form id='id_frmLogin' class="mui-input-group">
				<div class="mui-input-row">
					<label>用 户</label> <input id='id_txtUserName' type="text" value="" class="mui-input-clear mui-input" placeholder="请输入姓名" />
				</div>
				<div class="mui-input-row">
					<label>密 码</label> <input id='id_txtPwd' type="password" value="" class="mui-input-clear mui-input" placeholder="请输入密码" />
				</div>
			</form>

			<div class="mui-content-padded">
				<button id='id_btnLogin' type="submit" class="mui-btn mui-btn-success" style="width: 100%;">登 录</button>
			</div>

		</div>

		<%--<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>--%>
		<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript">
			$(function() {
				var code = getUrlParam('code');
				$.ajax({
					url: "<%=request.getContextPath()%>/wxOpenId",
					contentType: "application/json;charset=utf-8",
					type: "post",
					dataType: "json",
					data: JSON.stringify({
						"code": code
					}),
					success: function(data) {
						console.log(data);
						if(0==data){
                            mui.toast('微信登录失败，请手动输入账号密码');
                        }else{
                            mui.toast('欢迎'+data);
                            window.location.href = "jsp/main.jsp";
                        }

					}
				});
				var uName = window.localStorage.getItem('userName');
				var pwd = window.localStorage.getItem('password');
				if(null != uName&&null!=pwd) {
					$('#id_txtUserName').val(uName) 
					$("#id_txtPwd").val(pwd);
				}
			});
			
		</script>

		<script type="text/javascript">
			/** 登录 **/
			var btnLogin = document.getElementById("id_btnLogin");
			btnLogin.addEventListener("tap", function() {
				mui.toast('正在登录...');
				mui(this).button('loading');

				setTimeout(function() {
					mui(this).button('reset');
					$.ajax({
						url: "<%=request.getContextPath()%>/login",
						contentType: "application/json;charset=utf-8",
						type: "post",
						dataType: "json",
						data: JSON.stringify({
							"uname": $("#id_txtUserName").val(),
							"pwd": $("#id_txtPwd").val()
						}),
						success: function(data) {
							if(data == 1) {
								window.localStorage.clear()
								window.localStorage.setItem('userName', $("#id_txtUserName").val())
								window.localStorage.setItem('password', $("#id_txtPwd").val())
								window.location.href = "jsp/main.jsp";
							} else {
								mui.toast('用户名或密码错误');
								$("#id_txtUserName").val("");
								$("#id_txtPwd").val("");
							}

						}
					});
				}.bind(this), 300);
			});

			/** 禁止微信下拉动作 **/
			document.getElementsByTagName("body")[0].style.height = document.body.scrollHeight + "px";
			//document.getElementsByTagName("body")[0].style.width  = document.body.scrollWidth+"px";

			document.body.addEventListener('touchmove', function(e) {
				e.preventDefault(); //阻止默认的处理方式(阻止下拉滑动的效果)				
			}, {
				passive: false
			}); //passive 参数不能省略，用来兼容ios和android
		</script>
		<script>
			function getUrlParam (name) {
				var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
				var url = window.location.href.split('#')[0]
				var search = url.split('?')[1]
				if (search) {
					var r = search.substr(0).match(reg)
					if (r !== null) return unescape(r[2])
					return null
				} else {
					return null
				}
			}
		</script>

	</body>

</html>