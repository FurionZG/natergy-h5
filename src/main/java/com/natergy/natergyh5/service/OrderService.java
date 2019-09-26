package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.OrderMapper;
import com.natergy.natergyh5.dao.ProductMapper;
import com.natergy.natergyh5.entity.Customer;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderMapper orderDao;
    @Autowired
    private CustomerMapper customerDao;
    @Autowired
    private ProductMapper productMapper;

    public List<Order> queryOrders(String uname) {
        return orderDao.queryOrders(uname);
    }

    public List<String> queryCustomersByUser(String uname) {
        return customerDao.getCustomersByUser(uname);

    }

    public ResultOfSelectCustomerInfoByName getCustomerInfoByName(String customerName) {
        return customerDao.getCustomerInfoByName(customerName);
    }

    /**
     * 获取产品的规格、外包装、产品包装信息(生成级联)
     * 这段代码可维护性极差，总的来说，就是将数据库中的数据处理成级联菜单需要的数据格式
     * 使用MUI框架的级联菜单要求规定格式的数据，具体格式在MUI框架示例中的city.data-3.js中可以查看
     * 由于需要级联，所以查询分三步进行
     */
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

    /**
     * 保存订单的业务方法
     * @param order
     * @param uname
     * @return
     */
    @Transactional
    public Integer saveOrderTranscation(Order order, String uname) {
        //查询省，市，客户编码，合同方式，合同编号
        Map<String, String> customerInfo = orderDao.getCustomerInfo(order.getCustomerName());
        //保存主表
        Integer reten = orderDao.saveOrder(order, uname, customerInfo);
        //分别保存订单明细
        for (OrderDetail od : order.getOrderDetails()) {
            //查询产品规格编号
            String productNo = productMapper.getProductNo(od.getSize(), od.getInnerWrapper(), od.getOutwrapper());
            String customerNo = customerInfo.get("客户编码");
            reten *= orderDao.saveOrderDetail(od, productNo, customerNo, order.getId());
        }
        return reten;
    }

    /**
     * 下拉多加载五条的查询方法
     * @param uname
     * @param limit
     * @return
     */
    public List<Order> reloadOrdersLimit(String uname, Integer limit) {
        return orderDao.queryOrdersLimit(uname,limit);
    }

    public Integer saveCustomer(Customer customer, String uname) {
        return customerDao.saveCustomer(customer,uname);
    }
}
