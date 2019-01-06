package com.tensquare.artical.dao;

import com.tensquare.artical.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleid);
}
