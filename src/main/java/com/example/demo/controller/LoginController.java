package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/log")
public class LoginController {
    @GetMapping("")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("/log");
        return modelAndView;
    }
}
