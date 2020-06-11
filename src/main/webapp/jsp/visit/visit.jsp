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
    <h1 class="mui-title">销售拜访</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>
</header>
<footer id="footer" class="mui-bar mui-bar-footer" style="display: none">
    <a href="#bottomPopover" class="mui-btn mui-btn-link mui-pull-right">业务经理</a>
</footer>
<div id="bottomPopover" class="mui-popover mui-popover-bottom" style="height: 300px">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view" id = "salesmanList">
            </ul>
        </div>
    </div>
</div>
<!--右上角弹出菜单-->
<div id="topPopover" class="mui-popover">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funAdd()" id="addVisit"> <span
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
                    <button type="button" onclick="getVisitByAjax()">
                        <i class="typeahead__search-icon"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>

    <!--下拉刷新容器-->

    <div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: 100px;padding-top: 0px">

        <div class="mui-scroll" style="width: 95%">
            <input type="hidden" id="salesmanName" value=""/>

            <!--数据列表-->
            <ul class="mui-table-view mui-table-view-chevron" id="visitList">

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
        mui.toast('添加销售拜访');
        window.location.href = "/natergy-h5/visit/visitAddInit";
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
        var salesExecutive="${salesExecutive}";
        var user="${user}";
        var visitListListByUser = ${visitList};
        var json = eval(visitListListByUser);
        $("#limit").val(10);
        for (var i = 0; i < json.length; i++) { //循环数据
            $("#visitList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'><img style='width: 50px;height: 50px;' class='mui-media-object mui-pull-left' src='http://219.146.150.102:20005/" + json[i].images[0] + "'>" +
                json[i].customerName +
                "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                "<p class='mui-ellipsis'>" + json[i].user +"</p>" +
                "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
        }
        if((salesExecutive.indexOf(user)!=-1)||"邢振"==user){
            $("#footer").css("display","");
            $.ajax({
                url: "/natergy-h5/getSalesman",
                contentType: "application/json;charset=utf-8",
                type: "get",
                dataType: "json",
                success: function (data) {
                    html="";
                    for(var i=0;i<data.length;i++){
                        html +='<li class="mui-table-view-cell"><a href="#">'+data[i]+'</a></li>';
                    }
                    $("#salesmanList").append(html);
                }
            });
        }
        mui('.mui-scroll-wrapper').scroll();
        mui('body').on('tap', '#salesmanList li', function () {
            chooseSalesman(this)
        });
        mui('body').on('tap', '#visitList li', function () {
            clickLi(this)
        });
        mui('body').on('tap', '#addVisit', function () {
            funAdd()
        });
    });
</script>
<script>
    var allName = ${visitsNameSet}
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
    function getVisitByAjax() {
        $.ajax({
            url: "/natergy-h5/visit/getVisitInfoByAjax",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "customerName": $("#searchedName").val(),
            },
            dataType: "json",
            success: function (data) {
                var json = eval(data);
                $("#visitList li").remove();
                for (var i = 0; i < json.length; i++) { //循环数据
                    $("#visitList").append("<li class='mui-table-view-cell mui-media' style='height:55px' ><a class='mui-navigate-right'><div class='mui-media-body'><img style='width: 50px;height: 50px;' class='mui-media-object mui-pull-left' src='http://219.146.150.102:20005/" + json[i].images[0] + "'>" +
                        json[i].customerName +
                        "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                        "<p class='mui-ellipsis'>" + json[i].user +"</p>" +
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
                url: "/natergy-h5/visit/reload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#visitList li").length,
                    "salesmanName":$("#salesmanName").val()
                },
                success: function (data) {
                    var json = eval(data);
                    if (json.length < 5) {
                        count = true;
                    }
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#visitList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'><img style='width: 50px;height: 50px;' class='mui-media-object mui-pull-left' src='http://219.146.150.102:20005/" + json[i].images[0] + "'>" +
                            json[i].customerName +
                            "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user +"</p>" +
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
        $("#salesmanName").val("");
        setTimeout(function () {
            $.ajax({
                url: "/natergy-h5/visit/refresh",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: "",
                success: function (json) {
                    $("#visitList li").remove();
                    //var json = eval(data);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        $("#visitList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'><img style='width: 50px;height: 50px;' class='mui-media-object mui-pull-left' src='http://219.146.150.102:20005/" + json[i].images[0] + "'>" +
                            json[i].customerName +
                            "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                            "<p class='mui-ellipsis'>" + json[i].user +"</p>" +
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
        localStorage.setItem("visit", $(obj).find("input:hidden").val());
        window.location.href = "<%=request.getContextPath()%>/jsp/visit/visitEdit.jsp"
    }
</script>
<script>
    function chooseSalesman(obj){
        var $salesmanA = $(obj).find("a");
        var salesmanName = $salesmanA.text();
        $("#salesmanName").val(salesmanName)
        $.ajax({
            url: "/natergy-h5/visit/getVisitInfoBySalesman",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "salesmanName":salesmanName,
            },
            dataType: "json",
            success: function (json) {
                $("#visitList li").remove()
                for (var i = 0; i < json.length; i++) { //循环数据
                    $("#visitList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'><img style='width: 50px;height: 50px;' class='mui-media-object mui-pull-left' src='http://219.146.150.102:20005/" + json[i].images[0] + "'>" +
                        json[i].customerName +
                        "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                        "<p class='mui-ellipsis'>" + json[i].user +"</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                }
            }
        });
    }
</script>
</body>

</html>