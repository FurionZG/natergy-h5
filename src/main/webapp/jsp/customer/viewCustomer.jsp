<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/11/19
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />
</head>
<body>
<div class="mui-content-padded" style="margin: 15px;">
    <header class="mui-bar mui-bar-nav">
        <h1 class="mui-title">客户资料</h1>
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    </header>
    <div class="mui-content" >
        <form class="mui-input-group" style="margin: 0px 5px 0px 5px; border-radius: 10px;">
            <input type="hidden" id="id">
            <div class="mui-input-row">
                <label>客户名称</label> <input type="text" placeholder="" id="customerName" readonly="true">
            </div>
            <div class="mui-input-row">
                <label>客户编码</label> <input type="text" placeholder="" id="customerNo" readonly="true">
            </div>
            <div class="mui-input-row">
                <label>客户类型</label> <input type="text" placeholder="" id="type" readonly="true">
            </div>
            <div class="mui-input-row">
                <label>所在省</label> <input type="text" placeholder="" id="province" >
            </div>
            <div class="mui-input-row">
                <label>所在市</label> <input type="text" placeholder="" id="city" >
            </div>
            <div class="mui-input-row">
                <label>地址</label> <input type="text" placeholder="" id="receivingAddress" >
            </div>
            <div class="mui-input-row">
                <label>收货人</label> <input type="text" placeholder="" id="consignee" >
            </div>
            <div class="mui-input-row">
                <label>收票地址</label> <input type="text" placeholder="" id="invoiceAddress" >
            </div>
            <div class="mui-input-row">
                <label>收票人</label> <input type="text" placeholder="" id="collector" >
            </div>
            <div class="mui-input-row">
                <label>固话</label> <input type="text" placeholder="" id="tel" >
            </div>
            <div class="mui-input-row">
                <label>联系人姓名</label> <input type="text" placeholder="" id="contact" >
            </div>
            <div class="mui-input-row">
                <label>联系人手机</label> <input type="text" placeholder="" id="contactPhoneNum" >
            </div>
            <div class="mui-input-row">
                <label>联系人座机</label> <input type="text" placeholder="" id="contactTelNum" >
            </div>
            <div class="mui-input-row">
                <label>联系人职务</label> <input type="text" placeholder="" id="post" >
            </div>
        </form>
        <div class="mui-content-padded" style="margin-top: 20px;">
            <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">修改客户资料</button>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script>
    var json =eval("("+localStorage.getItem("customer")+")");
    $("#id").val(json.id);
    $("#customerName").val(json.customerName);
    $("#customerNo").val(json.customerNo);
    $("#type").val(json.type);
    $("#province").val(json.province);
    $("#city").val(json.city);
    $("#receivingAddress").val(json.receivingAddress);
    $("#consignee").val(json.consignee);
    $("#invoiceAddress").val(json.invoiceAddress);
    $("#collector").val(json.collector);
    $("#tel").val(json.tel);
    $("#contact").val(json.contact);
    $("#contactPhoneNum").val(json.contactPhoneNum);
    $("#contactTelNum").val(json.contactTelNum);
    $("#post").val(json.post);
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {
        mui.toast('正在更新客户资料...');
        $.ajax({
            url: "/natergy-h5/customer/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id": $("#id").val(),
                "province": $("#province").val(),
                "city": $("#city").val(),
                "receivingAddress": $("#receivingAddress").val(),
                "consignee": $("#consignee").val(),
                "invoiceAddress": $("#invoiceAddress").val(),
                "collector": $("#collector").val(),
                "tel": $("#tel").val(),
                "contact": $("#contact").val(),
                "contactPhoneNum": $("#contactPhoneNum").val(),
                "contactTelNum": $("#contactTelNum").val(),
                "post": $("#post").val(),
            }), success: function (data) {
                if (1 == data) {
                    mui.toast('保存成功');
                    window.location.href = "/natergy-h5/jsp/indexedList.jsp"
                } else {
                    mui.toast('客户资料修改失败，请稍后重试...');
                }
            }
        });
        mui(this).button('loading');
        setTimeout(function () {
            mui(this).button('reset');
            //mui.back();
        }.bind(this), 2000);
    });
</script>
</body>
</html>
