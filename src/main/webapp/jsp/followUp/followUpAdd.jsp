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
            <h1 class="mui-title">添加跟进记录</h1>
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
            <label>省</label> <input type="text" placeholder="" id="province">
        </div>
        <div class="mui-input-row">
            <label>市</label> <input type="text" placeholder="" id="city">
        </div>
        <div class="mui-input-row">
            <label>地址</label> <input type="text" placeholder="" id="address">
        </div>
        <p>定位信息</p>

        <div class="mui-input-row">
            <label>定位</label> <input type="text" placeholder="" id="nowAddress" readonly="true">

        </div>

        <p> 联系人1</p>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="请输入联系人1" id="contacts_1">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="请输入联系电话1" id="tel_1">
        </div>
        <div class="mui-input-row">
            <label>部门</label> <input type="text" placeholder="请输入联系人1部门" id="department_1">
        </div>
        <div class="mui-input-row">
            <label>职务</label> <input type="text" placeholder="请输入联系人1职务" id="post_1">
        </div>
        <div class="mui-input-row">
            <label>聊天账号</label> <input type="text" placeholder="请输入联系人1聊天账号" id="chart_1">
        </div>
        <p> 联系人2</p>
        <div class="mui-input-row">
            <label>名称</label> <input type="text" placeholder="请输入联系人2" id="contacts_2">
        </div>
        <div class="mui-input-row">
            <label>联系电话</label> <input type="text" placeholder="请输入联系电话2" id="tel_2">
        </div>
        <div class="mui-input-row">
            <label>部门</label> <input type="text" placeholder="请输入联系人2部门" id="department_2">
        </div>
        <div class="mui-input-row">
            <label>职务</label> <input type="text" placeholder="请输入联系人2职务" id="post_2">
        </div>
        <div class="mui-input-row">
            <label>聊天账号</label> <input type="text" placeholder="请输入联系人2聊天账号" id="chart_2">
        </div>
        <p>其他信息</p>
        <div class="mui-input-row">
            <label>固话 </label> <input type="text" placeholder="请输入固话" id="tel">
        </div>
        <div class="mui-input-row">
            <label>电子邮件</label> <input type="text" placeholder="请输入电子邮箱" id="email">
        </div>
        <div class="mui-input-row">
            <label>网址</label> <input type="text" placeholder="请输入公司主页" id="web">
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
                <input name="checkbox" value="房地产商" type="checkbox">
            </div>
            <div class="mui-input-row mui-checkbox mui-left">
                <label>门窗幕墙厂</label>
                <input name="checkbox" value="门窗幕墙厂" type="checkbox">
            </div>
            <div class="mui-input-row mui-checkbox mui-left ">
                <label>玻璃厂</label>
                <input name="checkbox" value="玻璃厂" type="checkbox">
            </div>
        </form>
    </div>

    <h5 class="mui-content-padded">跟进记录</h5>
    <div class="mui-input-row" style="margin: 10px 5px;">
        <textarea id="record" rows="5" placeholder="请输入跟进记录"></textarea>
    </div>

    <h5>同时选择上传1-4张照片，第一张为封面图</h5>
    <div class="photos">
        <div class="photosInput">
            <button id='chooseimgDiv' type="button"  onclick="ChoosePhoto()" class="mui-btn mui-btn-success" style="width: 100%;">选择图片</button>
            <div id="dd" style="margin-top:15px;margin-left: 1%;margin-right: 1%;height: 350px;border: 1px solid #8D8D8D " ></div>
            <input type="hidden" id ="imgId"/>

        </div>
    </div>
    <div class="mui-content-padded" style="margin-top: 20px;">
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">提交跟进记录</button>
    </div>

</div>

</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.15&key=026415b8165d5b4fabe82bc9a253e96a&plugin=AMap.Geocoder"></script>
<!-- 微信jssdk注入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<script>
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appId}', // 必填，企业号的唯一标识，此处填写企业号corpid
        timestamp: parseInt("${timestamp}", 10), // 必填，生成签名的时间戳
        nonceStr: '${noncestr}', // 必填，生成签名的随机串
        signature: '${signature}',// 必填，签名，见附录1
        jsApiList: ['getLocation', 'openLocation', 'chooseImage','uploadImage','previewImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        wx.getLocation({
            type: 'gcj02',
            success: function (res) {
                var latitude = res.latitude
                var longitude = res.longitude
                $.ajax({
                    url: 'https://restapi.amap.com/v3/geocode/regeo?output=JSON&location=' + longitude + ',' + latitude + '&key=026415b8165d5b4fabe82bc9a253e96a',
                    //url: 'https://restapi.amap.com/v3/geocode/regeo?output=JSON&location=116.39,39.9&key=026415b8165d5b4fabe82bc9a253e96a',
                    type: 'GET',
                    async: false, //设置同步。ajax默认异步
                    dataType: 'jsonp',
                    jsonp: 'callback', //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
                    jsonpCallback: "callback", //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
                    timeout: 5000,
                    contentType: 'application/json; charset=utf-8',
                    success: function (result) {
                        var obj = eval(result);
                        $("#nowAddress").val(obj.regeocode.formatted_address);
                    }
                })
                // wx.openLocation({
                //     latitude,
                //     longitude,
                //     scale: 15
                // });
            },
            fail: function (error) {
                AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
            }
        });


    });
    wx.error(function (res) {
    });
</script>
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
<!--ajax获取地址信息-->
<script>
    function getAddressByAjax() {

        $.ajax({
            url: "/natergy-h5/followUp/getAddress",
            contentType: "application/x-www-form-urlencoded:charset=UTF-8",
            type: "get",
            dataType: "json",
            data: {
                "name": $("#searchedName").val()
            },
            success: function (data) {
                //var json = eval("(" + data + ")");
                $("#customerName").val($("#searchedName").val());
                $("#province").val(data.province);
                $("#city").val(data.city);
                $("#address").val(data.address);
            }
        });
    }
</script>

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

<!--
    作者：240232995@qq.com
    时间：2019-08-24
    描述：保存订单
-->
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {
        var cName = $("#customerName").val();
        if(""==cName){
            mui.toast('客户名为空，请关联客户名...');
            return;
        }
        var chk_value = [];//定义一个数组
        $('input[name="checkbox"]:checked').each(function () {//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中    
        });
        var img_alt=[]
        $("#photos img").each(function(){
            img_alt.push($(this).attr("alt"));
        });
        var imgs=[];
        var str=$("#imgId").val().split(",");
        for(i=0;i<str.length;i++){
            imgs.push(str[i]);
        }
        // arrayImgs=[]
        // $("#photos img").each(function () {
        //     alert($(this).attr("alt"));
        //     arrayImgs.push($(this).attr("alt"));
        // });
//			if("" == customerName || "" == consignee || "" == receivingAddress || null == producer) {
//				mui.toast('请完善订单信息...');
//				return;
//			}
        mui.toast('正在保存跟进记录...');
        $.ajax({
            url: "/natergy-h5/followUp/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "province": $("#province").val(),
                "city": $("#city").val(),
                "nowAddress": $("#nowAddress").val(),
                "customerName": $("#customerName").val(),
                "address": $("#address").val(),
                "contacts1": $("#contacts_1").val(),
                "contacts2": $("#contacts_2").val(),
                "tel1": $("#tel_1").val(),
                "tel2": $("#tel_2").val(),
                "department1": $("#department_1").val(),
                "department2": $("#department_2").val(),
                "post1": $("#post_1").val(),
                "post2": $("#post_2").val(),
                "tel": $("#tel").val(),
                "email": $("#email").val(),
                "web": $("#we").val(),
                "chart1": $("#chart_1").val(),
                "chart2": $("#chart_2").val(),
                "industry": $("#industryPicker").text(),
                //"images":img_alt,
                "images":imgs,
                "relation": chk_value.join("/"),
                "record": $("#record").val()
            }), success: function (data) {
                //alert("Data Loaded: " + data);
                if (1 == data) {
                    mui.toast('保存成功');
                    window.location.href = "/natergy-h5/followUp/init"
                } else {
                    mui.toast('订单保存失败，请稍后重试...');
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

<script>
        var imgA=new Array();
        var imgserverId;  //存储的图片拼接字符；<br>function ChoosePhoto(){
        function ChoosePhoto(){
        wx.chooseImage({
            count: 9, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                document.getElementById("dd").innerHTML = "";
                imgA = [];
                imgserverId = "";
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                var htmlPhoto = "";
                for (var i = 0; i < localIds.length; i++) {
                    htmlPhoto += '<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src=' + localIds[i] + ' />';
                }
                syncUpload(localIds)
                document.getElementById("dd").innerHTML += htmlPhoto;
                preview();
            }
        });
    };
    var syncUpload = function(localIds){
        var localId = localIds.pop();
        wx.uploadImage({
            localId: localId.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                //res.serverId 返回图片的服务器端ID
                var serverId = res.serverId; // 返回图片的服务器端ID
                imgA.push(serverId)
                imgserverId=imgA;
                if(localIds.length > 0){
                    window.setTimeout(function(){
                        syncUpload(localIds);
                    },500);
                }else{
                    window.setTimeout(function(){
                        $("#imgId").val(imgA);
                    },500);

                }
            }
        })
    }
    function preview(){
        var imgs = [];
        var imgObj = $("#dd img");//这里改成相应的对象
        for (var i = 0; i < imgObj.length; i++) {
            imgs.push(imgObj.eq(i).attr('src'));
            imgObj.eq(i).click(function () {
                var nowImgurl = $(this).attr('src');
                WeixinJSBridge.invoke("imagePreview", {
                    "urls": imgs,
                    "current": nowImgurl
                });
            });
        }
    }
</script>
</body>
</html>