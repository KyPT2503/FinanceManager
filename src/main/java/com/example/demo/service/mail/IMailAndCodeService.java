package com.example.demo.service.mail;


import com.example.demo.model.MailAndCode;
import com.example.demo.service.IGeneralService;

public interface IMailAndCodeService extends IGeneralService<MailAndCode> {
    MailAndCode findByEmail(String email);
}
