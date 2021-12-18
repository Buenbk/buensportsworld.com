package com.libokai.controller.impl;

import com.libokai.controller.myController;
import com.libokai.pojo.Article;
import com.libokai.pojo.Field;
import com.libokai.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleControllerImpl implements myController {
    @Autowired
    private ArticleServiceImpl articleService;


    @RequestMapping("/details/{id}")
    public Article selectByPrimaryKeyDetails(@PathVariable("id") Integer id)
    {
        return articleService.selectByPrimaryKeyDetails(id);
    }


    @RequestMapping("/details")
    public List<Article> selectAllDetails()
    {
        return articleService.selectAllDetails();
    }


    @PostMapping("/details")
    public void insertWithTags(@RequestBody Article article)
    {
        articleService.insert(article);
        articleService.insertSomeTags(article.getTags(), article);
    }

    @RequestMapping("/details/userid/{userid}")
    public List<Article> selectByUserId(@PathVariable("userid") Integer userId)
    {
        return articleService.selectByUserId(userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByPrimaryKeyWithTags(@PathVariable("id") Integer id)
    {
        Article article = articleService.selectByPrimaryKeyDetails(id);
        articleService.deleteByPrimaryKey(id);
        articleService.deleteSomeTags(article.getTags(), id);
    }

    @RequestMapping("/details/fieldid/{id}")
    public List<Article> selectByFieldId(@PathVariable("id") Integer Id)
    {
        return articleService.selectByFieldId(Id);
    }

    @RequestMapping("/field/{id}")
    public Field selectFieldByKeySimple(@PathVariable("id") Integer id)
    {
        return articleService.selectFieldByKeySimple(id);
    }
}
