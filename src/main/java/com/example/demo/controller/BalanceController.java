package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.service.balance.IBalanceService;
import com.example.demo.service.event.IEventService;
import com.example.demo.service.groupaction.IGroupAction;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController("/balance")
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private IBalanceService balanceService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IEventService eventService;
    @Autowired
    private IGroupAction groupAction;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @GetMapping()
    public ModelAndView getBalancePage() {
        return new ModelAndView("balance/balances", "balances", balanceService.findAllByUser(getCurrentUser()));
    }

    @PostMapping("/create")
    public Balance create(@RequestBody Balance balance) {
        balance.setAppUser(appUserService.getCurrentUser());
        return balanceService.add(balance);
    }

    @GetMapping("/check")
    public List<String> checkBalance() {
        List<String> messages = new ArrayList<>();
        //todo: return 1 message if have out of balance limit
        List<Balance> balances = balanceService.findAllByUser(getCurrentUser());
        for (Balance balance : balances) {
            Date start = balance.getStartDay();
            Date end = balance.getEndDay();
            Double total = 0d;
            total = eventService.getTotalByUserAndTimeRange(getCurrentUser(), start, end, groupAction.findById(2L));
            System.out.println("total = " + total);
            if (total > balance.getMoney()) {
                messages.add("Out of limit! Date: " + balance.getStartDay().toString() + "-" + balance.getEndDay().toString());
            }
        }
        return messages;
    }
}
