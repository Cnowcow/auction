package com.stu.auction.api.test.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test1(){
        return "테스트 완료";
    }



    //테스트 해보는 것
    @GetMapping("/test")
    public ModelAndView test(HttpSession session) {

        System.out.println("session = " + session);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/test"); // "templates/views/test.html" 파일을 뷰로 사용

        return modelAndView;
    }


}
