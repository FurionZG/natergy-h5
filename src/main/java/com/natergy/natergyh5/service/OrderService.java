package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.OrderMapper;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderMapper orderDao;
    @Autowired
    private CustomerMapper customerDao;
    public List<Order> queryOrders(String uname) {
        return orderDao.queryOrders(uname);
    }

    public List<String> queryCustomersByUser(String uname) {
        return customerDao.getCustomersByUser(uname);

    }

    public ResultOfSelectCustomerInfoByName getCustomerInfoByName(String customerName) {
        return customerDao.getCustomerInfoByName(customerName);
    }
}
