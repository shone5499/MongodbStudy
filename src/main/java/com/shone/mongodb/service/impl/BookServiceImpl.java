package com.shone.mongodb.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.shone.mongodb.domain.Book;
import com.shone.mongodb.service.BaseMongoService;
import com.shone.mongodb.service.BookService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

/**
 * DemoClass
 *
 * @author Xiao GuoJian
 * @date 2019/12/13 下午3:57
 */
@Service
public class BookServiceImpl extends BaseMongoService<Book> implements BookService {
    @Override
    public Book createBook(Book book) {
        return this.save(book);
    }

    @Override
    public UpdateResult updateBook(Book book) {
        //根据ID 查询数据
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        //编辑要更新的数据
        Update update = new Update().set("updateTime", LocalDateTime.now());
        if(!StringUtils.isEmpty(book.getName())){
            update.set("name",book.getName());
        }
        if(!StringUtils.isEmpty(book.getInfo())){
            update.set("info",book.getInfo());
        }
        if(null != book.getPrice()){
            update.set("price",book.getPrice());
        }

        //更新数据
        return mongoTemplate.updateFirst(query,update,Book.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Book.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Book.class);

    }

    @Override
    public DeleteResult deleteBook(Book book) {
        return this.delete(book);
    }

    @Override
    public DeleteResult deleteBookById(String id) {
        return this.deleteById(id);
    }

    @Override
    public List<Book> getAllBook() {
        return mongoTemplate.findAll(Book.class);
    }

    @Override
    public List<Book> getAllBook(Book book) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(book.getId())){
            criteria.and("_id").is(book.getId());
        }
        if(!StringUtils.isEmpty(book.getName())){
            //模糊查询name
            Pattern pattern = Pattern.compile("^.*" + book.getName() + ".*$" , Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        if(null != book.getPrice()){
            criteria.and("price").is(book.getPrice());
        }
        if(null != book.getCreateTime()){
            //createTime 大于某个时间
            criteria.and("createTime").gt(book.getCreateTime());
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Book.class);
    }

    @Override
    public Book getBookById(String id) {
        return this.findById(id);
    }
}
