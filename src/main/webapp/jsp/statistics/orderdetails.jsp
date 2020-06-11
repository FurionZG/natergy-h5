<%@ page language="java" pageEncoding="utf-8"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>销售订单明细</title>
		<%
        	pageContext.setAttribute("APP_PATH",request.getContextPath());
        %>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/jqgrid/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jqgrid/js/jquery.jqGrid.src.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jqgrid/js/i18n/grid.locale-cn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/orderdetails.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mui.picker.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app.css"/>

	</head>
	<body>
			<header class="mui-bar mui-bar-nav">
        			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
        			<h1 class="mui-title">销售订单明细表</h1>
        		</header>


        		<div class="mui-content">

        			<div class="mui-card">
        				<ul class="mui-table-view">
        					<li class="mui-table-view-cell mui-collapse">

        						<a>	<img class="mui-media-object mui-pull-left" src="<%=request.getContextPath()%>/imgs/select.png"><span class="mui-navigate-right" style="float:left;margin-top: 13px;">筛选项</span></a>

        						<div class="mui-collapse-content">


        							<div class="mui-input-group">

        								<div class="mui-input-row">
        									<!--<label>姓名</label>-->
        									<input type="text" id="search_people" class="mui-input-clear" placeholder="请输入姓名">
        								</div>
        								<div class="mui-input-row">
        									<!--<label>时间段</label>-->

        									<div>
        										<a><span class="mui-icon-extra mui-icon-extra-calendar" style="float:left;"></span></a>
        										<button id='starttime'  data-options='{}'  style="margin:3px;width:110px;height:33px;float:left" class="btn mui-btn mui-btn-block">开始时间</button>
        							 			<div style="height:40px;width:80px;float:left"></div>
        							 			<div id="start" class="mui-input-clear"  style="height:40;float:left;margin-top: 10px;"></div>

        							 	   </div>
        								</div>

        								<div class="mui-input-row">
        									<!--<label>时间段</label>-->
        									<div>
        										<a><span class="mui-icon-extra mui-icon-extra-calendar" style="float:left;"></span></a>
        							    		<button id='endtime'  data-options='{}'  style="margin:3px;width:110px;height:33px;float:left" class="btn mui-btn mui-btn-block">结束时间</button>
        							    		<div style="height:40px;width:80px;float:left"></div>
        							    		<div id="end" class="mui-input-clear"  style="height:40;float:left;margin-top: 10px;"></div>
        							 	   </div>
        								</div>

        								<div class="mui-input-row">
                                        	<span id="filter_gift">过滤赠品:</span>
                                        	<div class="mui-switch mui-active">
                                        		<div class="mui-switch-handle"></div>
                                        	</div>
                                        </div>


        								<div class="mui-input-row">
                                        	<span id="filter_sample">过滤样品:</span>
                                        	<div class="mui-switch mui-active">
                                        		<div class="mui-switch-handle"></div>
                                        	</div>
                                        </div>


        								<div class="mui-button-row">
        									<button class="mui-btn mui-btn-primary mui-icon mui-icon-search" type="button" id="search"  style="float:left" >搜索</button>&nbsp;&nbsp;
        								</div>
        							</div>
        						</div>
        					</li>
        				</ul>

        	       </div>
        		</div>

        		<div class="scrow_ul_container" style="position:relative;margin: 10px;">
        			<div class="title">
        					订单明细表
        			</div>
        			<div id="refreshContainer" class="mui-content mui-scroll-wrapper" style="position: relative;height:70%">
        				        <div class="mui-scroll">
        				            <!--数据列表-->
        				            <ul id="mylist" class="mui-table-view mui-table-view-chevron">

        				            </ul>
        				        </div>
            		</div>
        		</div>
        		<br>


        	<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>

        	<script src="<%=request.getContextPath()%>/js/mui.picker.min.js"></script>
        	<script>
        			(function($) {
        				$.init();
        				var start = $('#start')[0];
        				var end = $('#end')[0];
        				var btns = $('.btn');
        				btns.each(function(i, btn) {
        					btn.addEventListener('tap', function() {
        						var _self = this;
        						if(_self.picker) {
        							_self.picker.show(function (rs) {
        								if(i==0){
        									$('#start').val(rs.text);
        								}else{
        									$('#end').val(rs.text);
        								}

        								_self.picker.dispose();
        								_self.picker = null;
        							});
        						} else {
        							var optionsJson = this.getAttribute('data-options') || '{}';
        							var options = JSON.parse(optionsJson);
        							var id = this.getAttribute('id');
        							/*
        							 * 首次显示时实例化组件
        							 * 示例为了简洁，将 options 放在了按钮的 dom 上
        							 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
        							 */
        							_self.picker = new $.DtPicker(options);
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

        							   if(i==0){
        									start.innerText =  rs.text;
        								}else{
        									end.innerText=rs.text;
        								}

        								/*
        								 * 返回 false 可以阻止选择框的关闭
        								 * return false;
        								 */
        								/*
        								 * 释放组件资源，释放后将将不能再操作组件
        								 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
        								 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
        								 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
        								 */
        								_self.picker.dispose();
        								_self.picker = null;
        							});
        						}

        					}, false);
        				});
        			})(mui);
        		</script>
        		<script>
        			mui.init({
        				swipeBack:false //启用右滑关闭功能
        			});
        		</script>

        		<script type="text/javascript">
        		    var countDown = 0;//下拉次数
        		    var countUp = 0;//上拉次数
        		    mui.init({
        		        pullRefresh: {
        		            container: "#refreshContainer",//待刷新区域标识，querySelector能定位的css选择器均可，比如：id、.class等
        		            down: {//下拉刷新
        		                height: 100,//可选,默认50.触发下拉刷新拖动距离,
        		                auto: false,//可选,默认false.首次加载自动下拉刷新一次
        		                contentdown: "下拉可以刷新",//可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
        		                contentover: "释放立即刷新",//可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
        		                contentrefresh: "正在刷新...",//可选，正在刷新状态时，下拉刷新控件上显示的标题内容
        		                callback: async function () {//必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
        		                    if (countDown >= total) {
        		                        mui.toast("没有更多数据了");
        		                        //没有数据
        		                        mui('#refreshContainer').pullRefresh().endPulldownToRefresh(true);
        		                        return;
        		                    }
        		                    //模拟向服务器获取数据的等待时间
        		                    await sleep(1000);
        		                    ///*每次加载动态的添加一个li*/
        							countDown++;
        							appendList( countDown);
        		                    mui.toast("更新了一条数据");
        		                    ////没有更多内容了，endPulldown 传入true， 不再执行下拉刷新
        		                    mui('#refreshContainer').pullRefresh().endPulldownToRefresh();

        		                }
        		            },
        		            up: {//上拉加载
        		                height: 100,//可选.默认50.触发上拉加载拖动距离
        		                auto: false,//可选,默认false.自动上拉加载一次
        		                contentrefresh: "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
        		                contentnomore: '没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
        		                callback: async function () {//必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
        		                    if (countUp >= total) {
        		                        this.endPullupToRefresh(true);//没有数据
        		                        return;
        		                    }
        		                    await sleep(1000);
        		                    /*每次加载动态的添加一个li*/

         							 countUp++;
        							appendList( countUp);
        		                    this.endPullupToRefresh(false)

        		                }
        		            }
        		        }
        		    });

        		    //模拟线程等待,ms:单位毫秒
        		    function sleep(ms) {
        		        return new Promise(resolve => setTimeout(resolve, ms));
        		    }
        	</script>

            <script>

                var filter_gift='y';

                var filter_sample='y';

                mui('.mui-content .mui-switch').each(function(i) { //循环所有toggle


                    if(this.classList.contains('mui-active'))
                    {
                         if(i==0){
                          $("#filter_gift").html('过滤赠品：' + "是");
                          filter_gift='y';
                         }else{

                          $("#filter_sample").html('过滤样品：' + "是");
                          filter_sample='y';

                         }

                    }else{


                               if(i==0){
                                    $("#filter_gift").html('过滤赠品：' + "否");
                                    filter_gift='n';
                               }else{

                                   $("#filter_sample").html('过滤样品：' + "否");
                                    filter_sample='n';
                               }

                    }



                    this.addEventListener('toggle', function(event) {
                         if(event.detail.isActive)
                         {
                              if(i==0){
                                 $("#filter_gift").html('过滤赠品：' + "是");
                                 filter_gift='y';
                               }else{

                                 $("#filter_sample").html('过滤样品：' + "是");
                                 filter_sample='y';
                                }

                         }else{
                               if(i==0){
                                  $("#filter_gift").html('过滤赠品：' + "否");
                                  filter_gift='n';
                                  }else{

                                  $("#filter_sample").html('过滤样品：' + "否");
                                  filter_sample='n';
                                  }
                              }
                    });
                });
		 </script>


	</body>
</html>
