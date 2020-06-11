$(function(){
    initList();
    mui('body').on('tap', '#mylist li', function () {
        clickLi(this)
    });
    $("#search").click(function(){
        initList();
    });
});
function clickLi(obj){
    var salerName=     $(obj).attr("salerName");
    var startTime=   $("#start").html();
    var endTime=    $("#end").html();
    var filterGift=   filter_gift;
    var filter_sample=    filter_sample;
    //localStorage.clear();
    //localStorage.setItem("value",$(obj).find("input:hidden").val());
    window.location.href="page_orderstatics_detail?salerName="+salerName+"&start="+startTime+"&end="+endTime+"&filterGift="+filterGift+"&filter_sample="+filter_sample;
}

var total=0;

function initList(){

    jQuery.post('orderstatics', {'salerName':$("#search_people").val(),'start':$("#start").html(),'end':$("#end").html(),'page':1,
        'filterGift':filter_gift,"filter_sample":filter_sample,'department':'国内销售部','jobposition':'国内业务经理'}, function (result) {

        jQuery("#mylist").html("");
        var htmls="";
        total=result.total;
        for (var i = 0; i < result.rows.length; i++) {
            htmls+="<li class='mui-table-view-cell mui-media' salerName="+result.rows[i].manager+">";
            // htmls+="			<a  class='mui-navigate-right' href=\"page_orderstatics_detail?salerName="+result.rows[i].manager+"&startTime="+$("#start").val()+"&endTime="+$("#end").val()+"&filterGift="+filter_gift+"&filter_sample="+filter_sample+"\">";//
            htmls+="				<div class='mui-media-body'>";
//			htmls+="<div style='display:none'>"+result.rows[i].id+"</div>";
            var totalprice="";
            if(result.rows[i].totalPrice==null){
                totalprice+="丢失数据";
            }else{
                totalprice=result.rows[i].totalPrice/10000;
                totalprice=totalprice.toFixed(2)+"万元";
            }
            var totalWeight="";
            if(result.rows[i].totalWeight==null){
                totalWeight+="丢失数据";
            }else{
                totalWeight=result.rows[i].totalWeight/1000;
                totalWeight=totalWeight.toFixed(2)+"吨";
            }

            htmls+="<span class='mui-icon-extra mui-icon-extra-peoples'>"+result.rows[i].manager+"&nbsp;&nbsp;"+totalWeight+"&nbsp;&nbsp;总价:"+totalprice+"</span>";


            var pricetj_html="<p class='mui-ellipsis'>价格统计:";
            for(var pricekey  in  result.rows[i].unitpriceMap)
            {

                pricetj_html+=""+pricekey+":"+result.rows[i].unitpriceMap[pricekey].totalNetWeight/1000+"吨,"+result.rows[i].unitpriceMap[pricekey].totalPrice/10000+"万元";
                pricetj_html+="&nbsp;&nbsp;";
            }
            pricetj_html+="</p>";
            htmls+=pricetj_html;


            var packtj_html="<p class='mui-ellipsis'>包装统计:";
            for(var packkey  in  result.rows[i].packMap)
            {

                packtj_html+=""+packkey+":"+result.rows[i].packMap[packkey].totalNetWeight/1000+"吨,"+result.rows[i].packMap[packkey].totalPrice/10000+"万元";
                packtj_html+="&nbsp;&nbsp;";
            }
            packtj_html+="</p>";
            htmls+=packtj_html;


            var provincetj_html="<p class='mui-ellipsis'>按省统计:";
            for(var provincekey  in  result.rows[i].provinceMap)
            {

                provincetj_html+=""+provincekey+":"+result.rows[i].provinceMap[provincekey].totalNetWeight/1000+"吨,"+result.rows[i].provinceMap[provincekey].totalPrice/10000+"万元";
                provincetj_html+="&nbsp;&nbsp;";
            }
            provincetj_html+="</p>";
            htmls+=provincetj_html;

//
//			htmls+="<p class='mui-ellipsis'>"+ result.rows[i].orderNo  +"&nbsp;&nbsp;"
//			+result.rows[i].goodsReach +"&nbsp;&nbsp;&nbsp;"+result.rows[i].productStandards+"&nbsp;&nbsp;"+result.rows[i].netWeight+"kg&nbsp;&nbsp;"+result.rows[i].price +"</p>";

            //packMap
            //provinceMap

            htmls+="</div>	</li>";
        }
        jQuery("#mylist").append(htmls);
    }, 'json');

}

function appendList(page){
    jQuery.post('http://localhost:8080/orderdetails', {'salerName':$("#search_people").val(),'start':$("#start").val(),'end':$("#end").val(),'page':page,'filterGift':filter_gift,'filter_sample':filter_sample,
        'department':'国内销售部','jobposition':'国内业务经理'}, function (result) {

        var htmls="";

        for (var i = 0; i < result.rows.length; i++) {
//          $("#mylist").append("<option value='" + result.rows[i].id + "'>" + result.rows[i].manager + "</option>");
            htmls+="<li class='mui-table-view-cell mui-media' salerName="+result.rows[i].manager+" startTime="+$("#start").val()+" endTime="+$("#end").val()+" filterGift="+filter_gift+" filter_sample="+filter_sample+">";
            //		    htmls+="			<a  class='mui-navigate-right' href=\"page_orderstatics_detail?salerName="+result.rows[i].manager+"&startTime="+$("#start").val()+"&endTime="+$("#end").val()+"&filterGift="+filter_gift+"&filter_sample="+filter_sample+"\">";//
            htmls+="				<div class='mui-media-body'>";
            htmls+="<div style='display:none'>"+result.rows[i].id+"</div>";
            var totalprice="";
            if(result.rows[i].totalPrice==null){
                totalprice+="丢失数据";
            }else{
                totalprice=result.rows[i].totalPrice/10000;
                totalprice=totalprice.toFixed(2)+"万元";
            }
            var totalWeight="";
            if(result.rows[i].totalWeight==null){
                totalWeight+="丢失数据";
            }else{
                totalWeight=result.rows[i].totalWeight/1000;
                totalWeight=totalWeight.toFixed(2)+"吨";
            }

            htmls+="<span class='mui-icon-extra mui-icon-extra-peoples'>"+result.rows[i].manager+"&nbsp;&nbsp;"+result.rows[i].orderDate.substring(0, 13)+"&nbsp;&nbsp;总价:"+totalprice+"元</span>";
            htmls+="					<p class='mui-ellipsis'>"+ result.rows[i].orderNo  +"&nbsp;&nbsp;"
                +result.rows[i].goodsReach +"&nbsp;&nbsp;"+result.rows[i].productStandards+"&nbsp;&nbsp;"+totalWeight+"&nbsp;&nbsp;"+result.rows[i].price +"</p>";
            htmls+="</div>	</li>";
        }
        jQuery("#mylist").append(htmls);
    }, 'json');

}
