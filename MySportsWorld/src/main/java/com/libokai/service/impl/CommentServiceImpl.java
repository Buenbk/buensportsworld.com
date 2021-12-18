package com.libokai.service.impl;

import com.libokai.mapper.CommentMapper;
import com.libokai.pojo.Comment;
import com.libokai.service.myService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl  implements myService {

    @Autowired
    private CommentMapper commentMapper;

    public void insert(Comment comment)
    {
        commentMapper.insert(comment);
    }

    public void deleteByPrimaryKey(Integer id)
    {
        commentMapper.deleteByPrimaryKey(id);
    }
}
