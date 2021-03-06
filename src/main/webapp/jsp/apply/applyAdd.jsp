<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/11/28
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">
    <link href="<%=request.getContextPath()%>/css/mui.picker.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/mui.poppicker.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fonts/my002/iconfont.css"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>

    <style>
        h5 {
            margin: 5px 7px;
        }

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
<div class="mui-content">


    <div class="mui-content-padded" style="margin: 15px;">
        <header class="mui-bar mui-bar-nav">
            <h1 class="mui-title">提交申请单</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>

        <form id="form-country_v1" name="form-country_v1" style="margin-top: 50px;">
            <div class="typeahead__container">
                <div class="typeahead__field">
                    <div class="typeahead__query">
                        <input class="js-typeahead-country_v1" name="country_v1[query]" type="search"
                               placeholder="Search" autocomplete="off" id="searchedName">
                    </div>
                    <div class="typeahead__button">
                        <button type="button" onclick="getAddressByAjax()">
                            <i class="typeahead__search-icon"></i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">

        <div class="mui-input-row">
            <label>客户名称</label> <input type="text" placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>客户编号</label> <input type="text" placeholder="" id="customerNo" readonly="true">
        </div>
        <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
            <button id='typePicker' class="mui-btn mui-btn-block" type='button'
                    style="width: 100%; text-align: center;">申请类型
            </button>
        </div>
        <div id="changed">

        </div>

    </form>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <textarea id="content" rows="5" placeholder="请输入申请内容"></textarea>
    </div>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">提交申请单</button>
    </div>
</div>

</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script>
    var allName = ${allCompanysNameList}
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
    function getAddressByAjax() {
        $.ajax({
            url: "/natergy-h5/customer/getCustomerAllInfo",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            dataType: "json",
            data: {
                "name": $("#searchedName").val()
            },
            success: function (data) {
                $("#customerName").val(data.customerName);
                $("#customerNo").val(data.customerNo);
            }
        });
    }
</script>
<script>
    function changeDiv(type) {
        $("#changed").empty();
        if ("退款" == type) {
            html = '<div class="mui-input-row"><label>退款金额</label> <input type="text" placeholder="" id="refundAmount" ></div>' +
                '<div class="mui-input-row"><label>退款账户</label> <input type="text" placeholder="" id="refundAccount" ></div>'
            $("#changed").append(html);
        }
        if ("调账" == type) {
            html = '<div class="mui-input-row"><label>调账金额</label> <input type="text" placeholder="" id="adjustmentAmount" ></div>' +
                '<div class="mui-input-row"><label>调后余额</label> <input type="text" placeholder="" id="balance" ></div>'
            $("#changed").append(html);
        }
        if ("客情" == type) {
            html = '<div class="mui-input-row"><label>费用金额</label> <input type="text" placeholder="" id="costs" ></div>'
            $("#changed").append(html);
        }
        if ("调价" == type) {
            html = '<div class="mui-input-row"><label>原单价</label> <input type="text" placeholder="" id="originalPrice" ></div>' +
                '<div class="mui-input-row"><label>现单价</label> <input type="text" placeholder="" id="presentPrice" ></div>'
            $("#changed").append(html);
        }
        if ("赠品" == type) {
            html = '<div class="mui-input-row"><label>赠品赠送方式</label> <input type="text" placeholder="" id="givingType" ></div>' +
                '<div class="mui-input-row"><label>折合单价</label> <input type="text" placeholder="" id="conversionPrice" ></div>'
            $("#changed").append(html);
        }
    }

    (function ($, doc) {
        $.init();
        $.ready(function () {
            /**
             * 获取对象属性的值
             * 主要用于过滤三级联动中，可能出现的最低级的数据不存在的情况，实际开发中需要注意这一点；
             * @param {Object} obj 对象
             * @param {String} param 属性名
             */
            var _getParam = function (obj, param) {
                return obj[param] || '';
            };
            var picker = new $.PopPicker();
            picker.setData(applyType);
            var productBrandPickerButton = doc.getElementById('typePicker');
            productBrandPickerButton.addEventListener('tap', function (event) {
                picker.show(function (items) {
                    doc.getElementById('typePicker').innerText = _getParam(items[0], 'text');
                    changeDiv(_getParam(items[0], 'text'));
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
        });
    })(mui, document);
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {

        var obj = {"customerName": $("#customerName").val(),"customerNo":$("#customerNo").val(),"type":$("#typePicker").html(),"content":$("#content").val()};
        $("#changed input").each(function () {
            obj[$(this).attr("id")]=$(this).val();
        })

        mui.toast('正在保存拜访记录...');
        $.ajax({
            url: "/natergy-h5/apply/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify(obj),
            success: function (data) {
                if (1 == data) {
                    mui.toast('保存成功');
                    window.location.href = "/natergy-h5/apply/init"
                } else {
                    mui.toast('出差拜访保存失败，请稍后重试...');
                }
            }
        });


        mui(this).button('loading');

        setTimeout(function () {
            mui(this).button('reset');

            //mui.back();

        }.bind(this), 2000);
    });
</script>
</body>
</html>
