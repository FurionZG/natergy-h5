package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserMapper userDao;

    public Integer checkUser(User user) {
        return userDao.checkUser(user);
    }

}
