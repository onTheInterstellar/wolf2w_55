package cn.wolfcode.wolf2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class MgrsiteApp {

    public static void main(String[] args) {
        SpringApplication.run(MgrsiteApp.class, args);
        System.out.println("启动成功");
    }

}
