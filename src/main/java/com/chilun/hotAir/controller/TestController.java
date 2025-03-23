package com.chilun.hotAir.controller;

import com.chilun.hotAir.model.Masked.UserMasked;
import com.chilun.hotAir.model.entity.User;
import com.chilun.hotAir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 齿轮
 * @date 2023-12-21-0:01
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test1")
    public String test1() {
        return "hello API_OPEN_SPACE";
    }
}
