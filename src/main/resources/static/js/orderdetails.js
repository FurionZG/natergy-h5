$(function(){
	initList();
	$("#search").click(function(){ 
	        initList();
	 });
});

var total=0;

function initList(){
	 jQuery.post('orderdetails', {'salerName':$("#search_people").val(),'start':$("#start").val(),'end':$("#end").val(),'page':1,'filterGift':filter_gift,"filter_sample":filter_sample,'department':'国内销售部','jobposition':'国内业务经理'}, function (result) {

        jQuery("#mylist").html("");
        var htmls="";
        total=result.total;
        for (var i = 0; i < result.rows.length; i++) {
            htmls+="<li class='mui-table-view-cell mui-media'>";
			htmls+="			<a >";//class='mui-navigate-right'
			htmls+="				<div class='mui-media-body'>";
			htmls+="<div style='display:none'>"+result.rows[i].id+"</div>";
			var totalprice="";
			if(result.rows[i].totalPrice==null){
				totalprice+="丢失数据";
			}else{
				totalprice=result.rows[i].totalPrice/10000;
					totalprice=totalprice.toFixed(2)+"万元";
			}

	        var totalNetWeight="";
			if(result.rows[i].netWeight==null){
				totalNetWeight+="丢失数据";
			}else{
				totalNetWeight=result.rows[i].netWeight/1000;
				totalNetWeight=totalNetWeight.toFixed(2)+"吨";
			}


			htmls+="<span class='mui-icon-extra mui-icon-extra-peoples'>"+result.rows[i].manager+"&nbsp;&nbsp;"+result.rows[i].orderDate.substring(0, 13)+"&nbsp;&nbsp;总价:"+totalprice+"元</span>";
			htmls+="					<p class='mui-ellipsis'>"+ result.rows[i].orderNo  +"&nbsp;&nbsp;"
			+result.rows[i].goodsReach +"&nbsp;&nbsp;&nbsp;"+result.rows[i].productStandards+"&nbsp;&nbsp;"+totalNetWeight+"kg&nbsp;&nbsp;"+result.rows[i].price +"</p>";
			htmls+="</div></a>	</li>";
        }
        jQuery("#mylist").append(htmls);
    }, 'json');
	
}

function appendList(page){
	 jQuery.post('orderdetails', {'salerName':$("#search_people").val(),'start':$("#start").val(),'end':$("#end").val(),'page':page,'filterGift':filter_gift,'filter_sample':filter_sample,'department':'国内销售部','jobposition':'国内业务经理'}, function (result) {

        var htmls="";
        
        for (var i = 0; i < result.rows.length; i++) {
//          $("#mylist").append("<option value='" + result.rows[i].id + "'>" + result.rows[i].manager + "</option>");
             htmls+="<li class='mui-table-view-cell mui-media'>";
			htmls+="			<a >";//class='mui-navigate-right'
			htmls+="				<div class='mui-media-body'>";
			htmls+="<div style='display:none'>"+result.rows[i].id+"</div>";
			var totalprice="";
			if(result.rows[i].totalPrice==null){
				totalprice+="丢失数据";
			}else{
				totalprice=result.rows[i].totalPrice;
			}
			  var totalNetWeight="";
            			if(result.rows[i].netWeight==null){
            				totalNetWeight+="丢失数据";
            			}else{
            				totalNetWeight=result.rows[i].netWeight/1000;
            					totalNetWeight=totalNetWeight.toFixed(2)+"吨";
            			}
			
				htmls+="<span class='mui-icon-extra mui-icon-extra-peoples'>"+result.rows[i].manager+"&nbsp;&nbsp;"+result.rows[i].orderDate.substring(0, 13)+"&nbsp;&nbsp;总价:"+totalprice+"元</span>";
			htmls+="					<p class='mui-ellipsis'>"+ result.rows[i].orderNo  +"&nbsp;&nbsp;"
			+result.rows[i].goodsReach +"&nbsp;&nbsp;"+result.rows[i].productStandards+"&nbsp;&nbsp;"+totalNetWeight+"&nbsp;&nbsp;"+result.rows[i].price +"</p>";
			htmls+="</div></a>	</li>";
        }
        jQuery("#mylist").append(htmls);
    }, 'json');
	
}
