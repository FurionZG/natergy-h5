<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>

    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/mui.indexedlist.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my001/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    </head>
<body>
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">Natergy Integration</h1>
</header>
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item mui-active" href="#tabbar1">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
    <a id="tj" class="mui-tab-item" href="#tabbar5" style="display: none">
        <span class="mui-icon iconfont icon-tongji"><!--<span class="mui-badge">43</span>--></span>
        <span class="mui-tab-label">统计</span>
    </a>
    <a class="mui-tab-item" href="#tabbar2">
        <span class="mui-icon mui-icon-email"><!--<span class="mui-badge">43</span>--></span>
        <span class="mui-tab-label">消息</span>
    </a>
    <a class="mui-tab-item" href="javascript:;" onclick="funForward()">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">客户</span>
    </a>
    <a class="mui-tab-item" href="#tabbar4">
        <span class="mui-icon mui-icon-gear"></span>
        <span class="mui-tab-label">设置</span>
    </a>
</nav>
<div class="mui-content">
    <div id="tabbar1" class="mui-control-content mui-active">
        <ul class="mui-table-view mui-grid-view mui-grid-9">
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funOrder()">
                    <span class="mui-icon iconfont icon-dingdan"></span>
                    <div class="mui-media-body">订单</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funRefund()">
                    <span class="mui-icon iconfont icon-tuihuo"></span>
                    <div class="mui-media-body">退货</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funVisit()">
                    <span class="mui-icon iconfont icon-baifang"></span>
                    <div class="mui-media-body">拜访</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funBusiness()">
                    <span class="mui-icon iconfont icon-chuchashenqing"></span>
                    <div class="mui-media-body">出差</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funPrice()">
                    <span class="mui-icon iconfont icon-baojiadan"></span>
                    <div class="mui-media-body">报价单</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funWork()">
                    <span class="mui-icon iconfont icon-rizhi"></span>
                    <div class="mui-media-body">工作</div>
                </a>
            </li>

            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funFollowUp()">
                    <span class="mui-icon iconfont icon-qiyegenjinguanli"></span>
                    <div class="mui-media-body">地产跟进</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funApply()">
                    <span class="mui-icon iconfont icon-shenqing"></span>
                    <div class="mui-media-body">申请单</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funBusinessApply()">
                    <span class="mui-icon iconfont icon-chuchashenqingshenpiliucheng"></span>
                    <div class="mui-media-body">出差申请</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funForward()">
                    <span class="mui-icon iconfont icon-kehu"></span>
                    <div class="mui-media-body">客户</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="test()">
                    <span class="mui-icon iconfont icon-dd_active"></span>
                    <div class="mui-media-body">测试</div>
                </a>
            </li>
        </ul>
    </div>
    <div id="tabbar5" class="mui-control-content">
        <ul class="mui-table-view mui-grid-view mui-grid-9">
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funOrderHotMap()">
                    <span class="mui-icon iconfont icon-dingdantongjiTJ"></span>
                    <div class="mui-media-body">销售订单热力图</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funOrderReturnHotMap()">
                    <span class="mui-icon iconfont icon-tuihuoTJ"></span>
                    <div class="mui-media-body">销售退货订单热力图</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funOrderSumHotMap()">
                    <span class="mui-icon iconfont icon-summaryTJ"></span>
                    <div class="mui-media-body">销售合计订单热力图</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funStatisticsList()">
                    <span class="mui-icon iconfont icon-tubiaoTJ"></span>
                    <div class="mui-media-body">销售订单统计表</div>
                </a>
            </li>
        </ul>
    </div>
    <div id="tabbar2" class="mui-control-content">
        <ul class="mui-table-view mui-table-view-chevron">
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">部门调整</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">安环办检查通知</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">人事任命调整</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">人事任命调整</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">年假通知</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">培训通知</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="" class="mui-navigate-right">会议通知</a>
            </li>
            <li class="mui-table-view-cell">
                <a href="/natergy-h5/notice/init" class="mui-navigate-right">销售通知</a>
            </li>
        </ul>

        <!--<div style="margin-right: 10px;">
            <button type="button" class="mui-btn mui-btn-link">更多消息...<span class="mui-badge">43</span></button>
        </div>-->
    </div>
    <div id="tabbar3" class="mui-control-content">

    </div>



    <div id="tabbar4" class="mui-control-content">
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">
                <a class="mui-navigate-right">
                    设置A
                </a>
            </li>
            <li class="mui-table-view-cell">
                <a class="mui-navigate-right">
                    设置B
                </a>
            </li>
            <li class="mui-table-view-cell">
                <a class="mui-navigate-right">
                    设置C
                </a>
            </li>
        </ul>
        <ul class="mui-table-view" style="margin-top: 25px;">
            <li class="mui-table-view-cell">
                <a class="mui-navigate-right">
                    关于NI
                </a>
            </li>
        </ul>
        <ul class="mui-table-view" style="margin-top: 25px;">
            <li class="mui-table-view-cell">
                <a href="javascript:;" onclick="funExit()" style="text-align: center;color: #FF3B30;">
                    退出登录
                </a>
            </li>
        </ul>
    </div>


</div>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.indexedlist.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pinyin.js"></script>

<script type="text/javascript">
    $(document).ready(function loading() {
        $.ajax({
            url: "/natergy-h5/getSalesExecutive",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            timeout: "1000",
            dataType: "json",
            success: function (data) {
                var user="${user}";
                if(data.indexOf(user)!=-1){
                    console.log(1)
                    $("#tj").css("display","")
                }
            }
        });
    });

</script>
<script type="text/javascript">
    /** 订单 **/
    function funOrder() {
        window.location.href = "/natergy-h5/order/init";
    }

    function funRefund() {
        window.location.href = "/natergy-h5/refund/init";
    }

    /** 拜访 **/
    function funVisit() {
        window.location.href = "/natergy-h5/visit/init";
    }

    /** 出差 **/
    function funBusiness() {
        window.location.href = "/natergy-h5/business/init";
    }

    /** 工作 **/
    function funWork() {
        window.location.href = "/natergy-h5/working/init";
    }

    /** 统计报表 **/
    function funReport() {
        mui.toast('统计报表-待开放...');
    }

    /** 地产跟进 **/
    function funFollowUp() {
        window.location.href = "/natergy-h5/followUp/init";
    }
    /** 申请单 **/
    function funApply() {
        window.location.href = "/natergy-h5/apply/init";
    }
    /** 报价单 **/
    function funPrice() {
        window.location.href = "/natergy-h5/excelToImage/init";
    }
    /** 出差申请 **/
    function funBusinessApply() {
        window.location.href = "/natergy-h5/businessApply/init";
    }
    /** 出差申请 **/
    function funOrderHotMap() {
        window.location.href = "/natergy-h5/statistics/page_orderhotmap";
    }
    /** 出差申请 **/
    function funOrderReturnHotMap() {
        window.location.href = "/natergy-h5/statistics/page_returnbackorderhotmap";
    }
    /** 出差申请 **/
    function funOrderSumHotMap() {
        window.location.href = "/natergy-h5/statistics/page_sumorderhotmap";
    }
    /** 出差申请 **/
    function funStatisticsList() {
        window.location.href = "/natergy-h5/statistics/page_orderstatics_tj";
    }

    function test() {
        mui.toast('测试测试...');
        //window.location.href = "<%=request.getContextPath()%>/xx";
    }

    /** 退出系统，返回登录界面 **/
    function funExit() {
        window.location.href = "/natergy-h5/logout";
    }
    function funForward(){
        window.location.href = "/natergy-h5/jsp/indexedList.jsp";
    }

    /** 禁止微信下拉动作 **/
    document.getElementsByTagName("body")[0].style.height = document.body.scrollHeight + "px";
    //document.getElementsByTagName("body")[0].style.width  = document.body.scrollWidth+"px";

    document.body.addEventListener('touchmove', function (e) {
        e.preventDefault(); //阻止默认的处理方式(阻止下拉滑动的效果)
    }, {
        passive: false
    }); //passive 参数不能省略，用来兼容ios和android
</script>
</body>

</html>