package com.windsor.cyanraft.service.impl;

import com.windsor.cyanraft.dao.UserDao;
import com.windsor.cyanraft.dto.UserRegisterRequest;
import com.windsor.cyanraft.model.User;
import com.windsor.cyanraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
