package com.libokai.controller.impl;

import com.libokai.controller.myController;
import com.libokai.mapper.EmailPasswordMapper;
import com.libokai.pojo.Article;
import com.libokai.pojo.EmailPassword;
import com.libokai.pojo.User;
import com.libokai.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserControllerImpl implements myController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/details/{id}")
    public User getUser(@PathVariable("id") Integer id)
    {
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping("/details")
    public List<User> getUserAll()
    {
        return userService.selectAll();
    }

    @PostMapping("/validate")
    public String validate(@RequestBody EmailPassword emailPassword)
    {
        System.out.println(emailPassword);
        String result = userService.selectByPrimaryKey(emailPassword.getEmail());
        if(result==null)
        {
            return "不存在此账号";
        }

        return Boolean.toString(result.equals(emailPassword.getPassword()));
    }

    @RequestMapping("/details/email/{email}")
    public User getUser(@PathVariable("email") String email)
    {
        return userService.selectByEmail(email);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user)
    {
        userService.updateByPrimaryKeySimple(user);
    }

    @PostMapping("/details")
    public void insertSimple(@RequestBody User user)
    {
        userService.insertSimple(user);
        userService.insertEmailPassword(user.getEmailPassword());
    }
}
