<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/11/28
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">
    <link href="<%=request.getContextPath()%>/css/mui.picker.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/mui.poppicker.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>

    <style>
        h5 {
            margin: 5px 7px;
        }

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

        .mui-content > .mui-table-view:first-child {
            margin-top: -1px;
        }

        selector {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="mui-content">
    <div class="mui-content-padded" style="margin: 15px;">
        <header class="mui-bar mui-bar-nav">
            <h1 class="mui-title">查看申请单</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>
    </div>
    <form class="mui-input-group" style="margin: 50px 5px 0px 5px; border-radius: 10px;">
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text" placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>客户编号</label> <input type="text" placeholder="" id="customerNo" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>状态</label> <input type="text" placeholder="" id="status" readonly="true">
        </div>
        <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
            <button id='typePicker' class="mui-btn mui-btn-block" type='button'
                    style="width: 100%; text-align: center;">申请类型
            </button>
        </div>
        <div id="changed">
        </div>
    </form>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <textarea id="content" rows="5" placeholder="请输入申请内容" readonly="readonly"></textarea>
    </div>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <h5>销售主管审批意见</h5>
        <textarea id="approvalComments" rows="5" placeholder="销售主管审批意见" readonly="readonly"></textarea>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script>
    function dynamicDiv(apply) {
        $("#changed").empty();
        if ("退款" == apply.type) {
            html = '<div class="mui-input-row"><label>退款金额</label> <input type="text" placeholder="" id="refundAmount" readonly="true" value="' +apply.refundAmount+ '"></div>' +
                '<div class="mui-input-row"><label>退款账户</label> <input type="text" placeholder="" id="refundAccount" readonly="true" value="' +apply.refundAccount+ '"></div>'
            $("#changed").append(html);
        }
        if ("调账" == apply.type) {
            html = '<div class="mui-input-row"><label>调账金额</label> <input type="text" placeholder="" id="adjustmentAmount" readonly="true" value="' +apply.adjustmentAmount+ '"></div>' +
                '<div class="mui-input-row"><label>调后余额</label> <input type="text" placeholder="" id="balance" readonly="true" value="' +apply.balance+ '"></div>'
            $("#changed").append(html);
        }
        if ("客情" == apply.type) {
            html = '<div class="mui-input-row"><label>费用金额</label> <input type="text" placeholder="" id="costs" readonly="true" value="' +apply.costs+ '"></div>'
            $("#changed").append(html);
        }
        if ("调价" == apply.type) {
            html = '<div class="mui-input-row"><label>原单价</label> <input type="text" placeholder="" id="originalPrice" readonly="true" value="' +apply.originalPrice+ '"></div>' +
                '<div class="mui-input-row"><label>现单价</label> <input type="text" placeholder="" id="presentPrice" readonly="true" value="' +apply.presentPrice+ '"></div>'
            $("#changed").append(html);
        }
        if ("赠品" == apply.type) {
            html = '<div class="mui-input-row"><label>赠品赠送方式</label> <input type="text" placeholder="" id="givingType" readonly="true" value="' +apply.givingType+ '"></div>' +
                '<div class="mui-input-row"><label>折合单价</label> <input type="text" placeholder="" id="conversionPrice" readonly="true" value="' +apply.conversionPrice+ '"></div>'
            $("#changed").append(html);
        }
    }
    $(document).ready(function loading() {
        var apply=localStorage.getItem("apply")
        apply = eval("("+apply+")");
        $("#customerName").val(apply.customerName);
        $("#customerNo").val(apply.customerNo);
        $("#typePicker").text(apply.type);
        $("#content").val(apply.content);
        $("#status").val(apply.status);
        $("#approvalComments").val(apply.approvalComments);
        dynamicDiv(apply);
    })
</script>
</body>
</html>
