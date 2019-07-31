package cn.kindleinfo.reptile.entity;

import entity.BaseDTO;
import lombok.Data;

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
@Table(name = "kindle_book_type")
public class BookType extends BaseDTO {

    private String typeCode;
    private String typeName;

}
