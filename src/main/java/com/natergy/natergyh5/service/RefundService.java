package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.OrderMapper;
import com.natergy.natergyh5.dao.ProductMapper;
import com.natergy.natergyh5.dao.RefundMapper;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.Refund;
import com.natergy.natergyh5.entity.RefundDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 退款业务层
 * @author 杨枕戈
 */
@Service
public class RefundService {

    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;


    public List<Refund> queryRefunds(String uname) {
        return refundMapper.queryRefunds(uname);
    }

    public Set<String> queryCustomerName(String uname) {
        return refundMapper.queryCustomerName(uname);
    }

    public List<Refund> getOrdersInfoByAjax(String customerName, String uname) {
        return refundMapper.getOrdersInfoByAjax(customerName, uname);
    }

    public List<Refund> reloadRefundsLimit(String uname, Integer limit) {
        return refundMapper.queryOrdersLimit(uname, limit);

    }

    public List<Refund> getRefundInfoBySalesman(String salesmanName) {
        return refundMapper.getRefundInfoBySalesman(salesmanName);
    }

    public List<Map<String, Object>> getAllProductInfo() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> sizeList = new ArrayList<String>();
        List<String> outwrapperList = new ArrayList<String>();
        List<String> innerwrapperList = new ArrayList<String>();
        sizeList = productMapper.getSize();
        for (String s : sizeList) {
            List<Map<String, Object>> resultOutwrapperList = new ArrayList<>();
            Map<String, Object> sizeMap = new HashMap<String, Object>();
            sizeMap.put("text", s);
            outwrapperList = productMapper.getOutWrapper(s);
            for (String o : outwrapperList) {
                List<Map<String, String>> resultInnerwarpperList = new ArrayList();
                Map<String, Object> outMap = new HashMap<String, Object>();
                outMap.put("text", o);
                innerwrapperList = productMapper.getInnerWrapper(s, o);
                for (String i : innerwrapperList) {
                    Map<String, String> inMap = new HashMap<String, String>();
                    inMap.put("text", i);
                    resultInnerwarpperList.add(inMap);
                }
                outMap.put("children", resultInnerwarpperList);
                resultOutwrapperList.add(outMap);
            }
            sizeMap.put("children", resultOutwrapperList);
            result.add(sizeMap);
        }
        return result;
    }

    @Transactional
    public Integer save(Refund refund, String uname) {
        String systemRefundNo = ("KTC" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).substring(0, 15);
        Map<String, String> customerInfo = orderMapper.getCustomerInfo(refund.getCustomerName(), uname);
        refund.setSystemRefundNo(systemRefundNo);
        refund.setRefundTime(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        refund.setStatus("草稿");
        refund.setUser(uname);
        refund.setCustomerNo(customerInfo.get("客户编码"));
        Integer reten = refundMapper.saveRefund(refund);
        for(RefundDetail refundDetail :refund.getRefundDetails()){
            refundDetail.setSystemRefundNo(systemRefundNo);
            refundDetail.setStatus("正常");
            String productNo = productMapper.getProductNo(refundDetail.getSize(), refundDetail.getInnerWrapper(), refundDetail.getOutWrapper());
            refundDetail.setProductNo(productNo);
            refundDetail.setTotalPrice(String.valueOf(Double.parseDouble(refundDetail.getPrice())*Double.parseDouble(refundDetail.getTotalWeight())));
            reten*=refundMapper.saveRefundDetail(refundDetail);
        }
        return reten;
    }
}
