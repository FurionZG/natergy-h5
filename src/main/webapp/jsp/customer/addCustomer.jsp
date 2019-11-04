<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/9/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="https://cdn.bootcss.com/mui/3.7.1/css/mui.min.css" rel="stylesheet">
</head>
<body>

<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">添加客户资料</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
</header>

<div class="mui-content">
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text" placeholder="请输入客户名称" id="customerName" >
        </div>
        <div class="mui-input-row">
            <label>省</label> <input type="text" placeholder="请输入省" id="pro" >
        </div>
        <div class="mui-input-row">
            <label>市</label> <input type="text" placeholder="请输入市" id="city" >
        </div>
        <div class="mui-input-row">
            <label>客户地址</label> <input type="text" placeholder="请输入客户地址" id="address" >
        </div>
    </form>
    <div class="mui-content">
        <h5 class="mui-content-padded">生产商</h5>
        <div class="mui-card">
            <form class="mui-input-group">
                <div class="mui-input-row mui-radio mui-left">
                    <label>玻璃厂</label> <input name="radio1" type="radio" value="玻璃厂">
                </div>
                <div class="mui-input-row mui-radio mui-left">
                    <label>门窗厂</label> <input name="radio1" type="radio" value="门窗厂">
                </div>
                <div class="mui-input-row mui-radio mui-left">
                    <label>幕墙公司</label> <input name="radio1" type="radio" value="幕墙公司">
                </div>
                <div class="mui-input-row mui-radio mui-left">
                    <label>政府单位</label> <input name="radio1" type="radio" value="政府单位">
                </div>
                <div class="mui-input-row mui-radio mui-left">
                    <label>其他</label> <input name="radio1" type="radio" value="其他">
                </div>
                <div class="mui-content-padded" style="margin-top: 20px;">
                    <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">保存客户信息</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function() {


        var customerName = $("#customerName").val();
        var pro = $("#pro").val();
        var city = $("#city").val();
        var address = $("#address").val();
        var type = $("input:radio:checked").val();

        if(null==type){
            mui.toast('请选择客户类别');
            return;
        }
        mui.toast('正在保存客户...');

        $.ajax({
            url: "/natergy-h5/customer/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "customerName": customerName,
                "province": pro,
                "city": city,
                "address": address,
                "type": type
            }), success: function (data) {
                if(1==data){
                    mui.toast('保存成功...');
                    window.location.href=document.referrer;
                }else{
                    mui.toast('保存失败，请稍后重试...');
                }
            }
        });


        mui(this).button('loading');

        setTimeout(function() {
            mui(this).button('reset');


            //mui.back();

        }.bind(this), 1000);
    });
</script>
</body>
</html>
