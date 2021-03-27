package com.example.demo.controller;

import com.example.demo.model.AppRole;
import com.example.demo.model.AppUser;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    Environment environment;
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
        AppUser appUser = new AppUser();
        return new ModelAndView("users/register", "newUser", appUser);
    }

    @PostMapping("/register")
    public AppUser register(@ModelAttribute("newUser") AppUser appUser) {
        MultipartFile image = appUser.getAvatarFile();
        String path = image.getOriginalFilename();
        appUser.setAvatar(path);
        try {
            FileCopyUtils.copy(image.getBytes(), new File(environment.getProperty("IMAGE_SOURCE") + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appUserService.add(appUser);
    }

    @GetMapping("/current-user")
    public AppUser appUser() {
        return appUserService.getCurrentUser();
    }
}
