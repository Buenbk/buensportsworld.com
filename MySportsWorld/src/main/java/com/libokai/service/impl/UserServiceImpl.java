package com.libokai.service.impl;

import com.libokai.mapper.EmailPasswordMapper;
import com.libokai.mapper.UserMapper;
import com.libokai.pojo.EmailPassword;
import com.libokai.pojo.User;
import com.libokai.service.myService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements myService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailPasswordMapper emailPasswordMapper;

    public User selectByPrimaryKey(Integer id)
    {
        return userMapper.selectByPrimaryKey(id);
    }
    public List<User> selectAll()
    {
        return userMapper.selectAll();
    }

    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public String selectByPrimaryKey(String email)
    {
        return emailPasswordMapper.selectByPrimaryKey(email);
    }

    public void updateByPrimaryKeySimple(User user)
    {
        userMapper.updateByPrimaryKeySimple(user);
    }
    public void insertSimple(User user)
    {
        userMapper.insertSimple(user);
    }
    public void insertEmailPassword(EmailPassword emailPassword)
    {
        emailPasswordMapper.insert(emailPassword);
    }
}
