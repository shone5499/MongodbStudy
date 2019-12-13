package com.shone.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.shone.mongodb.domain.Book;
import com.shone.mongodb.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {

    @Autowired
    private BookService bookService;

    /**
     * 创建记录
     */
    @Test
    public void createBook() {
        for (int i = 1; i < 3; i++) {
            Book book = Book.builder()
                    .name("中国近现代史" + i)
                    .price(100 + i)
                    .info("这是一个备注")
                    .createTime(LocalDateTime.now())
                    .build();
            bookService.createBook(book);
        }
    }

    /**
     * 修改记录
     */
    @Test
    public void updateBook() {
        Book book = Book.builder()
                .id("1")
                .name("中国近现代史")
                .price(100)
                .info("这是一个备注ha")
                .build();
        UpdateResult result = bookService.updateBook(book);
        System.out.println("Update Count = " + result.getModifiedCount());
    }

    /**
     * 获取所有记录
     */
    @Test
    public void getAllBook(){
        bookService.getAllBook().forEach(System.out::println);
    }

    /**
     * 根据查询条件获取所有记录
     */
    @Test
    public void getAllBookByCriteria(){
        Book book = Book.builder()
//                .id("1")
                .name("中国近现代史")
//                .price(100)
//                .info("这是一个备注ha")
                .createTime(LocalDateTime.parse("2019-12-13 17:25:01",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        bookService.getAllBook(book).forEach(System.out::println);
    }

    /**
     * 根据ID获取记录
     */
    @Test
    public void getBookById(){
        Book book = bookService.getBookById("2");
        System.out.println("Book = " + book);
    }

    /**
     * 删除记录
     */
    @Test
    public void deleteBook(){
        Book book = bookService.getBookById("2");
        DeleteResult result = bookService.deleteBook(book);
        System.out.println("Delete Count = " + result.getDeletedCount());
    }

    /**
     * 根据ID删除记录,其实内部的实现也是通过ID查询出来Entity,然后删除
     */
    @Test
    public void deleteBookById(){
        DeleteResult result = bookService.deleteBookById("5df3584f17c66c1eda110910");
        System.out.println("Delete Count = " + result.getDeletedCount());
    }

}
