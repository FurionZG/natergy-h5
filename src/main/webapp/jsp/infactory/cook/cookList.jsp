<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-02-25
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
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

    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">

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
    <h1 class="mui-title">订餐信息</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>
</header>


<div class="mui-content">
    <!--下拉刷新容器-->
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: 50px;padding-top: 0px">
        <div class="mui-scroll" style="width: 95%">
            <!-- limit-->
            <input type="hidden" id="limit" value=""/>

            <!--数据列表-->
            <ul class="mui-table-view mui-table-view-chevron" id="cookList">

            </ul>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layui/layui.all.js"></script>

<script>
    $(document).ready(function loading() {

        var cookList = ${cookList};

        var json = eval(cookList);


        for (var i = 0; i < json.length; i++) { //循环数据
            let bre = "";
            let lun="";
            let sup="";
            let ns = "";
            if(json[i].breakfast=="是"){
                bre+="早餐";
            }
            if(json[i].lunch=="是"){
                lun+="午餐："+json[i].lMantou;
            }
            if(json[i].supper=="是"){
                sup+="晚餐："+json[i].sMantou;
            }
            if(json[i].nightSnack=="是"){
                ns+="夜餐："+json[i].nMantou;
            }
            var html = "<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                json[i].date;
            if(""!=bre){
                html+="<p class='mui-ellipsis'>" + bre + "</p>";
            }
            if(""!=lun){
                html+="<p class='mui-ellipsis'>" + lun + "</p>";
            }if(""!=sup){
                html+="<p class='mui-ellipsis'>" + sup + "</p>";
            }if(""!=ns){
                html+="<p class='mui-ellipsis'>" + ns + "</p>";
            }
            html+="<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>";
            $("#cookList").append(html);

        }

        mui('body').on('tap', '#cookList li', function () {
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
                url: "/natergy-h5/infactory/cook/listReload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#cookList li").length
                },
                success: function (data) {
                    var json = eval(data);
                    console.log(json);
                    if (json.length < 5) {
                        count = true;
                    }
                    for (var i = 0; i < json.length; i++) { //循环数据
                        let bre = "";
                        let lun="";
                        let sup="";
                        let ns = "";
                        if(json[i].breakfast=="是"){
                            bre+="早餐";
                        }
                        if(json[i].lunch=="是"){
                            lun+="午餐："+json[i].lMantou;
                        }
                        if(json[i].supper=="是"){
                            sup+="晚餐："+json[i].sMantou;
                        }
                        if(json[i].nightSnack=="是"){
                            ns+="夜餐："+json[i].nMantou;
                        }
                        var html = "<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].date;
                        if(""!=bre){
                            html+="<p class='mui-ellipsis'>" + bre + "</p>";
                        }
                        if(""!=lun){
                            html+="<p class='mui-ellipsis'>" + lun + "</p>";
                        }if(""!=sup){
                            html+="<p class='mui-ellipsis'>" + sup + "</p>";
                        }if(""!=ns){
                            html+="<p class='mui-ellipsis'>" + ns + "</p>";
                        }
                        html+="<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>";
                        $("#cookList").append(html);
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
                url: "/natergy-h5/infactory/cook/listRefresh",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: "",
                success: function (json) {
                    $("#cookList li").remove();
                    //var json = eval(data);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        let bre = "";
                        let lun="";
                        let sup="";
                        let ns = "";
                        if(json[i].breakfast=="是"){
                            bre+="早餐";
                        }
                        if(json[i].lunch=="是"){
                            lun+="午餐："+json[i].lMantou;
                        }
                        if(json[i].supper=="是"){
                            sup+="晚餐："+json[i].sMantou;
                        }
                        if(json[i].nightSnack=="是"){
                            ns+="夜餐："+json[i].nMantou;
                        }
                        var html = "<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].date;
                        if(""!=bre){
                            html+="<p class='mui-ellipsis'>" + bre + "</p>";
                        }
                        if(""!=lun){
                            html+="<p class='mui-ellipsis'>" + lun + "</p>";
                        }if(""!=sup){
                            html+="<p class='mui-ellipsis'>" + sup + "</p>";
                        }if(""!=ns){
                            html+="<p class='mui-ellipsis'>" + ns + "</p>";
                        }
                        html+="<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>";
                        $("#cookList").append(html);
                    }
                }
            });
            mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
            mui.toast("已刷新...");
        }, 1500);
    }
</script>
<script>
    function clickLi(obj) {
        var tmp = $(obj).find("input:hidden").val();
        localStorage.setItem("cook", tmp);
        window.location.href = "<%=request.getContextPath()%>/jsp/infactory/cook/cookEdit.jsp";
    }
</script>
</body>
</html>
