package com.example.demo.controller;

import com.example.demo.model.Wallet;
import com.example.demo.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EventController {
    @Autowired
    private WalletService walletService;

    @ModelAttribute("listWallet")
    public List<Wallet> categories() {
        List<Wallet> listWallet = walletService.findAll();
        return listWallet;
    }

    @GetMapping("event")
    public ModelAndView showListEvent(){
        ModelAndView m = new ModelAndView("event/view");
        return m;
    }
}
