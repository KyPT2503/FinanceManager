package com.example.demo.controller;

import com.example.demo.model.Balance;
import com.example.demo.service.balance.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/balance")
public class BalanceController {

    @Autowired
    private IBalanceService balanceService;


    @GetMapping("/create")
    public ModelAndView showFormCreateBalance() {
        ModelAndView modelAndView = new ModelAndView("/balance/balance");
        modelAndView.addObject("balance",new Balance());
        return modelAndView;
    }
    @PostMapping("/create")
    public String created(@ModelAttribute Balance balance) {
        balanceService.add(balance);
        return "redirect:/balance";
    }
}
