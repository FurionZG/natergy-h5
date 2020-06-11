<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/11/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/mui.indexedlist.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
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
    <h1 class="mui-title">客户资料</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>
</header>
<div id="topPopover" class="mui-popover">
    <div class="mui-popover-arrow"></div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="javascript:;" onclick="funAdd()" id="addCustomer"> <span
                            class="mui-icon iconfont icon-add"></span>客户
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="mui-content" style="margin-top:1rem;">
    <div id='list' class="mui-indexed-list">
        <div class="mui-indexed-list-search mui-input-row mui-search">
            <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索">
        </div>
        <div class="mui-indexed-list-bar">
            <a>A</a>
            <a>B</a>
            <a>C</a>
            <a>D</a>
            <a>E</a>
            <a>F</a>
            <a>G</a>
            <a>H</a>
            <a>I</a>
            <a>J</a>
            <a>K</a>
            <a>L</a>
            <a>M</a>
            <a>N</a>
            <a>O</a>
            <a>P</a>
            <a>Q</a>
            <a>R</a>
            <a>S</a>
            <a>T</a>
            <a>U</a>
            <a>V</a>
            <a>W</a>
            <a>X</a>
            <a>Y</a>
            <a>Z</a>
        </div>
        <div class="mui-indexed-list-alert"></div>
        <div class="mui-indexed-list-inner">
            <div class="mui-indexed-list-empty-alert">没有数据</div>
            <ul class="mui-table-view" id="">
                <li data-group="A" class="mui-table-view-divider mui-indexed-list-group">A</li>
                <li data-group="B" class="mui-table-view-divider mui-indexed-list-group">B</li>
                <li data-group="C" class="mui-table-view-divider mui-indexed-list-group">C</li>
                <li data-group="D" class="mui-table-view-divider mui-indexed-list-group">D</li>
                <li data-group="E" class="mui-table-view-divider mui-indexed-list-group">E</li>
                <li data-group="F" class="mui-table-view-divider mui-indexed-list-group">F</li>
                <li data-group="G" class="mui-table-view-divider mui-indexed-list-group">G</li>
                <li data-group="H" class="mui-table-view-divider mui-indexed-list-group">H</li>
                <li data-group="I" class="mui-table-view-divider mui-indexed-list-group">I</li>
                <li data-group="J" class="mui-table-view-divider mui-indexed-list-group">J</li>
                <li data-group="K" class="mui-table-view-divider mui-indexed-list-group">K</li>
                <li data-group="L" class="mui-table-view-divider mui-indexed-list-group">L</li>
                <li data-group="M" class="mui-table-view-divider mui-indexed-list-group">M</li>
                <li data-group="N" class="mui-table-view-divider mui-indexed-list-group">N</li>
                <li data-group="O" class="mui-table-view-divider mui-indexed-list-group">O</li>
                <li data-group="P" class="mui-table-view-divider mui-indexed-list-group">P</li>
                <li data-group="Q" class="mui-table-view-divider mui-indexed-list-group">Q</li>
                <li data-group="R" class="mui-table-view-divider mui-indexed-list-group">R</li>
                <li data-group="S" class="mui-table-view-divider mui-indexed-list-group">S</li>
                <li data-group="T" class="mui-table-view-divider mui-indexed-list-group">T</li>
                <li data-group="U" class="mui-table-view-divider mui-indexed-list-group">U</li>
                <li data-group="V" class="mui-table-view-divider mui-indexed-list-group">V</li>
                <li data-group="W" class="mui-table-view-divider mui-indexed-list-group">W</li>
                <li data-group="X" class="mui-table-view-divider mui-indexed-list-group">X</li>
                <li data-group="Y" class="mui-table-view-divider mui-indexed-list-group">Y</li>
                <li data-group="Z" class="mui-table-view-divider mui-indexed-list-group">Z</li>
            </ul>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.indexedlist.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pinyin.js"></script>
<script>
    function viewCustomer(obj) {
        $.ajax({
            url: "/natergy-h5/customer/getCustomerAllInfo",
            dataType: "json",
            data: {"name":obj.innerHTML.trim().split("\t")[0]},
            type:"get",
            success: function (data) {
                if(null!=data){
                    localStorage.setItem("customer", JSON.stringify(data));
                    window.location.href = "<%=request.getContextPath()%>/jsp/customer/viewCustomer.jsp"
                }
            }
        })
    }
</script>
<script type="text/javascript">
    /** 添加客户资料 **/
    function funAdd() {
        mui.toast('添加客户资料');
        window.location.href = "<%=request.getContextPath()%>/jsp/customer/addCustomer.jsp";
    }
</script>
<script type="text/javascript">
    mui.init();
    mui.ready(function () {
        loadList()
    });

    function loadList() {
        $.ajax({
            url: "/natergy-h5/customer/getCustomerList",
            dataType: "json",
            cache: false,
            async: false,
            data: {},
            type: "POST",
            success: function (ret) {
                var data = eval(ret);
                //每次加载把之前加载的数据清除
                $(".mui-table-view .mui-indexed-list-group").nextAll().not(".mui-indexed-list-group").remove();
                for (var i in data) {
                    //取出中文转换的第一个拼音字母
                    var pinyin = ConvertPinyin(data[i]).substring(0, 1);
                    //循环字母列表
                    $(".mui-table-view .mui-indexed-list-group").each(function () {
                        if ($(this).html() == pinyin) {
                            var html = '<li class="mui-table-view-cell mui-indexed-list-item" onclick="viewCustomer(this);"> ' + data[i] + '</li>';
                            $(this).after(html);
                        }
                    })

                }
                //将后面没有值得字母列表隐藏
                $(".mui-table-view .mui-indexed-list-group").each(function () {
                    if ($(this).next().hasClass("mui-indexed-list-group")) {
                        $(this).remove();
                    }
                })


            },
            error: function () {
            }
        });

        var header = document.querySelector('.mui-title');
        var footer = document.querySelector('.mui-tab-item');
        var list = document.getElementById('list');
        //calc hieght
        list.style.height = document.body.offsetHeight + 'px';
        //create
        //mui('.mui-indexed-list').indexedList();
        window.indexedList = new mui.IndexedList(list);
    }
</script>

</body>
</html>
