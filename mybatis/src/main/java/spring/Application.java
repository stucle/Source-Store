package spring;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("spring")
public class Application
{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
