package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.service.balance.IBalanceService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("/balance")
public class BalanceController {

    @Autowired
    private IAppUserService appUserService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createBalanceForm(){
        ModelAndView mav = new ModelAndView("/balance/create");
        mav.addObject("balance",new Balance());
        return mav;
    }

}
