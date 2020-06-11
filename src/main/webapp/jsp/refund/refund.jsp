<%@page contentType="text/html" %>
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
    <h1 class="mui-title">退货</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>
</header>
<footer id="footer" class="mui-bar mui-bar-footer" style="display: none">
    <a href="#bottomPopover" class="mui-btn mui-btn-link mui-pull-right">业务经理</a>
</footer>
<!--右上角弹出菜单-->
<div id="topPopover" class="mui-popover">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funAdd()" id="addFollow"> <span
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
<div id="bottomPopover" class="mui-popover mui-popover-bottom" style="height: 300px">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view" id = "salesmanList">
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
                    <button type="button" onclick="getRefundByAjax()">
                        <i class="typeahead__search-icon"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>
    <!--下拉刷新容器-->
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="margin-top: 100px;padding-top: 0px">
        <div class="mui-scroll">
            <!-- limit-->
            <input type="hidden" id="limit" value=""/>
            <!--数据列表-->
            <ul class="mui-table-view mui-table-view-chevron" id="refundList">

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
    /** 添加退货 **/
    function funAdd() {
        mui.toast('添加退货记录');
        window.location.href = "<%=request.getContextPath()%>/jsp/refund/refundAdd.jsp";
    }

    /** 筛选退货 **/
    function funFilter() {
        mui.toast('待开发...');
    }

    /** 刷新退货 **/
    function funRefresh() {
        mui.toast('待开发...');
    }
</script>

<script>
    $(document).ready(function loading() {
        var refundInfoListByUser = ${refundInfoListByUser};
        var salesExecutive="${salesExecutive}";
        var user="${user}";
        for (var i = 0; i < refundInfoListByUser.length; i++) { //循环数据
            $("#refundList").append("<li class='mui-table-view-cell mui-media'  ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                refundInfoListByUser[i].systemRefundNo + "<p class='mui-ellipsis'>" + refundInfoListByUser[i].user + "</p>" +
                "<p class='mui-ellipsis'>" + refundInfoListByUser[i].customerName + "</p>" +
                "<p class='mui-ellipsis'>" + refundInfoListByUser[i].refundTime + "</p>" +
                "<p class='mui-ellipsis'>" + refundInfoListByUser[i].status + "</p>" +
                "<p class='mui-ellipsis'>运费：" + refundInfoListByUser[i].carriage + "</p>" +
                "<input type='hidden' value ='" + JSON.stringify(refundInfoListByUser[i]) + "'/></div></a></li>");
        }
        if(salesExecutive.indexOf(user)!=-1){
            $("#footer").css("display","")
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
        mui('.mui-scroll-wrapper').scroll({
            indicators:false
        });
        //mui('.mui-scroll-wrapper').scroll();
        mui('body').on('tap', '#refundList li', function () {
            clickLi(this)
        });
        mui('body').on('tap', '#salesmanList li', function () {
            chooseSalesman(this)
        });
        mui('body').on('tap', '#addRefund', function () {
            funAdd()
        });
    });
</script>
<script>
    function chooseSalesman(obj){
        var $salesmanA = $(obj).find("a");
        var salesmanName = $salesmanA.text();
        $.ajax({
            url: "/natergy-h5/refund/getRefundInfoBySalesman",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "salesmanName":salesmanName,
            },
            dataType: "json",
            success: function (refundList) {
                $("#refundList li").remove()
                for (var i = 0; i < refundList.length; i++) { //循环数据
                    $("#refundList").append("<li class='mui-table-view-cell mui-media' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                        refundList[i].systemRefundNo + "<p class='mui-ellipsis'>" + refundList[i].user + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].customerName + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].refundTime + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].status + "</p>" +
                        "<p class='mui-ellipsis'>运费：" + refundList[i].carriage + "</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(refundList[i]) + "'/></div></a></li>");
                }
            }
        });
    }
</script>
<script>
    var allName = ${refundNameSet}
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
    function getRefundByAjax() {
        $.ajax({
            url: "/natergy-h5/refund/getRefundInfoByAjax",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "customerName": $("#searchedName").val(),
            },
            dataType: "json",
            success: function (refundList) {
                $("#refundList li").remove()
                $("#limit").val(refundList.limit);
                for (var i = 0; i < refundList.length; i++) { //循环数据
                    //console.log(refundList)
                    $("#refundList").append("<li class='mui-table-view-cell mui-media' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                        refundList[i].systemRefundNo + "<p class='mui-ellipsis'>" + refundList[i].user + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].customerName + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].refundTime + "</p>" +
                        "<p class='mui-ellipsis'>" + refundList[i].status + "</p>" +
                        "<p class='mui-ellipsis'>运费：" + refundList[i].carriage + "</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(refundList[i]) + "'/></div></a></li>");
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
    var count = 0;
    function pullupRefresh() {
        setTimeout(function () {
            $.ajax({
                url: "/natergy-h5/refund/reload",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                timeout: "1000",
                dataType: "json",
                data: {
                    "limit": $("#refundList li").length
                },
                success: function (refundList) {
                    var lastLi = $("#refundList li:last-child .mui-media-body");
                    for (var i = 0; i < refundList.length; i++) { //循环数据
                        //if (refundList[i].refundDetails[0].orderNumber != lastLi.text().substr(0, 15)) {
                            //这个if判断是判断上拉加载的第一个退货的退货单号是否等于当前ul中最后一个li的退货单号，因为使用limit可能导致一个退货单的退货单明细被分开，如果两者相等，则不动态生成li
                            $("#refundList").append("<li class='mui-table-view-cell mui-media'><a class='mui-navigate-right'><div class='mui-media-body'>" +
                                refundList[i].systemRefundNo + "<p class='mui-ellipsis'>" + refundList[i].user + "</p>" +
                                "<p class='mui-ellipsis'>" + refundList[i].customerName + "</p>" +
                                "<p class='mui-ellipsis'>" + refundList[i].refundTime + "</p>" +
                                "<p class='mui-ellipsis'>" + refundList[i].status + "</p>" +
                                "<p class='mui-ellipsis'>运费：" + refundList[i].carriage + "</p>" +
                                "<input type='hidden' value ='" + JSON.stringify(refundList[i]) + "'/></div></a></li>");
                        //}
                    }
                    // mui('body').on('tap', 'li', function() {
                    // 	clickLi(this)
                    // });
                    mui.toast("已为您加加载" + refundList.length + "条...");
                },
                complete: function (XMLHttpRequest, status) {
                    if (status == 'timeout') {
                        mui.toast("请求超时...");
                    }
                }
            });
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 10)); //参数为true代表没有更多数据了。
        }, 1000);
    }

    /**
     * 下拉刷新具体业务实现
     */
    function pulldownRefresh() {
        setTimeout(function () {
            $.ajax({
                url: "/natergy-h5/refund/refresh",
                contentType: "application/x-www-form-urlencoded:charset=UTF-8",
                type: "get",
                dataType: "json",
                data: "",
                success: function (refundList) {
                    count = 0;
                    $("#refundList li").remove()
                    $("#limit").val(refundList.limit);
                    for (var i = 0; i < refundList.length; i++) { //循环数据
                        $("#refundList").append("<li class='mui-table-view-cell mui-media' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            refundList[i].systemRefundNo + "<p class='mui-ellipsis'>" + refundList[i].user + "</p>" +
                            "<p class='mui-ellipsis'>" + refundList[i].customerName + "</p>" +
                            "<p class='mui-ellipsis'>" + refundList[i].refundTime + "</p>" +
                            "<p class='mui-ellipsis'>" + refundList[i].status + "</p>" +
                            "<p class='mui-ellipsis'>运费：" + refundList[i].carriage + "</p>" +
                            "<input type='hidden' value ='" + JSON.stringify(refundList[i]) + "'/></div></a></li>");
                    }
                }
            });
            // mui('body').on('tap', 'li', function() {
            // 	clickLi(this)
            // });
            mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
            mui.toast("已刷新...");
        }, 1000);
    }
</script>
<script>
    //查看退货
    function clickLi(obj) {
        var $refundInfo = $(obj).find("input:hidden");
        var json = $refundInfo.val();
        var data = eval("(" + json + ")")
        var refundDetails = eval(data.refundDetails);
         if(data.state == "草稿"){
            layer.open({
                type: 2,
                title: '退货明细',
                shadeClose: true,
                shade: 0.8,
                area: ['90%', '80%'],
                data: obj,
                content: "<%=request.getContextPath()%>/jsp/refund/refundDetailsView.jsp", //iframe的url
                btn: ['编辑','返回'],
                success: function (layero, index) {
                    var iframe = window['layui-layer-iframe' + index];
                    //调用子页面的全局函数
                    iframe.child(json);
                },
                cancel: function () {
                    //右上角关闭回调
                },
                yes: function () {
                    localStorage.setItem("refund", $(obj).find("input:hidden").val());
                    window.location.href = "<%=request.getContextPath()%>/jsp/refund/refundEdit.jsp"
                }
            });
        }else{
            layer.open({
                type: 2,
                title: '退货明细',
                shadeClose: true,
                shade: 0.8,
                area: ['90%', '80%'],
                data: obj,
                content: "<%=request.getContextPath()%>/jsp/refund/refundDetailsView.jsp", //iframe的url
                btn: ['返回'],
                success: function (layero, index) {
                    var iframe = window['layui-layer-iframe' + index];
                    //调用子页面的全局函数
                    iframe.child(json);
                },
                cancel: function () {
                    //右上角关闭回调
                },
            });
        }


    };
</script>

</body>

</html>