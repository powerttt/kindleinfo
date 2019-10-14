package cn.kindleinfo.api.entity;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 书籍信息
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Data
@Table(name = "kindle_book_info")
public class BookInfo implements Serializable {

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

    @Column(name="photo_url")
    private String photoUrl;

    @Id
    @GeneratedValue(generator = "JDBC",strategy = GenerationType.IDENTITY)
    @KeySql(useGeneratedKeys = true)
    protected Long id;
    protected Integer state;
    protected String creator;
    @Column(name = "create_date",length = 19)
    protected String createDate;
    protected String updator;

    @Column(name = "update_date",length = 19)
    protected String updateDate;
    protected Boolean deleted = false;

}



