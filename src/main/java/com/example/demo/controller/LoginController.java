package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private IAppUserService appUserService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @RequestMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("users/log");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("users/register", "user", new AppUser());
    }

    @PostMapping("/register")
    public AppUser register(@ModelAttribute("user") AppUser appUser) {
        return appUserService.add(appUser);
    }

    @GetMapping("/current-user")
    public AppUser appUser() {
        return appUserService.getCurrentUser();
    }
}
