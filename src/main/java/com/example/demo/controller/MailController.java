package com.example.demo.controller;

import com.example.demo.model.MailAndCode;
import com.example.demo.service.mail.IMailAndCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private IMailAndCodeService mailAndCodeService;

    @GetMapping("/validate-email/{email}")
    public void addMailAndCode(@PathVariable("email") String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Validation email");
        int code = new Random().nextInt(8999) + 1000;
        message.setText(String.valueOf("Your validation code: " + code));
        MailAndCode mailAndCode = new MailAndCode();
        mailAndCode.setEmail(email);
        mailAndCode.setCode(String.valueOf(code));
        javaMailSender.send(message);
        mailAndCodeService.add(mailAndCode);
    }
}
