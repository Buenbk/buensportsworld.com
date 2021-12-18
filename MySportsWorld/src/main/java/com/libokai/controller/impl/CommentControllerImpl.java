package com.libokai.controller.impl;

import com.libokai.controller.myController;
import com.libokai.pojo.Comment;
import com.libokai.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentControllerImpl implements myController {
    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/details")
    public void insert(@RequestBody Comment comment)
    {
        commentService.insert(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByPrimaryKey(@PathVariable("id")Integer id)
    {
        commentService.deleteByPrimaryKey(id);
    }
}
