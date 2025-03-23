package com.chilun.hotAir.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 齿轮
 * @date 2024-01-25-12:15
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    void userRegister() {
        service.userRegister("chilun2","12345678","12345678");
    }
}