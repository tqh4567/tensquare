package com.tensquare.spit.service;


import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private  IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }
    public Optional<Spit> findById(String id){
        return spitDao.findById(id);
    }
    public  void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setShare(0);
        spit.setComment(0);
        spit.setState("1");
        //判断是否有父节点
        if(spit.getParentid()!=null&&spit.getParentid()!=""){
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }
    public void update(Spit spit){
        spitDao.save(spit);
    }
    public void delete(String id){
        spitDao.deleteById(id);
    }
    public Page<Spit> findByParentId(String parentid, int page,int size){
        Pageable pageable=PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentid,pageable);
    }

    public void thumbup(String spitId) {
//        Spit spit=spitDao.findById(spitId).get();
//        spit.setThumbup(spit.getThumbup()==null?1:spit.getThumbup()+1);
//        spitDao.save(spit);
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
    public void visits(String spitId) {
//        Spit spit=spitDao.findById(spitId).get();
//        spit.setThumbup(spit.getThumbup()==null?1:spit.getThumbup()+1);
//        spitDao.save(spit);
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update=new Update();
        update.inc("visits",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
    public void share(String spitId) {
//        Spit spit=spitDao.findById(spitId).get();
//        spit.setThumbup(spit.getThumbup()==null?1:spit.getThumbup()+1);
//        spitDao.save(spit);
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update=new Update();
        update.inc("share",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
