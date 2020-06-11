package com.natergy.natergyh5.controller;


import com.natergy.natergyh5.entity.statisticsEntity.*;
import com.natergy.natergyh5.service.statisticsServices.OrderDetailStatisticsService;
import com.natergy.natergyh5.service.statisticsServices.SaleOrderHotMapService;
import com.natergy.natergyh5.service.statisticsServices.SaleReturnBackOrderHotMapService;
import com.natergy.natergyh5.service.statisticsServices.UserStatisticsService;
import com.natergy.natergyh5.utils.statisticsUtils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wj
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private UserStatisticsService userStatisticsService;

    @Autowired
    private OrderDetailStatisticsService orderDetailStatisticsService;

    @Autowired
    private SaleOrderHotMapService saleOrderHotMapService;

    @Autowired
    private SaleReturnBackOrderHotMapService saleReturnBackOrderHotMapService;

    /***
     * 获取订单列表
     * @param request
     * @return
     */
    @RequestMapping("/orderdetails")
    @ResponseBody
    public Map<String, Object> orderdetails(HttpServletRequest request) {

//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");

        Map<String, Object> params = initParamsFromRequest(request);
//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");


        Integer rows = 50;
        if (null != request.getParameter("rows") && !"".equals(request.getParameter("rows"))) {
            rows = Integer.valueOf(request.getParameter("rows"));
        }


        Integer page = 1;
        if (null != request.getParameter("page") && !"".equals(request.getParameter("page"))) {
            page = Integer.valueOf(request.getParameter("page"));
        }


        System.out.println(params);


        List<PageOrderDetail> records = orderDetailStatisticsService.orderDetails(params);
        Iterator<PageOrderDetail> iterator = records.iterator();

        List<PageOrderDetail> selectRecords = new ArrayList<>();

        PageOrderDetail obj = null;
        int index = 0;
        while (iterator.hasNext()) {
            obj = iterator.next();

            if (index > rows * (page - 1) && index < rows * (page - 1) + 50) {
                selectRecords.add(obj);
            }
            if (index > rows * (page - 1) + 50) {
                break;
            }
            index++;
        }

        Map<String, Object> results = new HashMap<>();
        results.put("rows", selectRecords);
        results.put("records", records.size());
        results.put("total", records.size() / rows);
        return results;
    }


    /****
     * ajax 异步获取订单统计list {{人：销售数据}}数据
     * 页面支持分页功能，分页做了内存分页；
     * 页面的分页触发条件为，滑动到屏幕底端
     * @param request
     * @return
     */
    @RequestMapping("/orderstatics")
    @ResponseBody
    public Map<String, Object> orderstatics(HttpServletRequest request) {

        Map<String, Object> params = initParamsFromRequest(request);
//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");


        Integer rows = 50;
        if (null != request.getParameter("rows") && !"".equals(request.getParameter("rows"))) {
            rows = Integer.valueOf(request.getParameter("rows"));
        }


        Integer page = 1;
        if (null != request.getParameter("page") && !"".equals(request.getParameter("page"))) {
            page = Integer.valueOf(request.getParameter("page"));
        }


        List<PageOrderDetailTj> records = saleOrderHotMapService.orderStaticsByPriceStandardsRegion(params);
        Iterator<PageOrderDetailTj> iterator = records.iterator();

        List<PageOrderDetailTj> selectRecords = new ArrayList<>();

        PageOrderDetailTj obj = null;
        int index = 0;
        while (iterator.hasNext()) {
            obj = iterator.next();

            if (index >= rows * (page - 1) && index <= rows * (page - 1) + 50) {
                selectRecords.add(obj);
            }
            if (index > rows * (page - 1) + 50) {
                break;
            }
            index++;
        }

        Map<String, Object> results = new HashMap<>();
        results.put("rows", selectRecords);
        results.put("records", records.size());
        results.put("total", records.size() / rows);
        return results;
    }


    /****
     * 订单列表页面
     * @return
     */
    @RequestMapping("/page_orderdetails")
    public String page_order_details() {
//        ModelAndView modelAndView = new ModelAndView("orderdetails");//设置对应JSP的模板文件
//        modelAndView.addObject("details", results);
        return "statistics/orderdetails";
    }


    /****
     * 订单统计页面
     * @return
     */
    @RequestMapping("/page_orderstatics_tj")
    public String page_orderdetailtotal_tj() {
//        ModelAndView modelAndView = new ModelAndView("orderdetails");//设置对应JSP的模板文件
//        modelAndView.addObject("details", results);
        return "statistics/ordertj";
    }

    /****
     * 统计详情页面
     * @param request
     * @return
     */
    @RequestMapping("/page_orderstatics_detail")
    public ModelAndView orderStaticsByPriceStandardsRegionDetail(HttpServletRequest request) {

        Map<String, Object> params = initParamsFromRequest(request);

        List<PageOrderDetailTj> records = saleOrderHotMapService.orderStaticsByPriceStandardsRegion(params);
        ModelAndView modelAndView = new ModelAndView("statistics/ordertj_detail");//设置对应JSP的模板文件
        modelAndView.addObject("tj", records.get(0));

        return modelAndView;
    }


    /****
     * 订单统计页面
     * @return
     */
    @RequestMapping("/page_orderhotmap")
    public String page_orderhotmap() {
//        ModelAndView modelAndView = new ModelAndView("orderdetails");//设置对应JSP的模板文件
//        modelAndView.addObject("details", results);
        return "statistics/order_hotmap";
    }


    @RequestMapping("/orderhotmapdata")
    @ResponseBody
    public PageOrderDetailTjChinaMap orderHotMapData(HttpServletRequest request) {

        Map<String, Object> params = initParamsFromRequest(request);

        PageOrderDetailTjChinaMap chinaMapData = saleOrderHotMapService.saleChinaMap(params);

        return chinaMapData;
    }


    /*****
     * 用来从后台请求中获取查询参数
     * @param request
     * @return
     */
    private Map<String, Object> initParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");
        if (null != request.getParameter("department") && !"".equals(request.getParameter("department"))) {
            String department = request.getParameter("department");
            params.put("department", department);
        } else {
            params.put("department", "国内销售部");
        }

        if (null != request.getParameter("jobposition") && !"".equals(request.getParameter("jobposition"))) {
            String jobposition = request.getParameter("jobposition");
            params.put("jobposition", jobposition);
        } else {
            params.put("jobposition", "国内业务经理");
        }
        params.put("orderByType", "desc");


        String salerName = "";
        if (null != request.getParameter("salerName") && !"".equals(request.getParameter("salerName"))) {
            salerName = request.getParameter("salerName");
            params.put("salerName", salerName);
        }


        String startTime = "";
        if (null != request.getParameter("start") && !"".equals(request.getParameter("start"))) {
            startTime = request.getParameter("start");
            params.put("start", TimeUtil.getDateTime(startTime));
        } else {
            params.put("start", "2019年01月01日 09时35分46秒");
        }

        String endTime = "";
        if (null != request.getParameter("end") && !"".equals(request.getParameter("end"))) {
            endTime = request.getParameter("end");
            params.put("end", TimeUtil.getDateTime(endTime));
        } else {
            params.put("end", TimeUtil.currentTime());
        }

        String filterGift = "y";
        if (null != request.getParameter("filterGift") && !"".equals(request.getParameter("filterGift"))) {
            filterGift = request.getParameter("filterGift");
            params.put("filterGift", filterGift);
        } else {
            params.put("filterGift", filterGift);
        }

        String filter_sample = "y";
        if (null != request.getParameter("filter_sample") && !"".equals(request.getParameter("filter_sample"))) {
            filter_sample = request.getParameter("filter_sample");
            params.put("filter_sample", filter_sample);
        } else {
            params.put("filter_sample", filter_sample);
        }
        return params;
    }


    @RequestMapping("/page_returnbackorderhotmap")
    public String page_returnbackhotmap() {
        return "statistics/returnbackorder_hotmap";
    }

    @RequestMapping("/returnbackhotmapdata")
    @ResponseBody
    public PageReturnOrderDetailTjChinaMap returnBackHotMapData(HttpServletRequest request) {
//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");
        Map<String, Object> params = initParamsFromRequest(request);


        List<ReturnBackOrderDetail> returnBackOrderDetails = orderDetailStatisticsService.selectReturnBackOrderDetails(params);
        PageReturnOrderDetailTjChinaMap chinaMapData = new PageReturnOrderDetailTjChinaMap();
        chinaMapData.addReturnBackOrderDetails(returnBackOrderDetails);

        chinaMapData.computeDatas();

        return chinaMapData;
    }



    @RequestMapping("/page_sumorderhotmap")
    public String page_summation_orderhotmapdata() {
        return "statistics/sumorderhotmap";
    }

    @RequestMapping("/summationorderhotmapdata")
    @ResponseBody
    public PageSummationSaleHotChinaMap summationOrderHotMapData(HttpServletRequest request) {
        Map<String, Object> params = initParamsFromRequest(request);
        List<PageOrderDetail> pageOrderDetails = orderDetailStatisticsService.orderDetails(params);
        List<ReturnBackOrderDetail> returnBackOrderDetails = orderDetailStatisticsService.selectReturnBackOrderDetails(params);

        PageSummationSaleHotChinaMap summationSaleHotChinaMap = new PageSummationSaleHotChinaMap();
        summationSaleHotChinaMap.addOrderDetails(pageOrderDetails);
        summationSaleHotChinaMap.addReturnBackOrderDetails(returnBackOrderDetails);

        summationSaleHotChinaMap.computeDatas();

        return summationSaleHotChinaMap;
    }
}
