package com.hyeok.blog.test1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("temphome");
        // 파일리턴 기본 경로 : src/main/resources/static
        // 리턴명 : /home.html
        return "/home.html";
    }
}
