<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>修改销售订单</title>

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

    <div class="mui-content-padded" style="margin: 30px;">
        <header class="mui-bar mui-bar-nav">
            <h1 class="mui-title">修改销售订单</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>
    </div>
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <input type="hidden" id="id">
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text"  placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>收货人</label> <input type="text" placeholder="请输入收货人" id="consignee">
        </div>
        <div class="mui-input-row">
            <label>收货地址</label> <input type="text" placeholder="请输入收货地址" id="receivingAddress">
        </div>
        <div class="mui-input-row">
            <label>收票人</label> <input type="text" placeholder="请输入收票人" id="collector">
        </div>
        <div class="mui-input-row">
            <label>收票地址</label> <input type="text" placeholder="请输入收票地址" id="invoiceAddress">
        </div>
        <div class="mui-input-row">
            <label>注意事项</label> <input type="text" placeholder="请输入注意事项" id="attention">
        </div>
        <div class="mui-input-row">
            <label>发票事项</label> <input type="text" placeholder="请输入发票注意事项" id="invoiceAttention">
        </div>
    </form>
    <div class="mui-content">
        <h5 class="mui-content-padded">生产商</h5>
        <div class="mui-card">
            <form class="mui-input-group">
                <div class="mui-input-row mui-radio mui-left">
                    <label>绿能</label> <input name="radio1" type="radio" value="绿能" id="ln">
                </div>
                <div class="mui-input-row mui-radio mui-left">
                    <label>至简</label> <input name="radio1" type="radio" value="至简" id="zj">
                </div>

            </form>
        </div>
    </div>
    <div id="" align="center">
        <button id="addOrder" type="button" class="mui-btn mui-btn-success mui-icon mui-icon-plus mui-right" style="width: 65%;">添加</button>
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
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">修改订单</button>
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
        var json =eval("("+localStorage.getItem("order")+")");
        var orderDetails =json.orderDetails;
        console.log(json);
        $("#id").val(json.id);
        $("#customerName").val(json.customerName);
        $("#consignee").val(json.consignee);
        $("#receivingAddress").val(json.receivingAddress);
        $("#collector").val(json.collector);
        $("#invoiceAddress").val(json.invoiceAddress);
        $("#attention").val(json.attention);
        $("#invoiceAttention").val(json.invoiceAttention);
        if("绿能"==json.producer){
            $("#ln").attr("checked","checked");
        }
        if("至简"==json.producer){
            $("#zj").attr("checked","checked");
        }

        for(var i=0;i<orderDetails.length;i++){
            var res=orderDetails[i];
            $("#list")
                .append("<li class='mui-table-view-cell' ><div class='mui-table'><div class='mui-table-cell mui-col-xs-8'><h5 name='one'>" +
                    res.size + "|" + res.innerWrapper + "|" + res.outwrapper +
                    "</h5><p class='mui-h6' name='two'>" +
                    " |件数：" + res.count + " | 单价：" + res.price + " | 回扣：" + res.rebate +
                    "</p><p class='mui-h6 mui-ellipsis' name='tax'> |含税：" + res.tax + "</p></div><div class='mui-table-cell mui-col-xs-4 mui-text-right'><button type='button' class='mui-btn mui-btn-outlined' onclick='removeThis(this)' style='border-radius: 20px;'><span class='mui-icon mui-icon-trash'></span>删除</button></div></div></li>")
        }

    });
</script>
<!--添加订单 -->
<script>
    $("#addOrder")
        .on(
            "click",
            function() {
                layer
                    .open({
                        type: 2,
                        title: '订单明细',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['90%', '80%'],
                        content: '/natergy-h5/order/addDetailInit', //iframe的url
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
                                    " |件数：" + res.count + " | 单价：" + res.price + " | 回扣：" + res.rebate +
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
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function() {

        if(document.getElementById("list").getElementsByTagName("li").length == 0) {
            mui.toast('未添加货品...');
            return;
        }


        var customerName = $("#customerName").val();
        var consignee = $("#consignee").val();
        var receivingAddress = $("#receivingAddress").val();
        var collector = $("#collector").val();
        var invoiceAddress = $("#invoiceAddress").val();
        var attention = $("#attention").val();
        var invoiceAttention = $("#invoiceAttention").val();
        var producer = $("input:radio:checked").val();

        if(""==customerName || ""==consignee || ""==receivingAddress  || null==producer){
            mui.toast('请完善订单信息...');
            return;
        }
        mui.toast('正在修改订单...');
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
                outwrapper:x[2].trim(),
                count:x[3].split("：")[1].trim(),
                price:x[4].split("：")[1].trim(),
                rebate:x[5].split("：")[1].trim(),
                tax:tax
            })
        }
        console.log(list)
        $.ajax({
            url: "/natergy-h5/order/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id":$("#id").val(),
                "customerName": customerName,
                "consignee": consignee,
                "receivingAddress": receivingAddress,
                "collector": collector,
                "invoiceAddress": invoiceAddress,
                "attention": attention,
                "invoiceAttention": invoiceAttention,
                "producer": producer,
                "orderDetails": list
            }), success: function (data) {
                //alert("Data Loaded: " + data);
                if(data>0){
                    mui.toast('修改成功');
                    window.location.href="/natergy-h5/order/init"
                }else{
                    mui.toast('订单修改失败，请稍后重试...');
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