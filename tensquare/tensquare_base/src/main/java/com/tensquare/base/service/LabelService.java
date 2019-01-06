package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 查询全部标签
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据id查询相应的标签
     * @param id
     * @return
     */
    public Label findById(String id){
        Label label = labelDao.findById(id).get();

        return label;
    }

    /**
     * 添加Label
     * @param label
     */
    public void  save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改Label
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除Lable
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);

    }

    /**
     * 根据条件进行查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
       return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 跟对象
             * @param criteriaQuery  查询的关键字
             * @param criteriaBuilder  用来封装对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class),"%" + label.getState() + "%");
                    list.add(predicate);
                }
                Predicate[] pre=new Predicate[list.size()];
                pre=list.toArray(pre);
                return criteriaBuilder.and(pre);

            }
        });

    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 跟对象
             * @param criteriaQuery  查询的关键字
             * @param criteriaBuilder  用来封装对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class),"%" + label.getState() + "%");
                    list.add(predicate);
                }
                Predicate[] pre=new Predicate[list.size()];
                pre=list.toArray(pre);
                return criteriaBuilder.and(pre);

            }
        },pageable);
    }
}
