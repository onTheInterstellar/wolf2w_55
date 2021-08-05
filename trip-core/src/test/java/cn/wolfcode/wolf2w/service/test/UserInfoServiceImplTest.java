package cn.wolfcode.wolf2w.service.test;

import cn.wolfcode.wolf2w.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserInfoServiceImpl.class)
class UserInfoServiceImplTest {


    @Test
    void login() {

        UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();
        System.out.println(userInfoService.getGateway());

    }
}