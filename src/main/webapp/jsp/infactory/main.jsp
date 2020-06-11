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
    <style>
        .icon {
            width: 3em;
            height: 3em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
    </style>
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
    <a class="mui-tab-item" href="#tabbar2">
        <span class="mui-icon mui-icon-email"><!--<span class="mui-badge">43</span>--></span>
        <span class="mui-tab-label">消息</span>
    </a>
    <a class="mui-tab-item" href="javascript:;" onclick="funForward()">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">通讯录</span>
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
                <a href="javascript:;" onclick="funCook()">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-dingcan"></use>
                    </svg>
                    <div class="mui-media-body">员工订餐</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funCookList()">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-ico_shujuchaxunyutongji_dingcanmingxi"></use>
                    </svg>
                    <div class="mui-media-body">订餐信息</div>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
                <a href="javascript:;" onclick="funCookCount()">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-tongji"></use>
                    </svg>
                    <div class="mui-media-body">订餐统计</div>
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
<script src="<%=request.getContextPath()%>/js/iconfont.js"></script>
<script type="text/javascript">
    /** 订单 **/
    function funCook() {
        window.location.href = "/natergy-h5/infactory/cook/init";
    }

    function funCookList() {
        window.location.href = "/natergy-h5/infactory/cook/listInit";
    }

    /** 拜访 **/
    function funCookCount() {
        window.location.href = "/natergy-h5/jsp/infactory/cook/cookCount.jsp";
    }



    /** 退出系统，返回登录界面 **/
    function funExit() {
        window.location.href = "/natergy-h5/logout";
    }


    /** 禁止微信下拉动作 **/
    document.getElementsByTagName("body")[0].style.height = document.body.scrollHeight + "px";
    //document.getElementsByTagName("body")[0].style.width  = document.body.scrollWidth+"px";
    mui.init({
        swipeBack: true //启用右滑关闭功能
    });
    document.body.addEventListener('touchmove', function (e) {
        e.preventDefault(); //阻止默认的处理方式(阻止下拉滑动的效果)
    }, {
        passive: false
    }); //passive 参数不能省略，用来兼容ios和android
</script>
</body>

</html>