<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>销售订单统计</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.picker.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icons-extra.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css"/>

</head>
<body>
<header class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" href="page_orderdetailtotal_tj"></a>
    <h1 class="mui-title">销售订单统计表</h1>
</header>


<div class="mui-content">
    <div class="mui-card">
        <div class="mui-card-header">${tj.manager}</div>
        <ul class="mui-table-view">

            <li class="mui-table-view-cell mui-collapse">
                <a class="mui-navigate-right " href="#"><span
                        class="mui-icon-extra mui-icon-extra-prech">按价格（可滑动）</span></a>
                <div class="mui-collapse-content">

                    <div id="slider_price" class="mui-slider">
                        <div class="mui-slider-group mui-slider-loop">

                            <c:forEach items="${tj.unitpriceMap}" var="item" varStatus="pricerecordloop_status"
                                       begin="0">
                            <!-- 首节点与其它的样式不一样 -->
                            <c:choose>
                            <c:when test="${pricerecordloop_status.first}">
                            <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                </c:when>
                                <c:otherwise>
                                <div class="mui-slider-item " style="height: 25%;">
                                    </c:otherwise>
                                    </c:choose>


                                    <ul class="mui-table-view">
                                        <li class="mui-table-view-cell">
                                            价格: <span id="channel">${ item.key}元</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总重: <span id="bill_no">${ item.value.totalNetWeight/1000}吨</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总金额: <span id="total_fee">${ item.value.totalPrice/10000}万元</span>
                                        </li>

                                    </ul>
                                </div>
                                </c:forEach>

                                <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                                <c:forEach items="${tj.unitpriceMap}" var="item" varStatus="id" begin="0" end="1">

                                    <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                        <ul class="mui-table-view">
                                            <li class="mui-table-view-cell">
                                                价格: <span id="channel">${ item.key}元</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总重: <span id="bill_no">${ item.value.totalNetWeight/1000}吨</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总金额: <span id="total_fee">${ item.value.totalPrice/10000}万元</span>
                                            </li>
                                        </ul>
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="mui-slider-indicator">

                                <c:forEach items="${tj.unitpriceMap}" var="item" varStatus="price_indicator_status"
                                           begin="0">
                                    <c:choose>
                                        <c:when test="${price_indicator_status.first}">
                                            <div class="mui-indicator mui-active"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="mui-indicator"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


            <li class="mui-table-view-cell mui-collapse">
                <a class="mui-navigate-right" href="#"><span class="mui-icon-extra mui-icon-extra-card">按包装（可滑动）</span></a>
                <div class="mui-collapse-content">
                    <div id="slider_pack" class="mui-slider">
                        <div class="mui-slider-group mui-slider-loop">
                            <c:forEach items="${tj.packMap}" var="pack" varStatus="pack_record_status" begin="0">
                            <c:choose>
                            <c:when test="${pack_record_status.first}">
                            <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                </c:when>
                                <c:otherwise>
                                <div class="mui-slider-item " style="height: 25%;">
                                    </c:otherwise>
                                    </c:choose>

                                    <ul class="mui-table-view">
                                        <li class="mui-table-view-cell">
                                            包装: <span id="channel">${ pack.key}</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总重: <span id="bill_no">${ pack.value.totalNetWeight/1000}吨</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总金额: <span id="total_fee">${ pack.value.totalPrice/10000}万元</span>
                                        </li>

                                    </ul>
                                </div>
                                </c:forEach>
                                <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                                <c:forEach items="${tj.packMap}" var="item" varStatus="id1" begin="0" end="1">

                                    <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                        <ul class="mui-table-view">
                                            <li class="mui-table-view-cell">
                                                包装: <span id="channel">${ item.key}元</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总重: <span id="bill_no">${ item.value.totalNetWeight/1000}吨</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总金额: <span id="total_fee">${ item.value.totalPrice/10000}万元</span>
                                            </li>
                                        </ul>
                                    </div>
                                </c:forEach>


                            </div>
                            <div class="mui-slider-indicator">

                                <c:forEach items="${tj.packMap}" var="pack" varStatus="pack_loop_indicator_status"
                                           begin="0">

                                    <c:choose>
                                        <c:when test="${pack_loop_indicator_status.first}">
                                            <div class="mui-indicator mui-active"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="mui-indicator"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


            <li class="mui-table-view-cell mui-collapse">
                <a class="mui-navigate-right" href="#"><span class="mui-icon-extra mui-icon-extra-card">按省份（可滑动）</span></a>
                <div class="mui-collapse-content">
                    <div id="slider_province" class="mui-slider">
                        <div class="mui-slider-group mui-slider-loop">
                            <c:forEach items="${tj.provinceMap}" var="province" varStatus="provice_loop_status"
                                       begin="0">
                            <c:choose>
                            <c:when test="${provice_loop_status.first}">
                            <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                </c:when>
                                <c:otherwise>
                                <div class="mui-slider-item " style="height: 25%;">
                                    </c:otherwise>
                                    </c:choose>

                                    <ul class="mui-table-view">
                                        <li class="mui-table-view-cell">
                                            省份: <span id="channel">${ province.key}</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总重: <span id="bill_no">${ province.value.totalNetWeight/1000}吨</span>
                                        </li>
                                        <li class="mui-table-view-cell">
                                            总金额: <span id="total_fee">${ province.value.totalPrice/10000}万元</span>
                                        </li>

                                    </ul>
                                </div>
                                </c:forEach>
                                <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                                <c:forEach items="${tj.provinceMap}" var="province"
                                           varStatus="province_last_node_status" begin="0" end="1">

                                    <div class="mui-slider-item mui-slider-item-duplicate" style="height: 25%;">
                                        <ul class="mui-table-view">
                                            <li class="mui-table-view-cell">
                                                省份: <span id="channel">${ province.key}</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总重: <span id="bill_no">${ province.value.totalNetWeight/1000}吨</span>
                                            </li>
                                            <li class="mui-table-view-cell">
                                                总金额: <span id="total_fee">${ province.value.totalPrice/10000}万元</span>
                                            </li>

                                        </ul>
                                    </div>
                                </c:forEach>


                            </div>
                            <div class="mui-slider-indicator">

                                <c:forEach items="${tj.provinceMap}" var="province"
                                           varStatus="province_indicator_status" begin="0">

                                    <c:choose>
                                        <c:when test="${province_indicator_status.first}">
                                            <div class="mui-indicator mui-active"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="mui-indicator"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


        </ul>
        <div class="mui-card-footer">总重:${tj.totalWeight/1000}吨 &nbsp;&nbsp;总金额:${tj.totalPrice/10000}万元</div>
    </div>


</div>
<br>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
</body>
</html>
