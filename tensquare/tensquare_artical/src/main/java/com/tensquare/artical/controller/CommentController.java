package com.tensquare.artical.controller;

import com.tensquare.artical.pojo.Comment;
import com.tensquare.artical.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"评论成功");
    }
    @RequestMapping(value = "/article/{articleid}")
    public Result findByArticleId(@PathVariable String articleid){
        List<Comment> articlecomment = commentService.findByArticleid(articleid);
        return new Result(true,StatusCode.OK,"查询成功",articlecomment);
    }
}
