package com.tensquare.artical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.artical.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    @Modifying
    @Query(value = "update tb_article SET state=1 where id=?",nativeQuery = true)
	public void examine(String id);

    @Modifying
    @Query(value = "update tb_article SET thumbup=thumbup+1 where id=?",nativeQuery = true)
    public void updateThumbup(String id);
}
