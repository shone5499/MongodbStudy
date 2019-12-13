package com.shone.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * DemoClass
 *
 * @author Xiao GuoJian
 * @date 2019/12/13 下午3:30
 */

public abstract class BaseMongoService<T> {

    @Autowired
    public MongoTemplate mongoTemplate;

    public T save(T entity){
        return mongoTemplate.save(entity);
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> findAll() {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return mongoTemplate.findAll(entityClass);
    }

    /***
     * 根据id查询
     * @param id
     * @return
     */
    public T findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return mongoTemplate.findOne(query, entityClass);
    }

    public DeleteResult delete(T entity){
       return mongoTemplate.remove(entity);
    }

    public DeleteResult deleteById(String id){
        T entity = findById(id);
        return mongoTemplate.remove(entity);
    }


}
