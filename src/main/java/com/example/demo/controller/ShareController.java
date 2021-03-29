package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Share;
import com.example.demo.service.event.IEventService;
import com.example.demo.service.share.IShareService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IShareService shareService;
    @Autowired
    private IEventService eventService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @GetMapping("/home")
    public ModelAndView showHomePage() {
        return new ModelAndView("users/home");
    }

    @GetMapping("/user-search/{email}")
    public List<AppUser> searchUserByEmail(@PathVariable("email") String email) {
        return appUserService.findByEmail(email);
    }

    @PostMapping("/{id}")
    public AppUser share(@PathVariable("id") AppUser custom) {
        AppUser owner = getCurrentUser();
        if (owner == null) {
            return null;
        }
        Share share = new Share();
        share.setOwner(owner);
        share.setCustom(custom);
        Share shared = shareService.add(share);
        if (shared == null) {
            return null;
        }
        return custom;
    }

    @GetMapping("/shared")
    public List<AppUser> getAllShared() {
        List<Share> shares = shareService.findByOwner(getCurrentUser());
        List<AppUser> customs = new ArrayList<>();
        for (Share share : shares) {
            customs.add(share.getCustom());
        }
        return customs;
    }

    @GetMapping("/got-shared")
    public List<AppUser> getAllGotShared() {
        List<Share> shares = shareService.findAllByCustom(getCurrentUser());
        List<AppUser> owners = new ArrayList<>();
        for (Share share : shares) {
            owners.add(share.getOwner());
        }
        return owners;
    }

    @DeleteMapping("/delete/{id}")
    public void removeShare(@PathVariable("id") AppUser custom) {
        List<Share> shares = shareService.findAllByOwnerAndCustom(getCurrentUser(), custom);
        for (Share share : shares) {
            shareService.remove(share);
        }
    }

    @GetMapping("/all-event/{id}")
    public ModelAndView showAllEvent(@PathVariable("id") AppUser owner) {
        List<Share> shares = shareService.findAllByOwnerAndCustom(owner, getCurrentUser());
        if (shares.size() == 0) {
            return new ModelAndView("denied-access");
        }
        ModelAndView modelAndView = new ModelAndView("share/shared");
        modelAndView.addObject("owner", owner);
        modelAndView.addObject("events", eventService.findByUser(owner));
        return modelAndView;
    }
}
