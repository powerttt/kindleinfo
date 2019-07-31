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
@Table(name = "kindle_book_label")
public class BookLabel extends BaseDTO {

    private String typeCode;
    private String labelName;
    private String labelCode;


}
