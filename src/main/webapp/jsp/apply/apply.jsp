<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/11/27
  Time: 14:51
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
    <h1 class="mui-title">申请单</h1>
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
                    <a href="javascript:;" onclick="funAdd()" id="addApply"> <span
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
    <form id="form-country_v1" name="form-country_v1" style="margin-top: 10px;">
        <div class="typeahead__container">
            <div class="typeahead__field" style="">
                <div class="typeahead__query">
                    <input class="js-typeahead-country_v1" name="country_v1[query]" type="search"
                           placeholder="Search" autocomplete="off" id="searchedName">
                </div>
                <div class="typeahead__button">
                    <button type="button" onclick="getApplyByAjax()">
                        <i class="typeahead__search-icon"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>

    <!--下拉刷新容器-->

    <div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: 100px;padding-top: 0px">

        <div class="mui-scroll" style="width: 95%">
            <!-- limit-->
            <input type="hidden" id="limit" value=""/>

            <!--数据列表-->
            <ul class="mui-table-view mui-table-view-chevron" id="applyList">

            </ul>
        </div>
    </div>

</div>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layui/layui.all.js"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script type="text/javascript">
    function funAdd() {
        mui.toast('添加申请单');
        window.location.href = "/natergy-h5/apply/addInit";
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

        var applyListByUser = ${applyList};

        var json = eval(applyListByUser);
        $("#limit").val(10);
        for (var i = 0; i < json.length; i++) { //循环数据
            $("#applyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                json[i].customerName +
                "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
        }

        mui('body').on('tap', '#applyList li', function () {
            clickLi(this)
        });
        mui('body').on('tap', '#addApply', function () {
            funAdd()
        });
    });
</script>
<script>
    var allName = ${ApplysNameSet}
        $.typeahead({
            input: '.js-typeahead-country_v1',
            order: "desc",
            source: {
                data: allName
            },
            callback: {
                onInit: function (node) {
                    console.log('Typeahead Initiated on ' + node.selector);
                }
            }
        });
</script>
<script>
    function getApplyByAjax() {
        $.ajax({
            url: "/natergy-h5/apply/getApplyByAjax",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "customerName": $("#searchedName").val(),
            },
            dataType: "json",
            success: function (data) {
                var json = eval(data);
                $("#applyList li").remove();
                for (var i = 0; i < json.length; i++) { //循环数据
                    $("#applyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                        json[i].customerName +
                        "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                        "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                        "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                }
            }
        });

    }
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
                url: "/natergy-h5/apply/reload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#applyList li").length
                },
                success: function (data) {
                    var json = eval(data);
                    if (json.length < 5) {
                        count = true;
                    }
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#applyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].customerName +
                            "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
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
                url: "/natergy-h5/apply/refresh",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: "",
                success: function (json) {
                    $("#applyList li").remove();
                    //var json = eval(data);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#applyList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].customerName +
                            "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].status + "</p>" +
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
        var tmp = $(obj).find("input:hidden").val();
        localStorage.setItem("apply", tmp);
        var salesExecutive="${salesExecutive}";
        var user="${user}";
        if("新"!= eval("(" + tmp + ")").status){
            window.location.href = "<%=request.getContextPath()%>/jsp/apply/applyView.jsp";
            return ;
        }
        if(salesExecutive.indexOf(user)!=-1){
            window.location.href = "<%=request.getContextPath()%>/jsp/apply/applyApproval.jsp";
            return ;
        }
        if ("新" == eval("(" + tmp + ")").status&&""==eval("(" + tmp + ")").approvalComments) {
            window.location.href = "<%=request.getContextPath()%>/jsp/apply/applyEdit.jsp";
            return ;
        }
        window.location.href = "<%=request.getContextPath()%>/jsp/apply/applyView.jsp";
    }
</script>
</body>
</html>