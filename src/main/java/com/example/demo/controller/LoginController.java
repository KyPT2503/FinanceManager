package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class LoginController {
    @RequestMapping("/log")
    public ModelAndView getLoginPage() {
        return new ModelAndView("users/log");
    }
    @GetMapping("/register")
    public ModelAndView getRegisterPage(){
        return new ModelAndView("users/register");
    }
}
