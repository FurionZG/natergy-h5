<%--
  Created by IntelliJ IDEA.
  User: 杨枕戈
  Date: 2020-02-25
  Time: 16:01
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


<div class="mui-content-padded" style="margin: 15px;">
    <header class="mui-bar mui-bar-nav">
        <h1 class="mui-title">${today}订餐</h1>
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    </header>

</div>
<div class="mui-content">
    <form class="mui-input-group" style="margin: 50px 5px 0px 5px; border-radius: 10px;">
        <br>
        <h5>就餐区域：</h5>
        <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
            <button id='workspacePicker' class="mui-btn mui-btn-block" type='button'
                    style="width: 100%; text-align: center;">就餐区域
            </button>
            <input type="hidden" id="workspace"/>
        </div>
        <h5>订餐明细：</h5>
        <div class="mui-input-row mui-checkbox mui-left">
            <label>早餐</label> <input id="breakfast" type="checkbox" value="早餐" >
        </div>
        <div class="mui-input-row mui-checkbox mui-left">
            <label>午餐</label> <input id="lunch" type="checkbox" value="午餐">
        </div>
        <button id='lMantouPicker' class="mui-btn mui-btn-block" type='button'
                style="height: 50%;width: 100%; text-align: center;">午餐馒头
        </button>

        <div class="mui-input-row mui-checkbox mui-left">
            <label>晚餐</label> <input id="supper" type="checkbox" value="晚餐">
        </div>
        <button id='sMantouPicker' class="mui-btn mui-btn-block" type='button'
                style="height: 50%;width: 100%; text-align: center;">晚餐馒头
        </button>

        <div class="mui-input-row mui-checkbox mui-left">
            <label>夜餐</label> <input id="nightSnack" type="checkbox" value="夜餐">
        </div>
        <button id='nMantouPicker' class="mui-btn mui-btn-block" type='button'
                style="height: 50%;width: 100%; text-align: center;">夜餐馒头
        </button>

        <input id="remarks" type="text" class="mui-input-clear" placeholder="特殊情况备注">
        <hr>
        <input type="hidden" id="id">
    </form>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">修改</button>
    </div>
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
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {

        mui.toast('正在修改订餐信息...');
        $.ajax({
            url: "/natergy-h5/infactory/cook/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id":$("#id").val(),
                "team": $("#workspacePicker").text(),
                "breakfast": $("#breakfast").is(":checked")?"是":"否",
                "lunch": $("#lunch").is(":checked")?"是":"否",
                "lMantou": $("#lMantouPicker").text(),
                "supper": $("#supper").is(":checked")?"是":"否",
                "sMantou": $("#sMantouPicker").text(),
                "nightSnack": $("#nightSnack").is(":checked")?"是":"否",
                "nMantou": $("#nMantouPicker").text(),
                "remarks": $("#remarks").val(),
            }), success: function (data) {
                if (1 == data) {
                    mui.toast('提交成功');
                    window.location.href = "/natergy-h5/infactory/cook/listInit"
                } else if(-1 ==data) {
                    mui.toast("已超过订餐时间，请联系办公室或技术三部修改");
                } else{
                    mui.toast('提交失败，请稍后重试');
                }

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


            var picker2 = new $.PopPicker();
            picker2.setData(mantouNum);
            var lMantouPickerPickerButton = doc.getElementById('lMantouPicker');
            var sMantouPickerPickerButton = doc.getElementById('sMantouPicker');
            var nMantouPickerPickerButton = doc.getElementById('nMantouPicker');
            lMantouPickerPickerButton.addEventListener('tap', function (event) {
                picker2.show(function (items) {
                    doc.getElementById('lMantouPicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            sMantouPickerPickerButton.addEventListener('tap', function (event) {
                picker2.show(function (items) {
                    doc.getElementById('sMantouPicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            nMantouPickerPickerButton.addEventListener('tap', function (event) {
                picker2.show(function (items) {
                    doc.getElementById('nMantouPicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);

            let cook=localStorage.getItem("cook")
            let json = eval("("+cook+")");
            document.getElementById("id").value=json.id;
            document.getElementById("workspacePicker").innerText=json.team;
            document.getElementById("lMantouPicker").innerText=json.lMantou;
            document.getElementById("sMantouPicker").innerText=json.sMantou;
            document.getElementById("nMantouPicker").innerText=json.nMantou;
            document.getElementById("remarks").value=json.remarks.replace("三点后补订的餐","").trim();
            if("是"==json.breakfast){
                document.getElementById("breakfast").setAttribute("checked",true);
            }
            if("是"==json.lunch){
                document.getElementById("lunch").setAttribute("checked",true);
            }
            if("是"==json.supper){
                document.getElementById("supper").setAttribute("checked",true);
            }
            if("是"==json.nightSnack){
                document.getElementById("nightSnack").setAttribute("checked",true);
            }

        });
    })(mui, document);
</script>





</body>

</html>
