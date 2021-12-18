package com.libokai.service.impl;

import com.libokai.mapper.Article2TagMapper;
import com.libokai.mapper.ArticleMapper;
import com.libokai.mapper.FieldMapper;
import com.libokai.mapper.TagMapper;
import com.libokai.pojo.Article;
import com.libokai.pojo.Article2Tag;
import com.libokai.pojo.Field;
import com.libokai.pojo.Tag;
import com.libokai.service.myService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl  implements myService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private Article2TagMapper article2TagMapper;

    @Autowired
    private FieldMapper fieldMapper;

    public Article selectByPrimaryKeyDetails(Integer id)
    {
        return articleMapper.selectByPrimaryKeyDetails(id);
    }

    public List<Article> selectAllDetails()
    {
        return articleMapper.selectAllDetails();
    }

    public void insert(Article article)
    {
        articleMapper.insert(article);
    }
    public List<Article> selectByUserId(Integer userId)
    {
        return articleMapper.selectByUserId(userId);
    }
    public List<Article> selectByFieldId(Integer Id)
    {
        return articleMapper.selectByFieldId(Id);
    }
    public void deleteByPrimaryKey(Integer id)
    {
        articleMapper.deleteByPrimaryKey(id);
    }



    public void insertSomeTags(List<Tag> tags, Article article)
    {
        List<Article2Tag> article2Tags = new ArrayList<>();
        for(Tag tag : tags)
        {
            tagMapper.insert(tag);
            System.out.println(tag.getId());
            article2Tags.add(new Article2Tag(article.getId(), tag.getId()));
        }

        for (Article2Tag article2Tag : article2Tags)
        {
            article2TagMapper.insert(article2Tag);
        }
    }

    public void deleteSomeTags(List<Tag> tags, Integer articleId)
    {
        for (Tag tag : tags)
        {
            tagMapper.deleteByPrimaryKey(tag.getId());
            article2TagMapper.deleteByPrimaryKey(articleId, tag.getId());
        }
    }

    public Field selectFieldByKeySimple(Integer id)
    {
        return fieldMapper.selectByPrimaryKeySimple(id);
    }
}
