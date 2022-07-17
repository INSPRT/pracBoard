package com.pracboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

    @GetMapping("/home")
    public String home(HttpServletRequest request) {

        return "index";
    }


    
}