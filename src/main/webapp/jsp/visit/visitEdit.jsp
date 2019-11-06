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
    <link href="https://cdn.bootcss.com/mui/3.7.1/css/mui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">

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
            <h1 class="mui-title">修改拜访记录</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
            <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 30%;float: right">修改
            </button>
        </header>

    </div>
    <form  class="mui-input-group" style="margin: 50px 5px 0px 5px; border-radius: 10px;">
        <input type="hidden" id = "id">
        <hr>
        <h5>出差开始时间${businessStartTime}</h5>
        <h5>出差编号${businessNo}</h5>
        <div class="mui-input-row">
            <label>客户名称</label> <input type="text" placeholder="" id="customerName" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>省</label> <input type="text" placeholder="" id="province">
        </div>
        <div class="mui-input-row">
            <label>市</label> <input type="text" placeholder="" id="city">
        </div>
        <div class="mui-input-row">
            <label>地址</label> <input type="text" placeholder="" id="address">
        </div>


        <h5> 联系人1</h5>
        <div class="mui-input-row">
            <label>联系人</label> <input type="text" placeholder="请输入联系人1" id="contacts_1">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="请输入联系电话1" id="tel_1">
        </div>
        <h5> 联系人2</h5>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="请输入联系人2" id="contacts_2">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="请输入联系电话2" id="tel_2">
        </div>
        <h5> 联系人3</h5>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="请输入联系人3" id="contacts_3">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="请输入联系电话3" id="tel_3">
        </div>


    </form>
    <h5 class="mui-content-padded">其他信息</h5>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='productBrandPicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">干燥剂品牌
        </button>
        <input type="hidden" id="productBrand"/>
    </div>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='productTypePicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">干燥剂类型
        </button>
        <input type="hidden" id="productType"/>
    </div>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='consumptionPicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">年用量
        </button>
        <input type="hidden" id="consumption"/>
    </div>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='customerTypePicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">客户类型
        </button>
        <input type="hidden" id="customerType"/>
    </div>
    <div style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <button id='pricePicker' class="mui-btn mui-btn-block" type='button'
                style="width: 100%; text-align: center;">单价
        </button>
        <input type="hidden" id="price"/>
    </div>


    <h5 class="mui-content-padded">拜访记录</h5>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <textarea id="record" rows="5" placeholder="请输入拜访记录"></textarea>
    </div>
    <h5>图片附件</h5>
    <div class="photos">
        <div class="photosInput">
            <div id="dd" style="margin-top:15px;margin-left: 1%;margin-right: 1%;height: 350px;border: 1px solid #8D8D8D " ></div>
        </div>
    </div>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='delete' type="button" class="mui-btn mui-btn-danger" style="width: 100%;">删除跟进记录</button>
    </div>

</div>

</body>


<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>

<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>

<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>




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
        mui.toast('正在保存拜访记录...');

        $.ajax({
            url: "/natergy-h5/visit/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id":$("#id").val(),
                "contacts1": $("#contacts_1").val(),
                "contacts2": $("#contacts_2").val(),
                "tel1": $("#tel_1").val(),
                "tel2": $("#tel_2").val(),
                "contacts3": $("#contacts_3").val(),
                "tel3": $("#tel_3").val(),
                "record": $("#record").val(),
                "productBrand":$("#productBrandPicker").text(),
                "productType":$("#productTypePicker").text(),
                "consumption":$("#consumptionPicker").text(),
                "customerType":$("#customerTypePicker").text(),
                "price":$("#pricePicker").text(),
            }), success: function (data) {
                if(1==data){
                    mui.toast('修改成功');
                    window.location.href="/natergy-h5/visit/init"
                }else{
                    mui.toast('出差拜访修改失败，请稍后重试...');
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
            var json =eval("("+localStorage.getItem("visit")+")");
            console.log(json);
            doc.getElementById('id').value=json.id;
            doc.getElementById('customerName').value=json.customerName;
            doc.getElementById('province').value=json.province;
            doc.getElementById('city').value=json.city;
            doc.getElementById('address').value=json.address;
            doc.getElementById('contacts_1').value=json.contacts1;
            doc.getElementById('tel_1').value=json.tel1;
            doc.getElementById('contacts_2').value=json.contacts2;
            doc.getElementById('tel_2').value=json.tel2;
            doc.getElementById('contacts_3').value=json.contacts3;
            doc.getElementById('tel_3').value=json.tel3;
            doc.getElementById('productBrandPicker').innerText =json.productBrand;
            doc.getElementById('productTypePicker').innerText =json.productType;
            doc.getElementById('consumptionPicker').innerText =json.consumption;
            doc.getElementById('customerTypePicker').innerText =json.customerType;
            doc.getElementById('pricePicker').innerText =json.price;
            doc.getElementById('record').value=json.record;

            var htmlPhoto = "";
            for(var i=0;i<json.images.length;i++){
                htmlPhoto+='<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src="http://219.146.150.102:20005/'+json.images[i]+'"/>';
            }
            document.getElementById("dd").innerHTML += htmlPhoto;



            var _getParam = function (obj, param) {
                return obj[param] || '';
            };
            var picker = new $.PopPicker();
            picker.setData(productBrand);
            var productBrandPickerButton = doc.getElementById('productBrandPicker');
            productBrandPickerButton.addEventListener('tap', function (event) {
                picker.show(function (items) {
                    doc.getElementById('productBrandPicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            var picker1 = new $.PopPicker();
            picker1.setData(productType);
            var productTypePickerButton = doc.getElementById('productTypePicker');
            productTypePickerButton.addEventListener('tap', function (event) {
                picker1.show(function (items) {
                    doc.getElementById('productTypePicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            var picker2 = new $.PopPicker();
            picker2.setData(consumption);
            var consumptionPickerButton = doc.getElementById('consumptionPicker');
            consumptionPickerButton.addEventListener('tap', function (event) {
                picker2.show(function (items) {
                    doc.getElementById('consumptionPicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            var picker3 = new $.PopPicker();
            picker3.setData(customerType);
            var customerTypePickerButton = doc.getElementById('customerTypePicker');
            customerTypePickerButton.addEventListener('tap', function (event) {
                picker3.show(function (items) {
                    doc.getElementById('customerTypePicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);
            var picker4 = new $.PopPicker();
            picker4.setData(price);
            var pricePickerButton = doc.getElementById('pricePicker');
            pricePickerButton.addEventListener('tap', function (event) {
                picker4.show(function (items) {
                    doc.getElementById('pricePicker').innerText = _getParam(items[0], 'text');
                    //返回 false 可以阻止选择框的关闭
                    //return false;
                });
            }, false);

        });
    })(mui, document);
</script>
<script>
    var btnSave = document.getElementById("delete");
    btnSave.addEventListener("tap", function () {
        $.ajax({
            url: "/natergy-h5/visit/delete",
            contentType: "application/json;charset=utf-8",
            type: "get",
            dataType: "json",
            data: {
                "id" :$("#id").val()
            }, success: function (data) {
                if(1==data){
                    mui.toast('删除成功');
                    window.location.href="/natergy-h5/visit/init"
                }else{
                    mui.toast('出差拜访删除失败，请稍后重试...');
                }
            }
        });
    });

</script>

</body>

</html>