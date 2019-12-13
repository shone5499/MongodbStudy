package com.shone.mongodb.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * DemoClass
 *
 * @author Xiao GuoJian
 * @date 2019/12/13 下午3:28
 */
@Data
@Builder
@Document(collection = "book")
public class Book {
    @Id
    private String id;
    private String name;
    private String info;
    private Integer price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
