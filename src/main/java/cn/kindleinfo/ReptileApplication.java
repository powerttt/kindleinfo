package cn.kindleinfo;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.kindleinfo.**")
@MapperScan("cn.kindleinfo.reptile.dao")
public class ReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReptileApplication.class, args);
    }

}
