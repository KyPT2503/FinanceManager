package com.example.demo.controller;

import com.example.demo.model.Wallet;
import com.example.demo.service.user.IAppUserService;
import com.example.demo.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private IWalletService walletService;
    @Autowired
    private IAppUserService appUserService;

    @GetMapping("")
    public ModelAndView showIndex() {
        return new ModelAndView("wallet/index", "wallet", walletService.findByUser(appUserService.getCurrentUser()));
    }

    @PostMapping("/create")
    public Wallet createWallet(@RequestBody Wallet wallet) {
        wallet.setAppUser(appUserService.getCurrentUser());
        return walletService.add(wallet);

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

    @PutMapping("/update")
    public Wallet updateWallet(@RequestBody Wallet wallet) {
        wallet.setAppUser(appUserService.getCurrentUser());
        walletService.update(wallet);
        return wallet;
    }
}