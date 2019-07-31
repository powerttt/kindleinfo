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
public class Comment extends BaseDTO {

    @Column(length = 1024)
    private String comment;

    @Column(length = 50, name = "user_code")
    private String userCode;
}
