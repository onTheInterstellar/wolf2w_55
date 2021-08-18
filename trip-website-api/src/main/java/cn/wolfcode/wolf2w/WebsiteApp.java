package cn.wolfcode.wolf2w;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class WebsiteApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(WebsiteApp.class, args);
        System.out.println("启动成功, 时间:"+ (System.currentTimeMillis() - start) + "毫秒");
    }


}
