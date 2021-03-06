<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-05-30
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>添加销售退货</title>

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
            <h1 class="mui-title">添加销售退货</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>
        <form id="form-country_v1" name="form-country_v1" style="margin-top: 30px;">
            <div class="typeahead__container">
                <div class="typeahead__field">
                    <div class="typeahead__query">
                        <input class="js-typeahead-country_v1" name="country_v1[query]" type="search" placeholder="Search" autocomplete="off" id="searchedName">
                    </div>
                    <div class="typeahead__button">
                        <button type="button" onclick="getAllInfoByAjax()">
                            <i class="typeahead__search-icon"></i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text"  placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>退货地址</label> <input type="text" placeholder="请输入退货地址" id="refundAddress">
        </div>
        <div class="mui-input-row">
            <label>退货联系人</label> <input type="text" placeholder="请输入退货联系人" id="refundContacts">
        </div>
        <div class="mui-input-row">
            <label>备注</label> <input type="text" placeholder="请输入备注" id="remarks1">
        </div>

    </form>

    <div id="" align="center" style="margin-top: 20px;">
        <button id="addRefund" type="button" class="mui-btn mui-btn-success mui-icon mui-icon-plus mui-right" style="width: 65%;">添加</button>
        <button type="button" class="mui-btn mui-btn-danger mui-btn-outlined" style="width: 30%;" onclick="$('#list').html('')">
            删除全部<span class="mui-icon mui-icon-trash"></span>
        </button>
    </div>
    <div style="margin-top: 10px; padding: 12px; box-shadow: 3px 10px 3px gray;">
        <label><p class="mui-h6">货品明细</p></label>
        <ul class="mui-table-view mui-table-view-striped mui-table-view-condensed" id="list">
        </ul>
        </label>
    </div>

    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">保存退货</button>
    </div>

</div>

</body>

<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layui/layui.all.js"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>

<!--初始化页面，将公司名称传入搜索框-->
<script>
    $(document).ready(function loading() {
        $.ajax({
            url: "<%=request.getContextPath()%>/order/addInit",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            dataType: "json",

            success: function(allName) {
                $.typeahead({
                    input: '.js-typeahead-country_v1',
                    order: "desc",
                    source: {
                        data: allName
                    },
                    callback: {
                        onInit: function(node) {
                            console.log('Typeahead Initiated on ' + node.selector);
                        }
                    }
                });
            }
        });
    });
</script>
<!--添加退货 -->
<script>

    $("#addRefund")
        .on(
            "click",
            function() {
                layer
                    .open({
                        type: 2,
                        title: '退货明细',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['100%', '100%'],
                        content: '/natergy-h5/refund/addDetailInit', //iframe的url
                        btn: ['确定', '关闭'],
                        yes: function(index) {
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            var tax = "";
                            if("是"==res.tax) {
                                tax = "是"
                            } else {
                                tax = "否"
                            }
                            $("#list")
                                .append("<li class='mui-table-view-cell' ><div class='mui-table'><div class='mui-table-cell mui-col-xs-8'><h5 name='one'>" +
                                    res.size + "|" + res.innerWrapper + "|" + res.outWrapper +
                                    "</h5><p class='mui-h6' name='two'>" +
                                    " |件数：" + res.count +
                                    " |退货数量（Kg）：" + res.weight + " | 单价：" + res.price  +
                                    "</p><p class='mui-h6 mui-ellipsis' name='tax'> |含税：" + tax + "</p></div><div class='mui-table-cell mui-col-xs-4 mui-text-right'><button type='button' class='mui-btn mui-btn-outlined' onclick='removeThis(this)' style='border-radius: 20px;'><span class='mui-icon mui-icon-trash'></span>删除</button></div></div></li>")

                            console.log(res);
                            layer.close(index);
                        },
                        cancel: function() {
                            //右上角关闭回调
                        }

                    });
            });

    function removeThis(obj) {

        $(obj).parent().parent().remove();

    }
</script>
<script>
    mui.init({
        swipeBack: false
        //关于右滑关闭功能
    });
    //语音识别完成事件
    //			document.getElementById("search").addEventListener('recognized', function(e) {
    //				console.log(e.detail.value);
    //			});

    var nativeWebview, imm, InputMethodManager;
    var initNativeObjects = function() {
        if(mui.os.android) {
            var main = plus.android.runtimeMainActivity();
            var Context = plus.android.importClass("android.content.Context");
            InputMethodManager = plus.android
                .importClass("android.view.inputmethod.InputMethodManager");
            imm = main.getSystemService(Context.INPUT_METHOD_SERVICE);
        } else {
            nativeWebview = plus.webview.currentWebview()
                .nativeInstanceObject();
        }
    };
    var showSoftInput = function() {
        if(mui.os.android) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        } else {
            nativeWebview.plusCallMethod({
                "setKeyboardDisplayRequiresUserAction": false
            });
        }
        setTimeout(function() {
            var inputElem = document.querySelector('input');
            inputElem.focus();
            inputElem.parentNode.classList.add('mui-active'); //第一个是search，加上激活样式
        }, 200);
    };
    mui.plusReady(function() {
        initNativeObjects();
        showSoftInput();
    });
</script>

<script>
    function getAllInfoByAjax() {

        $.ajax({
            url: "<%=request.getContextPath()%>/customer/getCustomerInfo",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            dataType: "json",
            data: {
                "name": $("#searchedName").val()
            },
            success: function(data) {
                //var json = eval("(" + data + ")");
                $("#customerName").val(data.customerName);
                $("#refundContacts").val(data.consignee);
                $("#refundAddress").val(data.receivingAddress);

            }
        });

        <%--$.ajax({--%>
        <%--	url: "<%=request.getContextPath()%>/customer/getAttention",--%>
        <%--	contentType: "application/x-www-form-urlencoded:charset=UTF-8",--%>
        <%--	type: "get",--%>
        <%--	dataType: "json",--%>
        <%--	data: {--%>
        <%--		"name": $("#searchedName").val()--%>
        <%--	},--%>
        <%--	success: function(data) {--%>
        <%--		$("#attention").val(data.attention);--%>
        <%--		$("#invoiceAttention").val(data.invoiceAttention);--%>
        <%--	}--%>
        <%--});--%>
    }
</script>
<!--
    作者：240232995@qq.com
    时间：2019-08-24
    描述：保存退货
-->
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function() {

        if(document.getElementById("list").getElementsByTagName("li").length == 0) {
            mui.toast('未添加退货明细...');
            return;
        }


        var customerName = $("#customerName").val();
        var refundAddress = $("#refundAddress").val();
        var refundContacts = $("#refundContacts").val();
        var remarks1 = $("#remarks1").val();


        if(""==customerName || ""==refundAddress || ""==refundContacts ){
            mui.toast('请完善退货信息...');
            return;
        }
        mui.toast('正在保存退货...');
        var a = $("#list li").length;
        var list = [];
        for(i = 0; i < a; i++) {
            var x = $("#list li").eq(i).text().split("\|");
            var tax =""
            if(-1!=x[6].indexOf("是")){
                tax="是"
            }else{
                tax="否"
            }
            list.push({
                size:x[0].trim(),
                innerWrapper:x[1].trim(),
                outWrapper:x[2].trim(),
                count:x[3].split("：")[1].trim(),
                totalWeight:x[4].split("：")[1].trim(),
                price:x[5].split("：")[1].trim(),
                tax:tax
            })
        }
        console.log(list)
        $.ajax({
            url: "/natergy-h5/refund/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "customerName": customerName,
                "refundAddress": refundAddress,
                "refundContacts": refundContacts,
                "remarks1": remarks1,
                "refundDetails": list
            }), success: function (data) {
                //alert("Data Loaded: " + data);
                if(1==data){
                    mui.toast('保存成功');
                    window.location.href="/natergy-h5/refund/init"
                }else{
                    mui.toast('退货保存失败，请稍后重试...');
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

<script>
    function removeThis(obj) {

        $(obj).parent().parent().parent().remove();

    }
</script>
</body>

</html>
