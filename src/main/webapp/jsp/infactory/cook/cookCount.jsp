<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-02-25
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.picker.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css"/>


    <style>
        h5 {
            margin: 5px 7px;
        }

        .mui-btn {
            font-size: 16px;
            padding: 8px;
            margin: 3px ;
            border-radius: 10px;
        }
    </style>
</head>

<body>


<div class="mui-content-padded" style="margin: 50px;">
    <header class="mui-bar mui-bar-nav">
        <h1 class="mui-title">订餐统计</h1>
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    </header>

</div>
<div class="mui-content">
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
    <button id="date" data-options='{"type":"date","beginYear":2019,"endYear":2022}'
            class="btn mui-btn mui-btn-block">选择日期</button>
    <button id='workspacePicker' class="mui-btn mui-btn-block" type='button'
            style="width: 100%; text-align: center;">就餐区域</button>
    <button id='eatPicker' class="mui-btn mui-btn-block" type='button'
            style="width: 100%; text-align: center;">餐次</button>
    </div>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">统计</button>
    </div>
    <div id="context"></div>

</div>
</div>

</body>

<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>

<!--修改跟进记录-->
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
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {

        if("选择日期"==$("#date").text()||"就餐区域"==$("#workspacePicker").text()||"餐次"==$("#eatPicker").text()){
            mui.toast('筛选条件不完整...');
            return;
        }
        mui.toast('正在查找...');
        $.ajax({
            url: "/natergy-h5/infactory/cook/count",
            contentType: "application/json;charset=utf-8",
            type: "get",
            dataType: "json",
            data: {
                "date": $("#date").text(),
                "workspace": $("#workspacePicker").text(),
                "eat": $("#eatPicker").text(),
            }, success: function (data) {
                $("#context h3").remove();
                $("#context").append("<h3>"+data+"</h3>");
            }
        });

        mui(this).button('loading');

        setTimeout(function () {
            mui(this).button('reset');
            //mui.back();
        }.bind(this), 1000);
    });
</script>
<script>
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
            picker.setData(workspace);
            var workspacePickerPickerButton = doc.getElementById('workspacePicker');
            //				var cityResult3 = doc.getElementById('userResult');
            workspacePickerPickerButton.addEventListener('tap', function (event) {
                picker.show(function (items) {
                    //						cityResult3.innerText = "你选择的城市是:" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
                    doc.getElementById('workspacePicker').innerText = _getParam(items[0], 'text');

                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);

            var picker1 = new $.PopPicker();
            picker1.setData(eat);
            var eatPickerButton = doc.getElementById('eatPicker');
            //				var cityResult3 = doc.getElementById('userResult');
            eatPickerButton.addEventListener('tap', function (event) {
                picker1.show(function (items) {
                    //						cityResult3.innerText = "你选择的城市是:" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
                    doc.getElementById('eatPicker').innerText = _getParam(items[0], 'text');

                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
        });
    })(mui, document);
</script>





</body>

</html>
