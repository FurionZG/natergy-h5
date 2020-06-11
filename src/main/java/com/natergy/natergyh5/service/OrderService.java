package com.natergy.natergyh5.service;

import com.natergy.natergyh5.entity.ResultOfAttention;
import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.OrderMapper;
import com.natergy.natergyh5.dao.ProductMapper;
import com.natergy.natergyh5.entity.Customer;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 订单模块业务层
 * @author 杨枕戈
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderMapper orderDao;
    @Autowired
    private CustomerMapper customerDao;
    @Autowired
    private ProductMapper productMapper;
    @Value("${SalesExecutive}")
    String salesExecutive;
    /**
     * 查询登录用户的最后10条订单记录
     * @param uname 登录用户的用户名
     * @return 返回登录用户最后10条订单记录
     */
    public List<Order> queryOrders(String uname) {
        return orderDao.queryOrders(uname);
    }

    /**
     * 查询业务经理为登录用户用户名的客户名称
     * @param uname 登录用户的用户名
     * @return 返回业务经理为登录用户用户名的客户名列表
     */
    public List<String> queryCustomersByUser(String uname) {
        return customerDao.getCustomersByUser(uname);
    }

    /**
     * 查询某个客户的客户信息
     * @param customerName 客户名
     * @param uname 登录用户的用户名
     * @return 返回该客户的客户信息
     */
    public ResultOfSelectCustomerInfoByName getCustomerInfoByName(String customerName, String uname) {
        return customerDao.getCustomerInfoByName(customerName, uname);
    }


    /**
     * 获取产品的规格、外包装、产品包装信息(生成级联)
     * 这段代码可维护性极差，但基于目前数据库结构不得不写出这样的代码
     * 总的来说，就是将数据库中的数据处理成级联菜单需要的数据格式
     * 使用MUI框架的级联菜单要求规定格式的数据，具体格式在MUI框架示例中的city.data-3.js中可以查看
     * 由于需要级联，所以查询分三步进行
     * @return 三级级联要求的数据格式的产品信息
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
     * @param order 订单对象
     * @param uname 登录用户的用户名
     * @return 返回是否保存成功
     */
    @Transactional
    public Integer saveOrderTranscation(Order order, String uname) {
        //查询省，市，客户编码，合同方式，合同编号
        Map<String, String> customerInfo = orderDao.getCustomerInfo(order.getCustomerName(), uname);
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
     * 查询更多的订单信息
     * @param uname 登录用户的用户名
     * @param limit 页面显示的订单记录的数量
     * @return 返回从limit开始之后5条订单记录
     */
    public List<Order> reloadOrdersLimit(String uname, Integer limit) {
        return orderDao.queryOrdersLimit(uname, limit);
    }

    /**
     * 保存新客户
     * @param customer 客户对象
     * @param uname 登录用户的用户名（作为业务经理字段）
     * @return 返回是否保存成功
     */
    public Integer saveCustomer(Customer customer, String uname) {
        Integer selectCustomer = customerDao.selectCustomerCount(customer.getCustomerName());
        if(0==selectCustomer){
            return customerDao.saveCustomer(customer, uname);
        }else{
            return -1;
        }

    }

    /**
     * 修改订单状态为已到货
     * @param orderNumber 订单号
     * @return 返回是否修改成功
     */
    public Integer updateArrived(String orderNumber) {
        return orderDao.updateArrived(orderNumber);
    }

    /**
     * 查询登录用户所有订单的客户名称
     * @param uname 登录用户的用户名
     * @return 返回登录用户所有订单的客户名称Set
     */
    public Set<String> queryCustomerName(String uname) {
        List<String> resultSet = orderDao.queryCustomerName(uname);
        return new HashSet(resultSet);
    }

    /**
     * 查询客户的下单信息
     * @param customerName 客户名
     * @param uname 登录用户的用户名
     * @return 返回该客户的下单信息
     */
    public List<Order> getOrdersInfoByAjax(String customerName, String uname) {
        return orderDao.getOrdersInfoByAjax(customerName, uname);
    }

    /**
     * 更新订单，由于需要与NI-PC端共通，所以这里直接根据id删除订单和订单明细，再重新添加订单记录和订单明细记录，保持订单号不变。
     * @param order 订单对象
     * @param uname 登录用户的用户名
     * @return 是否更新订单
     */
    @Transactional
    public Integer updateOrder(Order order, String uname) {
        Order oldOrder = orderDao.queryOldOrderById(order.getId());
        order.setOrderTime(oldOrder.getOrderTime());
        order.setOrderNumber(oldOrder.getOrderNumber());
        for (OrderDetail od : order.getOrderDetails()) {
            od.setTotalWeight(String.valueOf(od.getOutwrapper().contains("桶") ? 160 * Integer.parseInt(od.getCount()) : 25 * Integer.parseInt(od.getCount())));
            od.setTotalPrice(String.valueOf(Double.parseDouble(od.getTotalWeight()) * Double.parseDouble(od.getPrice())));
            od.setOrderNumber(order.getOrderNumber());
        }
        Integer deleteOrderDetails = orderDao.deleteOrderDetails(order.getId());
        Integer deleteOrders = orderDao.deleteOrders(order.getId());
        Integer insertNewOrder = saveOrderTranscation(order, uname);
        return deleteOrderDetails * deleteOrders * insertNewOrder;
    }

    public List<String> getCustomerList(String uname) {
        List<Customer> customerList = orderDao.getCustomerList(uname);
        List<String> resultList = new ArrayList<>();
        for(Customer customer :customerList){
            String s = customer.getCustomerName()+"\t"+customer.getCustomerNo();
            resultList.add(s);
        }
        return resultList;
    }

    public List<Order> getOrderInfoBySalesman(String salesmanName) {
        return orderDao.getOrderInfoBySalesman(salesmanName);
    }

    public ResultOfAttention getAttention(String customerName, String uname) {
        return customerDao.getAttention(customerName, uname);
    }
}
