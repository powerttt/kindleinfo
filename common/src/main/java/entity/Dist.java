package entity;

import entity.BaseDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Dist extends BaseDTO {

    @Column(length = 32)
    private String key;

    @Column(length = 500)
    private String value;

}
