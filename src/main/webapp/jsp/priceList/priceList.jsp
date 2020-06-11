<%--
  Created by IntelliJ IDEA.
  User: 四爷
  Date: 2019/12/6
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet">
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="<%=request.getContextPath()%>/css/mui.picker.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/mui.poppicker.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.typeahead.css">
</head>
<body>
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">报价单</h1>
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <a class="mui-icon mui-icon-right-nav mui-pull-right" href="#topPopover">···</a>
</header>
<div class="mui-content">
    <form id="form-country_v1" name="form-country_v1" style="margin-top: 10px;">
        <div class="typeahead__container">
            <div class="typeahead__field" style="">
                <div class="typeahead__query">
                    <input class="js-typeahead-country_v1" name="country_v1[query]" type="search"
                           placeholder="Search" autocomplete="off" id="searchedName">
                </div>
                <div class="typeahead__button">
                    <button type="button" onclick="nameInput()">
                        <i class="typeahead__search-icon"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>
<form class="mui-input-group" style="margin: 15px 5px 0px 5px; border-radius: 10px;">
    <div class="mui-input-row">
        <label>客户名称</label> <input type="text" placeholder="" id="customerName" >
    </div>
</form>
<div class="mui-content-padded" style="margin-top: 20px;">
    <button id='addPrice' type="button" class="mui-btn mui-btn-success" style="width: 100%;">添加明细</button>
</div>
<ul id ="entryList" class="mui-table-view"></ul>
<div class="mui-content-padded" style="margin-top: 20px;">
    <button id='id_btnSave' type="button" class="mui-btn mui-btn-success" style="width: 100%;">生成报价单</button>
</div>
</div>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.picker.js"></script>
<script src="<%=request.getContextPath()%>/js/mui.poppicker.js"></script>
<script src="<%=request.getContextPath()%>/js/industry_data.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/js/jquery.typeahead.js"></script>
<script>
    var liCount = 0;
    var btnSave = document.getElementById("addPrice");
    btnSave.addEventListener("tap", function () {
        if($("#entryList hr").length<3){
            html='<hr><div style=" border:2px solid greenyellow"><div class="box" style="margin: 15px 5px 0px 5px; border-radius: 10px;">\n' +
                '<div class="form-picker-container" id='+liCount+' data-layer="1" >'+
                '<button class="mui-btn mui-btn-block">请选择</button></div>' +
                '<div class="mui-input-row"><label>单价(元/公斤)</label> <input type="text" class="price"/></div>'+
                '</div></div>';
            $("#entryList").append(html);
            refreshPicker();
            liCount++;
        }else{
            mui.toast("最多可添加三条");
        }
        mui(this).button('loading');

        setTimeout(function () {
            mui(this).button('reset');
        }.bind(this),20);
    });
</script>
<script>
    var allName = ${nameSet}
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
    function nameInput(){
        var name = $("#searchedName").val();
        $("#customerName").val(name);

    }
</script>
<script>
    function refreshPicker(){
        $(".form-picker-container").each(function(){
            var that = $(this);
            var id = that.attr('id');
            var setData = that.attr('data-setData');
            that.off('tap');
            that.on('tap', function() {
                var picker = new mui.PopPicker();
                picker.setData(priceList);
                picker.show(function(items) {
                    //that.val(items[0].value);
                    $("#" + id+" button").text(items[0].text);
                    picker.dispose();
                });
            });
        });
    }
</script>
<script>
    var btnSave = document.getElementById("id_btnSave");
    btnSave.addEventListener("tap", function () {
        if($("#entryList button").length==0){
            mui.toast("您还没有生成报价单明细...");
            return ;
        }else{
            submit();
        }
        mui(this).button('loading');

        setTimeout(function () {
            mui(this).button('reset');
            //mui.back();
        }.bind(this), 4000);
    });
</script>
<script>
    function submit() {
        var entrys = [];
        $('.box').each(function () {
            var tmpString = $(this).find("button").text();
            var price = $(this).find(".price").val();
            if("请选择"==tmpString || ""==price){
                return true;//jquery each中 return false就是break；return true就是continue
            }
            var size = tmpString.split("（")[0].trim();
            var wrapper = tmpString.split("（")[1].trim().replace("）","");
            entrys.push({
                "size":size,
                "wrapper":wrapper,
                "price":price
            })
        })
        for(var i=0;i<entrys.length;i++){
            if(entrys[i].price<7.5){
                mui.toast("报价单间不得低于7.5元/千克")
                return
            }
        }
        $.ajax({
            url: "/natergy-h5/excelToImage/get",
            contentType: "application/json;charset=utf-8",
            type: "post",
            data: JSON.stringify({
                "companyName": $("#customerName").val(),
                "entryList":entrys
            }),
            dataType: "json",
            success: function (data) {
                console.log(data);
                if("ok"==data){
                    window.location.href="/natergy-h5/jsp/priceList/previewImage.jsp";
                }else{
                    mui.toast("生成失败，请稍后重试...");
                    return;
                }
            }
        });
    }
</script>
</body>
</html>
