package cn.kindleinfo.reptile;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@SpringBootApplication(scanBasePackages = "**.**")
@MapperScan("**.dao")
public class ReptileServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReptileServerApplication.class, args);
    }
}
