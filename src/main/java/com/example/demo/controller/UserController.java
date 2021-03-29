package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.service.balance.IBalanceService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IBalanceService balanceService;

    @Autowired
    private IAppUserService appUserService;


    @GetMapping
    public String homeUser() {
        return "/users/register";
    }

    @GetMapping("/create")
    public ModelAndView showCreateUser() {
        ModelAndView mav = new ModelAndView("/users/create");
        mav.addObject("user",new AppUser());
        return mav;
    }


    @GetMapping("/balance/create")
    public ModelAndView showFormCreateBalance() {
        ModelAndView modelAndView = new ModelAndView("/balance/create");
        modelAndView.addObject("balance",new Balance());
        return modelAndView;
    }
    @PostMapping("/balance/create")
    public String created(@ModelAttribute Balance balance) {
        balance.setAppUser(appUserService.getCurrentUser());
        balanceService.add(balance);
//        ModelAndView modelAndView = new ModelAndView("/users/register");
        return "/users/register";
    }

}
