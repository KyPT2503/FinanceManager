package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.service.balance.IBalanceService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IBalanceService balanceService;

    @Autowired
    private IAppUserService appUserService;

//    @ModelAttribute("user")
//    public AppUser appUsers() {
//        return appUserService.getCurrentUser();
//    }


    @GetMapping("/create")
    public ModelAndView showCreateUser() {
        ModelAndView mav = new ModelAndView("/users/create");
        mav.addObject("user",new AppUser());
        return mav;
    }


}
