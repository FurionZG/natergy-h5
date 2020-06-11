<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-02-14
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>双向绑定</title>

    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css"> -

    <style>
        h5 {
            margin: 5px 7px;
        }
    </style>

</head>

<body>
<div class="mui-content">

    <div class="mui-content-padded" style="margin: 15px;">
        <header class="mui-bar mui-bar-nav">
            <h1 class="mui-title">双向绑定</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>

    </div>
    <form class="mui-input-group" style="margin: 50px 5px 0px 5px; border-radius: 10px;">
        <div class="mui-input-row">
            <label>姓名</label> <input type="text"  placeholder="" id="username">
        </div>
    </form>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">绑定</button>
    </div>
</div>
</body>

<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {
        var code = getUrlParam('code');
        $.ajax({
            url: "<%=request.getContextPath()%>/bindWxOpenId",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "code": code,
                "username":$("#username").val()
            }),
            success: function(data) {
                if(1==data){
                    mui.toast('绑定成功！');
                }else if(-1==data){
                    mui.toast('openid已绑定，修改请联系技术三部');
                }else if(-2==data){
                    mui.toast('该用户已经绑定，修改请联系技术三部');
                }else if(-3==data){
                    mui.toast('openid为空，请退出后重试');
                }
            }
        });

        if(null != uName&&null!=pwd) {
            $('#id_txtUserName').val(uName)
            $("#id_txtPwd").val(pwd);
        }
    });

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
