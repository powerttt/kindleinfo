package cn.kindleinfo.reptile.entity;

import entity.Photo;
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
@Table(name = "kindle_photo_book")
public class BookPhoto extends Photo {



}
