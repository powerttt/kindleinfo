package entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Data
public class Photo implements Serializable {

    @Column(columnDefinition = "longtext")
    private String photo;
}
