package com.natergy.natergyh5.service;

import com.natergy.natergyh5.annotations.NatergyColumnName;
import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/19 17:29
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerDao ;

    public Customer getCustomerAllInfo(String customerName) {
        return customerDao.getCustomerAllInfo(customerName);
    }

    public List<String> getCustomerNameList(String uname){
        return customerDao.getCustomersByUser(uname);
    }


    @Transactional
    public Integer updateCustomerInfo(Customer newCustomer,String user) throws IllegalAccessException, ClassNotFoundException {
        String id = newCustomer.getId();
        Customer oldCustomer = customerDao.getCustomerInfoById(id);
        Class customerClz = Class.forName("com.natergy.natergyh5.entity.Customer");
        List<Field> customerField = Arrays.asList(customerClz.getDeclaredFields());
        for(int i =0;i<customerField.size();i++){
            customerField.get(i).setAccessible(true);
            String newfieldValue= (String)customerField.get(i).get(newCustomer);
            String oldfieldValue= (String)customerField.get(i).get(oldCustomer);
            if(null==newfieldValue || null==oldfieldValue){
                continue;
            }
            if(!oldfieldValue.equals(newfieldValue)) {
                String columnName = customerField.get(i).getAnnotation(NatergyColumnName.class).value();
                String content = columnName  + "-由-" + customerField.get(i).get(oldCustomer) + "-改为-" + customerField.get(i).get(newCustomer)+"时间"+new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date());
                customerDao.saveChangePort(user, newCustomer.getId(), content);
            }
        }

        return customerDao.updateCustomer(newCustomer);

    }

    public Set<String> queryCustomerName(String uname) {
        List<String> resultSet = this.customerDao.queryCustomerName(uname);
        return new HashSet<>(resultSet);
    }
}
