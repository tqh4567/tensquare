package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;
//    @Autowired
//    private IdWorker idWorker;

    /**
     * 添加索引
     * @param article
     */
    public void add(Article article){
//        article.setId(idWorker.nextId()+"");
        articleSearchDao.save(article);
    }

    /**
     * 查询索引
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String keyword,int page,int size){
        Pageable pageable= PageRequest.of(page-1,size);
        return articleSearchDao.findByTitleOrContentLike(keyword,keyword,pageable);
    }
}
