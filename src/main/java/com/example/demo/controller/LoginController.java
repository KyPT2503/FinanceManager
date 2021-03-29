package com.example.demo.controller;

import com.example.demo.model.AppRole;
import com.example.demo.model.AppUser;
import com.example.demo.model.MailAndCode;
import com.example.demo.repository.MailAndCodeRepository;
import com.example.demo.service.mail.IMailAndCodeService;
import com.example.demo.service.mail.MailAndCodeService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    private IMailAndCodeService mailAndCodeService;

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
    public ModelAndView register(@Validated @ModelAttribute("newUser") AppUser appUser, BindingResult bindingResult, @RequestParam("code") String code) {
        System.out.println(code);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("users/register", "newUser", appUser);
        }
        MailAndCode mailAndCode = mailAndCodeService.findByEmail(appUser.getEmail());
        if (mailAndCode == null || !mailAndCode.getCode().equals(code)) {
            ModelAndView modelAndView = new ModelAndView("users/register");
            modelAndView.addObject("newUser", appUser);
            modelAndView.addObject("message", "Wrong code. Please ensure you type the right code we sent you!");
            return modelAndView;
        }
        MultipartFile image = appUser.getAvatarFile();
        String path = image.getOriginalFilename();
        appUser.setAvatar(path);
        try {
            FileCopyUtils.copy(image.getBytes(), new File(environment.getProperty("IMAGE_SOURCE") + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        appUserService.add(appUser);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/current-user")
    public AppUser appUser() {
        return appUserService.getCurrentUser();
    }
}
