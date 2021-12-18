package com.libokai.controller.impl;

import com.libokai.controller.myController;
import com.libokai.pojo.News;
import com.libokai.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/news")
public class NewsControllerImpl implements myController {
    @Autowired
    private NewsServiceImpl newsService;

    @RequestMapping("/getAllNews")
    public List<News> getAllNews()
    {
        return newsService.selectAll();
    }

    @RequestMapping("/details/{id}")
    public News selectByPrimaryKey(@PathVariable("id") Integer id)
    {
        return newsService.selectByPrimaryKey(id);
    }

}
