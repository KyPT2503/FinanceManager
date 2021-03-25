package com.example.demo.formater;

import com.example.demo.model.AppUser;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class AppUserFormater implements Formatter<AppUser> {

    private IAppUserService appUserService;

    @Autowired
    public AppUserFormater(IAppUserService appUserService1) {
        this.appUserService = appUserService1;
    }

    @Override
    public AppUser parse(String text, Locale locale) throws ParseException {
       AppUser appUser = new AppUser();
       try {
           appUser = appUserService.findById(Long.parseLong(text));
       }catch (NumberFormatException e) {
           e.printStackTrace();
       }

        return appUser;
    }

    @Override
    public String print(AppUser object, Locale locale) {
        return null;
    }
}
