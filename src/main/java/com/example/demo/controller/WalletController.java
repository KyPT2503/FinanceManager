package com.example.demo.controller;

import com.example.demo.model.Wallet;
import com.example.demo.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private IWalletService iWalletService;

    @GetMapping("")
    public ModelAndView fillAll() {
        List<Wallet> walletList = iWalletService.findAll();
        ModelAndView modelAndView = new ModelAndView("wallet/list");
        modelAndView.addObject("wallet", walletList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() {
        ModelAndView modelAndView = new ModelAndView("wallet/create");
        modelAndView.addObject("wallet", new Wallet());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Wallet wallet) {
        ModelAndView modelAndView = new ModelAndView("wallet/create");
        iWalletService.add(wallet);
        modelAndView.addObject("wallet",wallet);
        modelAndView.addObject("mess", "Thêm mới thành công ví :" + wallet.getName());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id) {
        Wallet wallet = iWalletService.findById(id);
        ModelAndView modelAndView = new ModelAndView("wallet/edit");
        modelAndView.addObject("wallet", wallet);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @ModelAttribute Wallet wallet) {
        wallet.setId(id);
        iWalletService.add(wallet);
        return new ModelAndView("redirect:/wallets");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id,@PathVariable Wallet wallet){
        ModelAndView modelAndView = new ModelAndView("redirect:/wallets");
        wallet.setId(id);
        iWalletService.remove(wallet);
        return modelAndView;
    }


    @PostMapping("/search")
    public ModelAndView searchProductByName(@RequestParam String search) {
        search = "%" + search + "%";
        List<Wallet> wallets = iWalletService.findByProductName(search);
//        if (wallets.size() == 0) return new ModelAndView("error-404");
        ModelAndView modelAndView=new ModelAndView("wallet/list");
        modelAndView.addObject("wallet",wallets);
        return modelAndView;
    }
}
