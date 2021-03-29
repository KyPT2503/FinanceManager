package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Wallet;
import com.example.demo.service.user.IAppUserService;
import com.example.demo.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletAjaxController {
    @Autowired
    private IWalletService walletService;
    @Autowired
    private IAppUserService appUserService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @GetMapping("")
    public ModelAndView showIndex() {
        return new ModelAndView("wallet/index", "wallet", walletService.findAll());
    }

    @PostMapping("/create")
    public Wallet createWallet(@RequestBody Wallet wallet) {
        wallet.setAppUser(appUserService.getCurrentUser());
       Wallet a = walletService.add(wallet);
        return a;
    }

    @DeleteMapping("/remove/{id}")
    public String removeWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return "Removed wallet, id:" + id;
    }

    @GetMapping("/{id}")
    public Wallet getSingleWallet(@PathVariable("id") Wallet wallet) {
        return wallet;
    }
    @GetMapping("/all-wallet")
    public List<Wallet> getAllWallet() {
        return walletService.findAll();
    }

    @PutMapping("/update")
    public Wallet updateWallet(@RequestBody Wallet wallet) {
        walletService.update(wallet);
        return wallet;
    }
}