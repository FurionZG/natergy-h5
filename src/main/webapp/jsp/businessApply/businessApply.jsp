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

    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
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
    <h1 class="mui-title">出差申请</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>

</header>
<!--右上角弹出菜单-->
<div id="topPopover" class="mui-popover">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funAdd()" id="addBusinessApply"> <span
                            class="mui-icon iconfont icon-add"></span>添加
                    </a>
                </li>

                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funFilter()"> <span class="mui-icon iconfont icon-shaixuan"></span>筛选
                    </a>
                </li>

                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funRefresh()"> <span class="mui-icon iconfont icon-shuaxin"></span>刷新

                    </a>
                </li>
            </ul>
        </div>
    </div>

</div>
<div class="mui-content">
        <form id="form-country_v1" name="form-country_v1" style="margin-top: 10px;display: none; " >
            <div class="typeahead__container">
                <div class="typeahead__field" style="">
                    <div class="typeahead__query">
                        <input class="js-typeahead-country_v1" name="country_v1[query]" type="search"
                               placeholder="Search" autocomplete="off" id="searchedName">
                    </div>
                    <div class="typeahead__button">
                        <button type="button" onclick="getWorkingsByAjax()">
                            <i class="typeahead__search-icon"></i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    <!--下拉刷新容器-->

    <div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: 50px;padding-top: 0px">
        <div class="mui-scroll" style="width: 95%">
            <!-- limit-->
            <input type="hidden" id="limit" value=""/>
            <input type="hidden" id="search" value=""/>
            <!--数据列表-->
            <ul class="mui-table-view mui-table-view-chevron" id="businessApplyList">

            </ul>
        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

    function funAdd() {
        mui.toast('添加工作进程');
        window.location.href = "/natergy-h5/businessApply/addInit";
    }

    /** 筛选订单 **/
    function funFilter() {
        mui.toast('待开发...');
    }

    /** 刷新订单 **/
    function funRefresh() {
        mui.toast('待开发...');
    }
</script>

<script>
    $(document).ready(function loading() {

        var businessApplyList = ${businessApplyList};
       
        
        var json = eval(businessApplyList);


        for (var i = 0; i < json.length; i++) { //循环数据
            $("#businessApplyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                json[i].date +
                "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
        }

        mui('body').on('tap', '#businessApplyList li', function () {
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
                url: "/natergy-h5/businessApply/reload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#businessApplyList li").length,
                },
                success: function (data) {
                    var json = eval(data);
                    if (json.length < 5) {
                        count = true;
                    }
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#businessApplyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].date +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
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
        $("#search").val("");
        setTimeout(function () {
            $.ajax({
                url: "/natergy-h5/businessApply/refresh",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: "",
                success: function (json) {
                    $("#businessApplyList li").remove();
                    //var json = eval(data);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#businessApplyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].date +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
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
    function clickLi(obj) {
        localStorage.setItem("businessApply", $(obj).find("input:hidden").val());
        window.location.href = "/natergy-h5/businessApply/editInit"
    }
</script>

<script>
    function getWorkingsByAjax(){
        $.ajax({
            url: "/natergy-h5/working/getWorkingsByName",
            contentType: "application/json;charset=utf-8",
            type: "get",
            dataType: "json",
            data: {
                "name": $("#searchedName").val()
            },
            success: function (json) {
                $("#businessApplyList li").remove();
                //var json = eval(data);
                $("#search").val($("#searchedName").val());
                for (var i = 0; i < json.length; i++) { //循环数据
                    $("#businessApplyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                        json[i].date +
                        "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                        "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                }
            }
        });
    }
</script>
</body>

</html>