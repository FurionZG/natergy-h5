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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.picker.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />


    <style>
        h5 {
            margin: 5px 7px;
        }
    </style>
</head>

<body>


<div class="mui-content-padded" style="margin: 15px;">
    <header class="mui-bar mui-bar-nav">
        <h1 class="mui-title">修改出差记录</h1>
        <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 30%;float: right">修改
        </button>
    </header>

</div>
<div class="mui-content"  >
    <form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
        <input type="hidden" id="id">
        <hr>
        <h5>基本信息</h5>
        <div class="mui-input-row">
            <label>出差编号</label> <input type="text" placeholder="" id="businessNo" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>状态</label> <input type="text" placeholder="" id="status" readonly="true">
        </div>
        <h5>起始日期</h5> <button id="startDate" data-options='{"value":"2019-10-10 10:10","beginYear":2019,"endYear":2020}' class="btn mui-btn mui-btn-block">选择起始时间 ...</button>
        <h5>终止日期</h5> <button id="endDate" data-options='{"value":"2019-10-10 10:10","beginYear":2019,"endYear":2020}' class="btn mui-btn mui-btn-block">选择终止时间 ...</button>
        <h5>统计信息</h5>
        <div class="mui-input-row">
            <label>天数</label> <input type="text" placeholder="" id="time" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>拜访客户数</label> <input type="text" placeholder="" id="visitCustomerCount" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>新户数</label> <input type="text" placeholder="" id="visitNewCustomerCount" readonly="true">
        </div>
        <div class="mui-input-row">
            <label>起始里程</label> <input type="text" placeholder="" id="startMileage">
        </div>
        <h5>上传起始终止里程照片</h5>
        <button id='startImg' type="button" class="mui-btn mui-btn-success" onclick="ChoosePhoto(this);" style="width: 100%">上传</button>
            <div id ="dd1"></div>
        <input type="hidden" id ="startSrc">
        <div class="mui-input-row">
            <label>终止里程</label> <input type="text" placeholder="" id="endMileage">
        </div>
        <h5>上传起始终止里程照片</h5>
        <button id='endImg' type="button" class="mui-btn mui-btn-success" onclick="ChoosePhoto(this);" style="width: 100%">上传</button>
        <div id ="dd2"></div>
        <input type="hidden" id ="endSrc">
        <div class="mui-input-row">
            <label>公里数</label> <input type="text" placeholder="" id="Mileage">
        </div>
        <div class="mui-input-row">
            <label>过路费</label> <input type="text" placeholder="" id="roadToll">
        </div>
        <div class="mui-input-row">
            <label>加油费</label> <input type="text" placeholder="" id="fuelCosts">
        </div>
        <div class="mui-input-row">
            <label>加油数</label> <input type="text" placeholder="" id="fuelVolume">
        </div>
        <div class="mui-input-row">
            <label>油耗</label> <input type="text" placeholder="" id="oilConsumption">
        </div>
        <div class="mui-input-row">
            <label>车票</label> <input type="text" placeholder="" id="ticket">
        </div>
        <h5>补贴</h5>
        <div class="mui-input-row">
            <label>特补</label> <input type="text" placeholder="" id="specialSubsidies">
        </div>
        <div class="mui-input-row">
            <label>特补说明</label> <input type="text" placeholder="请输入特补说明" id="specialSubsidiesDescription">
        </div>
        <div class="mui-input-row">
            <label>餐补</label> <input type="text" placeholder="" id="mealAllowance">
        </div>
        <div class="mui-input-row">
            <label>住宿</label> <input type="text" placeholder="" id="accommodation">
        </div>
        <hr>
        <div class="mui-input-row">
            <label>本次花费</label> <input type="text" placeholder="" id="totalCosts">
        </div>
        <div class="mui-input-row">
            <label>客情费</label> <input type="text" placeholder="" id="customerFee">
        </div>
        <div class="mui-input-row">
            <label>行程</label> <input type="text" placeholder="请输入行程" id="trip">
        </div>
        <div class="mui-input-row">
            <label>总结</label> <input type="text" placeholder="请输入总结" id="summary">
        </div>
        <div class="mui-input-row">
            <label>建议</label> <input type="text" placeholder="请输入建议" id="proposal">
        </div>
    </form>
    <h5>拜访记录</h5>
    <div class="mui-scroll" style="width: 100%;" >
        <form id="form-country_v1" name="form-country_v1" style="margin-top: 5px;">
            <div class="typeahead__container">
                <div class="typeahead__field">
                    <div class="typeahead__query">
                        <input class="js-typeahead-country_v1" name="country_v1[query]" type="search"
                               placeholder="点我输入" autocomplete="off" id="searchedName">
                    </div>
                    <div class="typeahead__button">
                        <button type="button" onclick="getVisitByAjax()">
                            <i class="typeahead__search-icon"></i>
                        </button>
                    </div>
                </div>

            </div>

        </form>
        <!--数据列表-->
        <ul class="mui-table-view mui-table-view-chevron" id="visitList">

        </ul>

    </div>


</div>

</body>


<script src="https://cdn.bootcss.com/mui/3.7.1/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<script>
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: localStorage.getItem("appId"), // 必填，企业号的唯一标识，此处填写企业号corpid
        timestamp: localStorage.getItem("timestamp"), // 必填，生成签名的时间戳
        nonceStr: localStorage.getItem("nonceStr"), // 必填，生成签名的随机串
        signature: localStorage.getItem("signature"),// 必填，签名，见附录1
        jsApiList: ['chooseImage', 'uploadImage', 'previewImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
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

<script>
    var imgA = new Array();
    var imgserverId;  //存储的图片拼接字符；<br>function ChoosePhoto(){
    function ChoosePhoto(obj) {
        var id = obj.getAttribute("id");
        var imgDiv="";
        var imgSrc="";
        if("startImg"==id){
            imgDiv="dd1";
            imgSrc="startSrc";
        }else {
            imgDiv="dd2";
            imgSrc="endSrc";
        }
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                document.getElementById(imgDiv).innerHTML = "";
                imgA = [];
                imgserverId = "";
                var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                var htmlPhoto = "";
                for (var i = 0; i < localIds.length; i++) {
                    htmlPhoto += '<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src=' + localIds[i] + ' />';
                }
                syncUpload(localIds,imgSrc)
                document.getElementById(imgDiv).innerHTML += htmlPhoto;
                preview(imgDiv);
            }
        });
    };
    var syncUpload = function (localIds,imgSrc) {
        var localId = localIds.pop();
        wx.uploadImage({
            localId: localId.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                //res.serverId 返回图片的服务器端ID
                var serverId = res.serverId; // 返回图片的服务器端ID
                imgA.push(serverId)
                imgserverId = imgA;
                if (localIds.length > 0) {
                    window.setTimeout(function () {
                        syncUpload(localIds);
                    }, 500);
                } else {
                    window.setTimeout(function () {
                        $("#"+imgSrc).val(imgA);
                    }, 500);

                }
            }
        })
    }

    function preview(id) {
        var imgs = [];
        var imgObj = $("#"+id+" img");//这里改成相应的对象
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
<!--修改跟进记录-->
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {


        mui.toast('正在保存出差记录...');

        $.ajax({
            url: "/natergy-h5/business/update",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id": $("#id").val(),
                "startDate": $("#startDate").text(),
                "endDate": $("#endDate").text(),
                "startMileage": $("#startMileage").val(),
                "endMileage": $("#endMileage").val(),
                "mileage": $("#Mileage").val(),
                "roadToll": $("#roadToll").val(),
                "fuelCosts": $("#fuelCosts").val(),
                "fuelVolume": $("#fuelVolume").val(),
                "ticket": $("#ticket").val(),
                "specialSubsidies": $("#specialSubsidies").val(),
                "specialSubsidiesDescription": $("#specialSubsidiesDescription").val(),
                "mealAllowance": $("#mealAllowance").val(),
                "accommodation": $("#accommodation").val(),
                "totalCosts": $("#totalCosts").val(),
                "customerFee": $("#customerFee").val(),
                "trip": $("#trip").val(),
                "summary": $("#summary").val(),
                "proposal": $("#proposal").val(),
                "startImage":$("#startSrc").val(),
                "endImage":$("#endSrc").val()
            }), success: function (data) {
                if (1 == data) {
                    mui.toast('修改成功');
                    window.location.href = "/natergy-h5/business/init"
                } else {
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
            var json = eval("(" + localStorage.getItem("business") + ")");
            doc.getElementById('id').value = json.id;
            doc.getElementById('businessNo').value = json.businessNo;
            doc.getElementById('startDate').innerHTML = json.startDate;
            doc.getElementById('status').value = json.status;
            doc.getElementById('endDate').innerHTML = json.endDate;
            doc.getElementById('time').value = json.time;
            doc.getElementById('visitCustomerCount').value = json.visitCustomerCount;
            doc.getElementById('visitNewCustomerCount').value = json.visitNewCustomerCount;
            doc.getElementById('startMileage').value = json.startMileage;
            doc.getElementById('endMileage').value = json.endMileage;
            doc.getElementById('Mileage').value = json.mileage;
            doc.getElementById('roadToll').value = json.roadToll;
            doc.getElementById('fuelCosts').value = json.fuelCosts;
            doc.getElementById('fuelVolume').value = json.fuelVolume;
            doc.getElementById('oilConsumption').value = json.oilConsumption;
            doc.getElementById('ticket').value = json.ticket;
            doc.getElementById('specialSubsidies').value = json.specialSubsidies;
            doc.getElementById('specialSubsidiesDescription').value = json.specialSubsidiesDescription;
            doc.getElementById('mealAllowance').value = json.mealAllowance;
            doc.getElementById('accommodation').value = json.accommodation;
            doc.getElementById('totalCosts').value = json.totalCosts;
            doc.getElementById('customerFee').value = json.customerFee;
            doc.getElementById('trip').value = json.trip;
            doc.getElementById('summary').value = json.summary;
            doc.getElementById('proposal').value = json.proposal;


            var startPhoto = '<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src="http://219.146.150.102:20005/' + json.startImage + '"/>';
            document.getElementById("dd1").innerHTML = startPhoto;
            var endPhoto = '<img style="width: 150px;height: 150px; margin-left: 15px;margin-top: 15px;" src="http://219.146.150.102:20005/' + json.endImage + '"/>';
            document.getElementById("dd2").innerHTML = endPhoto;

            $.ajax({
                url: "/natergy-h5/visit/selectByBusinessNo",
                contentType: "application/json;charset=utf-8",
                type: "post",
                dataType: "json",
                data: jQuery("#businessNo").val(),
                success: function (data) {
                    var json = eval(data);
                    jQuery("#limit").val(10);
                    for (var i = 0; i < json.length; i++) { //循环数据
                        jQuery("#visitList").append("<li class='mui-table-view-cell mui-media' style='height:55px' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                            json[i].customerName +
                            "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                            "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                    }

                    mui('body').on('tap', '#visitList li', function () {
                        clickLi(this)
                    });
                }
            });
            var allName = "";
            $.ajax({
                url: "/natergy-h5/visit/getVisitCustomerNameByBusinessNo",
                contentType: "application/json;charset=utf-8",
                type: "post",
                data: jQuery("#businessNo").val(),
                dataType: "json",
                success: function (data) {
                    initSearch(data)
                }
            });
        });
    })(mui, document);

    function initSearch(allName) {
        jQuery.typeahead({
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
    }
</script>
<script>
    var btns = mui('.btn');
    btns.each(function(i, btn) {
        btn.addEventListener('tap', function() {
            var _self = this;
            if(_self.picker) {
                _self.picker.show(function (rs) {
                    btns[i].innerText =  rs.y.text+"年"+rs.m.text+"月"+rs.d.text+"日 "+rs.h.text+"时"+rs.i.text+"分";
                    btns[i].setAttribute("data-options",'{"value":"'+rs.y.text+'-'+rs.m.text+'-'+rs.d.text+' '+rs.h.text+':'+rs.i.text+'","beginYear":2019,"endYear":2020}')
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
                    btns[i].innerText =  rs.y.text+"年"+rs.m.text+"月"+rs.d.text+"日 "+rs.h.text+"时"+rs.i.text+"分";
                    btns[i].setAttribute("data-options",'{"value":"'+rs.y.text+'-'+rs.m.text+'-'+rs.d.text+' '+rs.h.text+':'+rs.i.text+'","beginYear":2019,"endYear":2020}')
                    _self.picker.dispose();
                    _self.picker = null;
                });
            }

        }, false);
    });
</script>
<script>
    function getVisitByAjax() {
        $.ajax({
            url: "/natergy-h5/visit/getVisitByAjax",
            contentType: "application/json;charset=utf-8",
            type: "get",
            data: {
                "customerName": jQuery("#searchedName").val(),
                "businessNo": jQuery("#businessNo").val()
            },
            dataType: "json",
            success: function (data) {
                var json = eval(data);
                $("#visitList li").remove();
                for (var i = 0; i < json.length; i++) { //循环数据
                    jQuery("#visitList").append("<li class='mui-table-view-cell mui-media' style='height:55px' ><a class='mui-navigate-right'><div class='mui-media-body'>" +
                        json[i].customerName +
                        "<p class='mui-ellipsis'>" + json[i].date + "</p>" +
                        "<input type='hidden' value ='" + JSON.stringify(json[i]) + "'/></div></a></li>");
                }
            }
        });

    }
</script>
<script>
    function clickLi(obj) {

        localStorage.setItem("visit", $(obj).find("input:hidden").val());
        window.location.href = "<%=request.getContextPath()%>/jsp/visit/visitEdit.jsp"
    }
</script>


</body>

</html>