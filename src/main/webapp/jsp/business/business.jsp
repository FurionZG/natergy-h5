<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>

    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link href="https://cdn.bootcss.com/mui/3.7.1/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>

    <title></title>

    <style>
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

<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">出差计划</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">开始出差</button>

</header>


<!--下拉刷新容器-->
<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top:50px">
    <div class="mui-scroll" style="width: 95%">
        <!-- limit-->
        <input type="hidden" id="limit" value=""/>

        <!--数据列表-->
        <ul class="mui-table-view mui-table-view-chevron" id="businessList">

        </ul>
    </div>

        <input type="hidden" id ='flag'>

</div>

<!--<div style="margin-top: 30px;">
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view mui-table-view-chevron" id="orderList">

            </ul>
        </div>
    </div>

</div>-->
<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layui/layui.all.js"></script>


<script>
    $(document).ready(function loading() {
        var businessList=${businessList};
        var count = ${onBusinessCount};
        localStorage.setItem("appId",'${appId}');
        localStorage.setItem("timestamp",parseInt("${timestamp}", 10));
        localStorage.setItem("nonceStr",'${noncestr}');
        localStorage.setItem("signature",'${signature}');
        $("#flag").val(count);
        if(0==count){
            $("#id_btnSave").attr('class','mui-btn mui-btn-success');
            $("#id_btnSave").text('开始出差');
        }else{
            $("#id_btnSave").attr('class','mui-btn mui-btn-danger');
            $("#id_btnSave").text('结束出差');
        }
        var json = eval(businessList);

        $("#limit").val(10);
        for (var i = 0; i < json.length; i++) { //循环数据
            $("#businessList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                json[i].businessNo +
                "<p class='mui-ellipsis'>" + json[i].startDate + "</p>" +
                "<p class='mui-ellipsis'>" + json[i].status + "</p>"+
            "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
        }
        mui('body').on('tap', '#businessList li', function () {
            clickLi(this)
        });
    });
</script>
<script>
    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            down: {
                style: 'circle',
                callback: pulldownRefresh
            },
            up: {
                auto: false,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });

    var count = false;

    function pullupRefresh() {
        setTimeout(function () {

            $.ajax({
                url: "/natergy-h5/business/reload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#businessList li").length
                },
                success: function (data) {
                    var json = eval(data);
                    if(json.length<5){
                        count=true;
                    }
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#businessList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].businessNo +
                            "<p class='mui-ellipsis'>" + json[i].startDate + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>"+
                            "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                    }
                    mui.toast("已为您加加载" + json.length + "条...");
                },
                complete: function (XMLHttpRequest, status) {
                    if (status == 'timeout') {
                        mui.toast("请求超时...");
                    }
                }

            });

            mui('#pullrefresh').pullRefresh().endPullupToRefresh(count); //参数为true代表没有更多数据了。
        }, 500);
    }

    /**
     * 下拉刷新具体业务实现
     */
    function pulldownRefresh() {
        setTimeout(function () {
            $.ajax({
                url: "/natergy-h5/business/refresh",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: "",
                success: function (json) {
                    $("#businessList li").remove();
                    //var json = eval(data);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#businessList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].businessNo +
                            "<p class='mui-ellipsis'>" + json[i].startDate + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>"+
                            "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                    }
                }
            });
            mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
            mui.toast("已刷新...");
        }, 1500);
    }
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function() {

        var flag=$("#flag").val();
        if(1==flag){
            mui.toast('正在结束出差...');
            $.ajax({
                url: "/natergy-h5/business/end",
                contentType: "application/json;charset=utf-8",
                type: "post",
                async: false,
                dataType: "json",
                success: function (data) {
                    if(1==data){
                        mui.toast('保存成功');
                        window.location.href="/natergy-h5/business/init"
                    }else{
                        mui.toast('保存失败，请稍后重试...');
                    }
                }
            });
        }else {
            mui.toast('正在开始出差...');
            $.ajax({
                url: "/natergy-h5/business/begin",
                contentType: "application/json;charset=utf-8",
                type: "post",
                async: false,
                dataType: "json",
                success: function (data) {
                    if (1 == data) {
                        mui.toast('保存成功');
                        window.location.reload();
                        window.location.href="/natergy-h5/business/init"
                    } else {
                        mui.toast('保存失败，请稍后重试...');
                    }
                }
            });
        }



        setTimeout(function() {
            mui(this).button('reset');

            //mui.back();

        }.bind(this), 1000);
    });
</script>
<script>
    function clickLi(obj){
        localStorage.setItem("business",$(obj).find("input:hidden").val());
        window.location.href="<%=request.getContextPath()%>/jsp/business/businessEdit.jsp"
    }

</script>
</body>

</html>