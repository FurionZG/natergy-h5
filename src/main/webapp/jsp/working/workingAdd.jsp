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
            <h1 class="mui-title">添加工作进程</h1>
            <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        </header>

    </div>

    <div style="padding-top: 30px">
        <input type="hidden" id="id">
        <label>日期</label>
        <button id="date" data-options='{"type":"date","beginYear":2019,"endYear":2022}'
                class="btn mui-btn mui-btn-block">选择日期 ...
        </button>

        <label>今日工作</label>
        <div class="mui-input-row" style="margin: 10px 5px;">
            <textarea id="todayWorking" rows="6" placeholder="请输入今日工作"></textarea>
        </div>


        <label>明日工作</label>
        <div class="mui-input-row" style="margin: 10px 5px;">
            <textarea id="tomorrowWorking" rows="6" placeholder="请输入明日工作"></textarea>
        </div>
        <label>备注</label>
        <div class="mui-input-row">
            <input type="text" placeholder="请输入备注" id="marks">
        </div>


        <h5>同时选择上传1-4张照片，第一张为封面图</h5>
        <div class="photos">
            <div class="photosInput">
                <button id='chooseimgDiv' type="button" onclick="ChoosePhoto()" class="mui-btn mui-btn-success"
                        style="width: 100%;">选择图片
                </button>
                <div id="dd"
                     style="margin-top:15px;margin-left: 1%;margin-right: 1%;height: 350px;border: 1px solid #8D8D8D "></div>
                <input type="hidden" id="imgId"/>

            </div>
        </div>
        <div class="mui-content-padded" style="margin-top: 20px;">
            <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">保存工作进程
            </button>
        </div>

    </div>
</div>
</body>


<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/update.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.min.js"></script>

<!-- 微信jssdk注入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>

<script>
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appId}', // 必填，企业号的唯一标识，此处填写企业号corpid
        timestamp: parseInt("${timestamp}", 10), // 必填，生成签名的时间戳
        nonceStr: '${noncestr}', // 必填，生成签名的随机串
        signature: '${signature}',// 必填，签名，见附录1
        jsApiList: ['chooseImage', 'uploadImage', 'previewImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
    });
    wx.error(function (res) {
    });
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


        var img_alt = []
        $("#photos img").each(function () {
            img_alt.push($(this).attr("alt"));
        });
        var imgs = [];
        var str = $("#imgId").val().split(",");
        for (i = 0; i < str.length; i++) {
            imgs.push(str[i]);
        }

        mui.toast('正在保存工作进程...');
        $.ajax({
            url: "/natergy-h5/working/save",
            contentType: "application/json;charset=utf-8",
            type: "post",
            dataType: "json",
            data: JSON.stringify({
                "id":$("#id").val(),
                "date": $("#date").text(),
                "todayWorking": $("#todayWorking").val(),
                "tomorrowWorking": $("#tomorrowWorking").val(),
                "marks": $("#marks").val(),
                "images": imgs,
            }), success: function (data) {
                //alert("Data Loaded: " + data);
                if (1 == data) {
                    mui.toast('保存成功');
                    window.location.href = "/natergy-h5/working/init"
                } else {
                    mui.toast('工作进程保存失败，请稍后重试...');
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
    var imgA = new Array();
    var imgserverId;  //存储的图片拼接字符；<br>function ChoosePhoto(){
    function ChoosePhoto() {
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


    var syncUpload = function (localIds) {
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
                        $("#imgId").val(imgA);
                    }, 500);

                }
            }
        })
    }

    function preview() {
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
