package cn.bdqn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.bdqn.mapper")
public class Test {
    public static void main(String[] args) {
        SpringApplication.run(Test.class);
    }
}
