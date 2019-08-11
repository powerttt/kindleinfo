package cn.kindleinfo.api.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Data
@Entity
@Table(name = "kindle_book_type")
public class BookType   implements Serializable {

    private String typeCode;
    private String typeName;
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
