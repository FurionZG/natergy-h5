<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.picker.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />

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
            <h1 class="mui-title">添加出差申请</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>

    </div>

    <div style="padding-top: 30px">
        <input type="hidden" id="id">

        <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
            <button id='businessNoPicker' class="mui-btn mui-btn-block" type='button'
                    style="width: 100%; text-align: center;">选择出差编号
            </button>
        </div>
        <label>预计出差开始时间</label>
        <button id="pStartDate" data-options='{"type":"date","beginYear":2019,"endYear":2022}'
                class="btn mui-btn mui-btn-block">选择日期 ...
        </button>

        <label>预计出差结束时间</label>
        <button id="pEndDate" data-options='{"type":"date","beginYear":2019,"endYear":2022}'
                class="btn mui-btn mui-btn-block">选择日期 ...
        </button>

        <label>计划地点</label>
        <div class="mui-input-row">
            <input type="text" placeholder="请输入计划地点" id="pPos">
        </div>

        <label>计划路线</label>
        <div class="mui-input-row" >
            <textarea id="pWay" rows="4" placeholder="请输入计划路线"></textarea>
        </div>


        <label>本次出差拜访计划详情</label>
        <div class="mui-input-row" >
            <textarea id="plan" rows="6" placeholder="请输入本次出差拜访计划详情"></textarea>
        </div>

        <label>预计出差花费</label>
        <div class="mui-input-row">
            <input type="text" placeholder="请输入预计出差花费" id="pCosts">
        </div>

        <label>部门主管意见</label>
        <div class="mui-input-row" >
            <textarea id="directorOpinion" rows="3"  readonly="true"></textarea>
        </div>

        <label>总经理意见</label>
        <div class="mui-input-row" >
            <textarea id="presidentOpinion" rows="3"  readonly="true"></textarea>
        </div>


        <div class="mui-content-padded" style="margin-top: 20px;">
            <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">提交出差申请
            </button>
        </div>

    </div>
</div>
</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.min.js"></script>

<script>
    (function ($, doc) {
    $.init();
    $.ready(function () {
        var businessNoList = ${businessNoList};
        var json = eval(businessNoList);
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
        picker.setData(json);
        var businessNoPickerButton = doc.getElementById('businessNoPicker');
        //				var cityResult3 = doc.getElementById('userResult');
        businessNoPickerButton.addEventListener('tap', function (event) {
            picker.show(function (items) {
                //						cityResult3.innerText = "你选择的城市是:" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
                doc.getElementById('businessNoPicker').innerText = _getParam(items[0], 'text');
                //返回 false 可以阻止选择框的关闭
                //return false;
            });
        }, false);
    });
})(mui, document);
</script>



<script>
    mui.init({
        swipeBack: false
        //关于右滑关闭功能
    });
    var nativeWebview, imm, InputMethodManager;
    var initNativeObjects = function () {
        if (mui.os.android) {
            var main = plus.android.runtimeMainActivity();
            var Context = plus.android.importClass("android.content.Context");
            InputMethodManager = plus.android
                .importClass("android.view.inputmethod.InputMethodManager");
            imm = main.getSystemService(Context.INPUT_METHOD_SERVICE);
        } else {
            nativeWebview = plus.webview.currentWebview()
                .nativeInstanceObject();
        }
    };
    var showSoftInput = function () {
        if (mui.os.android) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        } else {
            nativeWebview.plusCallMethod({
                "setKeyboardDisplayRequiresUserAction": false
            });
        }
        setTimeout(function () {
            var inputElem = document.querySelector('input');
            inputElem.focus();
            inputElem.parentNode.classList.add('mui-active'); //第一个是search，加上激活样式
        }, 200);
    };
    mui.plusReady(function () {
        initNativeObjects();
        showSoftInput();
    });
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {



        mui.toast('正在提交出差申请...');
        $.ajax({
            url: "/natergy-h5/businessApply/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "pBusinessStartTime":$("#pStartDate").text(),
                "pBusinessEndTime":$("#pEndDate").text(),
                "pPos": $("#pPos").val(),
                "pWay": $("#pWay").val(),
                "plan": $("#plan").val(),
                "pCosts": $("#pCosts").val(),
                "businessNo": $("#businessNoPicker").text()
            }), success: function (data) {
                //alert("Data Loaded: " + data);
                if (1 == data) {
                    mui.toast('保存成功');
                    window.location.href = "/natergy-h5/businessApply/init"
                } else {
                    mui.toast('出差申请提交失败，请稍后重试...');
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

<script>
    var btns = mui('.btn');
    btns.each(function(i, btn) {
        btn.addEventListener('tap', function() {
            var _self = this;
            if(_self.picker) {
                _self.picker.show(function (rs) {
                    btns[i].innerText =  rs.y.text+"年"+rs.m.text+"月"+rs.d.text+"日";
                    _self.picker.dispose();
                    _self.picker = null;
                });
            } else {
                var optionsJson = this.getAttribute('data-options') || '{}';
                var options = JSON.parse(optionsJson);
                var id = this.getAttribute('id');
                _self.picker = new mui.DtPicker(options);
                _self.picker.show(function(rs) {
                    /*
                     * rs.value 拼合后的 value
                     * rs.text 拼合后的 text
                     * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
                     * rs.m 月，用法同年
                     * rs.d 日，用法同年
                     * rs.h 时，用法同年
                     * rs.i 分（minutes 的第二个字母），用法同年
                     */
                    btns[i].innerText =  rs.y.text+"年"+rs.m.text+"月"+rs.d.text+"日";
                    _self.picker.dispose();
                    _self.picker = null;
                });
            }

        }, false);
    });
</script>
</body>

</html>
