package com.stu.auction.api.test.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {


    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

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

        logger.info("reservation test");
        return modelAndView;
    }


}
