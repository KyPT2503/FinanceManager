package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @GetMapping("event")
    public ModelAndView showListEvent(){
        ModelAndView m = new ModelAndView("event/view");
        return m;
    }
}
