package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.FollowUpMapper;
import com.natergy.natergyh5.entity.FollowUp;
import com.natergy.natergyh5.entity.ResultOfAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUpService {

    @Autowired
    private FollowUpMapper followUpDao;
    @Autowired
    private CustomerMapper customerDao;


    public List<FollowUp> getFollowUpByUser(String user) {
        return  followUpDao.getFollowUpByUser(user);
    }

    public List<String> getAllCompanys(String uname) {
        return customerDao.getCustomersByUser(uname);
    }

    public ResultOfAddress getAddressInfo(String companyName, String uname) {
        ResultOfAddress result=customerDao.getAddress(companyName,uname);
        result.setCompanyName(companyName);
        return result;
    }

    public Integer saveFollowUp(FollowUp followUp) {
        return followUpDao.saveFollowUp(followUp);
    }

    public List<FollowUp> refreshFollowUp(String uname) {
        return  followUpDao.getFollowUpByUser(uname);
    }

    public Integer updateFollowUp(FollowUp followUp, String uname) {
        return followUpDao.updateFollowUp(followUp,uname);
    }
}
