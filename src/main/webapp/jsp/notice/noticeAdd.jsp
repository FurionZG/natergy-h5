<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-03-07
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />
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
            <h1 class="mui-title">添加销售通知</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>
    </div>

    <div style="padding-top: 30px">
        <input type="hidden" id="id">
        <input type="checkbox" id="allClick" onclick="setAllNo()" />全选/全不选
        <br/>
        <div id = "checkDiv">

        </div>
        <br>
        <label>通知内容</label>
        <div class="mui-input-row" style="margin: 10px 5px;">
            <textarea id="noticeContent" rows="6" placeholder="请输入通知内容"></textarea>
        </div>

        <div class="mui-content-padded" style="margin-top: 20px;">
            <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">发布通知
            </button>
        </div>

    </div>
</div>
</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script>
    //全选函数
    function setAll() {
        var staff = document.getElementsByName("staffs");
        for (var i = 0; i < staff.length; i++) {
            staff[i].checked = true;
        }
    }

    //全不选函数
    function setNo() {
        var staff = document.getElementsByName("staffs");
        for (var i = 0; i < staff.length; i++) {
            staff[i].checked = false;
        }
    }
    function setAllNo(){
        var box = document.getElementById("allClick");
        var staff = document.getElementsByName("staffs");
        if(box.checked == false){
            for (var i = 0; i < staff.length; i++) {
                staff[i].checked = false;
            }
        }else{
            for (var i = 0; i < staff.length; i++) {
                staff[i].checked = true;
            }
        }
    }

</script>
<script>
    function funChange() {

        let staff = document.getElementsByName("staffs");
        var bool= true;
        for (var i = 0; i < staff.length; i++) {
            if(!staff[i].checked){
                bool=false;
            }
        }
        if(bool==true){
            document.getElementById("allClick").checked = true;
            console.log(1)
        }else{
            document.getElementById("allClick").checked = false;
            console.log(2)
        }
    }
    $(document).ready(function(){
        $.ajax({
            url: "/natergy-h5/notice/getSalesmanList",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            timeout: "1000",
            dataType: "json",
            success: function (data) {
                var json = eval(data);
                for (var i = 0; i < json.length; i++) { //循环数据
                    $("#checkDiv").append("<input type='checkbox' name='staffs' onchange='funChange()' /><span>"+json[i]+"</span>");
                }
            },
            complete: function (XMLHttpRequest, status) {
                if (status == 'timeout') {
                    mui.toast("请求超时...");
                }
            }
        });
    });


</script>


<script>
    mui.init({
        swipeBack: false
        //关于右滑关闭功能
    });
    var nativeWebview, imm, InputMethodManager;
    var initNativeObjects = function () {
        if (mui.os.android) {
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
    var showSoftInput = function () {
        if (mui.os.android) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        } else {
            nativeWebview.plusCallMethod({
                "setKeyboardDisplayRequiresUserAction": false
            });
        }
        setTimeout(function () {
            var inputElem = document.querySelector('input');
            inputElem.focus();
            inputElem.parentNode.classList.add('mui-active'); //第一个是search，加上激活样式
        }, 200);
    };
    mui.plusReady(function () {
        initNativeObjects();
        showSoftInput();
    });
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {
        var str="";
        $("input:checkbox[name='staffs']:checked").each(function(){
            str+="/"+$(this).next().text();
        })
        mui.toast('正在发布通知...');
        $.ajax({
            url: "/natergy-h5/notice/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "noticeContent":$("#noticeContent").val(),
                "str": str
            }), success: function (data) {
                console.log(1)
                //alert("Data Loaded: " + data);
                if ("error" != data) {
                    console.log(1)
                    mui.toast(data);
                    setTimeout('window.location.href = "/natergy-h5/jsp/main.jsp"',3000);
                } else {
                    mui.toast('通知保存失败，请稍后重试...');
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
