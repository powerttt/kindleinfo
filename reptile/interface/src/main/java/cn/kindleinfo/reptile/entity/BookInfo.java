package cn.kindleinfo.reptile.entity;

import entity.BaseDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 书籍信息
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Data
@Entity
@Table(name = "kindle_book_info")
public class BookInfo extends BaseDTO {

    @Column(length = 30,name = "author_code")
    private String authorCode;

    @Column(length = 100,name = "book_name")
    private String bookName;

    @Column(length = 100,name = "book_code")
    private String bookCode;

    @Column(length = 30,name = "label_code")
    private String labelCode;

    @Column(columnDefinition = "double(3,1)")
    private Double score;

    @Column(columnDefinition = "double(3,1)")
    private Double star;

    @Column(length = 1024)
    private String introduction;

    @Column(length = 1024,name="downs_json")
    private String downsJson;

    @Column(length = 100,name="douban_url")
    private String doubanUrl;

    @Transient
    private String photo;



}
