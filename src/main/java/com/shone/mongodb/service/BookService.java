package com.shone.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.shone.mongodb.domain.Book;

import java.util.List;

/**
 * DemoClass
 *
 * @author Xiao GuoJian
 * @date 2019/12/13 下午3:54
 */

public interface BookService {

    Book createBook(Book book);

    UpdateResult updateBook(Book book);

    DeleteResult deleteBook(Book book);

    DeleteResult deleteBookById(String id);

    List<Book> getAllBook();

    List<Book> getAllBook(Book book);

    Book getBookById(String id);
}
