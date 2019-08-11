package cn.kindleinfo.api.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据库键值对
 *
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Data
@Entity
@Table(name = "kindle_dist")
public class Dist   implements Serializable {

    @Column(length = 32)
    private String key;

    @Column(length = 500)
    private String value;
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
