<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">
    -
    <link href="<%=request.getContextPath()%>/css/mui.picker.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/mui.poppicker.css" rel="stylesheet"/>


    <style>
        h5 {
            margin: 5px 7px;
        }
    </style>
</head>

<body>
<div class="mui-content">


    <div class="mui-content-padded" style="margin: 15px;">
        <header class="mui-bar mui-bar-nav">
            <h1 class="mui-title">修改跟进记录</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>

    </div>
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <input type="hidden" id ="id">
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text" placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>省</label> <input type="text" placeholder="" id="province" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>市</label> <input type="text" placeholder="" id="city" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>地址</label> <input type="text" placeholder="" id="address" readonly="true">
        </div>
        <p>定位信息</p>

        <div class="mui-input-row">
            <label>定位</label> <input type="text" placeholder="" id="nowAddress" readonly="true">

        </div>

        <p> 联系人1</p>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="" id="contacts_1">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="" id="tel_1">
        </div>
        <div class="mui-input-row">
            <label>部门</label> <input type="text" placeholder="" id="department_1">
        </div>
        <div class="mui-input-row">
            <label>职务</label> <input type="text" placeholder="" id="post_1">
        </div>
        <div class="mui-input-row">
            <label>聊天账号</label> <input type="text" placeholder="" id="chart_1">
        </div>
        <p> 联系人2</p>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="" id="contacts_2">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="" id="tel_2">
        </div>
        <div class="mui-input-row">
            <label>部门</label> <input type="text" placeholder="" id="department_2">
        </div>
        <div class="mui-input-row">
            <label>职务</label> <input type="text" placeholder="" id="post_2">
        </div>
        <div class="mui-input-row">
            <label>聊天账号</label> <input type="text" placeholder="" id="chart_2">
        </div>
        <p>其他信息</p>
        <div class="mui-input-row">
            <label>固话 </label> <input type="text" placeholder="" id="tel">
        </div>
        <div class="mui-input-row">
            <label>电子邮件</label> <input type="text" placeholder="" id="email">
        </div>
        <div class="mui-input-row">
            <label>网址</label> <input type="text" placeholder="" id="web">
        </div>


    </form>
    <h5 class="mui-content-padded">行业</h5>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='industryPicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">选择行业
        </button>
        <input type="hidden" id="industry"/>
    </div>

    <h5 class="mui-content-padded">影响关联</h5>
    <div class="mui-card">
        <form class="mui-input-group">
            <div class="mui-input-row mui-checkbox mui-left">
                <label>房地产商</label>
                <input name="checkbox" value="房地产商" type="checkbox" id="checkbox1">
            </div>
            <div class="mui-input-row mui-checkbox mui-left">
                <label>门窗幕墙厂</label>
                <input name="checkbox" value="门窗幕墙厂" type="checkbox" id="checkbox2">
            </div>
            <div class="mui-input-row mui-checkbox mui-left ">
                <label>玻璃厂</label>
                <input name="checkbox" value="玻璃厂" type="checkbox" id="checkbox3" >
            </div>
        </form>
    </div>

    <h5 class="mui-content-padded">跟进记录</h5>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <textarea id="record" rows="5" placeholder="跟进记录"></textarea>
    </div>
    <h5>图片附件</h5>
    <div class="photos">
        <div class="photosInput">
            <div id="dd" style="margin-top:15px;margin-left: 1%;margin-right: 1%;height: 350px;border: 1px solid #8D8D8D " ></div>
        </div>
    </div>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">修改跟进记录</button>
    </div>

</div>

</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>

<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>

<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>




<script>
    mui.init({
        swipeBack: false
        //关于右滑关闭功能
    });
    //语音识别完成事件
    //			document.getElementById("search").addEventListener('recognized', function(e) {
    //				console.log(e.detail.value);
    //			});

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




<!--修改跟进记录-->
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {


        var chk_value = [];//定义一个数组
        $('input[name="checkbox"]:checked').each(function () {//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
        });


//			if("" == customerName || "" == consignee || "" == receivingAddress || null == producer) {
//				mui.toast('请完善订单信息...');
//				return;
//			}
        mui.toast('正在保存跟进记录...');

        $.ajax({
            url: "/natergy-h5/followUp/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id":$("#id").val(),
                "province": $("#province").val(),
                "city": $("#city").val(),
                "nowAddress": $("#nowAddress").val(),
                "customerName": $("#customerName").val(),
                "address": $("#address").val(),
                "contacts_1": $("#contacts_1").val(),
                "contacts_2": $("#contacts_2").val(),
                "tel_1": $("#tel_1").val(),
                "tel_2": $("#tel_2").val(),
                "department_1": $("#department_1").val(),
                "department_2": $("#department_2").val(),
                "post_1": $("#post_1").val(),
                "post_2": $("#post_2").val(),
                "tel": $("#tel").val(),
                "email": $("#email").val(),
                "web": $("#web").val(),
                "chart_1": $("#chart_1").val(),
                "chart_2": $("#chart_2").val(),
                "industry": $("#industryPicker").text(),
                "relation": chk_value.join("/"),
                "record": $("#record").val()
            }), success: function (data) {
                if(1==data){
                    mui.toast('修改成功');
                    window.location.href="/natergy-h5/followUp/init"
                }else{
                    mui.toast('订单修改失败，请稍后重试...');
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
            var json =eval("("+localStorage.getItem("value")+")");
            doc.getElementById('id').value=json.id;
            doc.getElementById('customerName').value=json.customerName;
            doc.getElementById('province').value=json.province;
            doc.getElementById('city').value=json.city;
            doc.getElementById('nowAddress').value=json.nowAddress;
            doc.getElementById('address').value=json.address;
            doc.getElementById('contacts_1').value=json.contacts_1;
            doc.getElementById('tel_1').value=json.tel_1;
            doc.getElementById('department_1').value=json.department_1;
            doc.getElementById('post_1').value=json.post_1;
            doc.getElementById('chart_1').value=json.chart_1;
            doc.getElementById('contacts_2').value=json.contacts_2;
            doc.getElementById('tel_2').value=json.tel_2;
            doc.getElementById('department_2').value=json.department_2;
            doc.getElementById('post_2').value=json.post_2;
            doc.getElementById('chart_2').value=json.chart_2;
            doc.getElementById('web').value=json.web;
            doc.getElementById('email').value=json.email;
            doc.getElementById('tel').value=json.tel;
            doc.getElementById('record').value=json.record;
            doc.getElementById('industryPicker').innerText =json.industry;

            var htmlPhoto = "";
            for(var i=0;i<json.images.length;i++){
                htmlPhoto+='<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src="http://219.146.150.102:20005/'+json.images[i]+'"/>';
            }
            document.getElementById("dd").innerHTML += htmlPhoto;

            if(-1!=json.relation.indexOf("房地产商")){
                doc.getElementById('checkbox1').checked=true;
            }
            if(-1!=json.relation.indexOf("门窗幕墙厂")){
                doc.getElementById('checkbox2').checked=true;
            }
            if(-1!=json.relation.indexOf("玻璃厂")){
                doc.getElementById('checkbox3').checked=true;
            }

            /**
             * 获取对象属性的值
             * 主要用于过滤三级联动中，可能出现的最低级的数据不存在的情况，实际开发中需要注意这一点；
             * @param {Object} obj 对象
             * @param {String} param 属性名
             */
            var _getParam = function (obj, param) {
                return obj[param] || '';
            };

            var picker = new $.PopPicker({
                layer: 2
            });

            picker.setData(industry_data);
            var industryPickerPickerButton = doc.getElementById('industryPicker');
            //				var cityResult3 = doc.getElementById('userResult');
            industryPickerPickerButton.addEventListener('tap', function (event) {
                picker.show(function (items) {
                    //						cityResult3.innerText = "你选择的城市是:" + _getParam(items[0], 'text') + " " + _getParam(items[1], 'text') + " " + _getParam(items[2], 'text');
                    doc.getElementById('industryPicker').innerText = _getParam(items[0], 'text') + "-" + _getParam(items[1], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);

        });
    })(mui, document);
</script>

</body>

</html>