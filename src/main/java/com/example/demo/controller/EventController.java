package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.service.groupaction.GroupActionService;
import com.example.demo.service.user.AppUserService;
import com.example.demo.service.user.IAppUserService;
import com.example.demo.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EventController {
    private WalletService walletService;

    private GroupActionService groupActionService;
    @Autowired
    private IAppUserService appUserService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @Autowired
    public EventController(WalletService walletService, GroupActionService groupActionService) {
        this.walletService = walletService;
        this.groupActionService = groupActionService;
    }

    @ModelAttribute("listWallet")
    public List<Wallet> getListWallet() {
        return walletService.findAll();
    }

    @ModelAttribute("listAction")
    public List<GroupAction> getListAction() {
        return groupActionService.findAll();
    }

    @GetMapping("event")
    public ModelAndView showListEvent() {
        return new ModelAndView("event/view");
    }
}
