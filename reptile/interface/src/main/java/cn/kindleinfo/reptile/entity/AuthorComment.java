package cn.kindleinfo.reptile.entity;

import entity.Comment;
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
@Table(name = "kindle_comment_author")
public class AuthorComment extends Comment {


    @Column(length = 100,name = "author_code")
    private String authorCode;

}
