package cn.kindleinfo.reptile.entity;

import entity.BaseDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */

@Data
@Entity
@Table(name = "kindle_author_info")
public class AuthorInfo extends BaseDTO {

    @Column(length = 100,name = "author_name")
    private String authorName;

    @Column(length = 100,name = "author_code")
    private String authorCode;



}
