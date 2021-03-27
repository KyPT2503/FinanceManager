package com.example.demo.service.mail;

import com.example.demo.model.MailAndCode;
import com.example.demo.repository.MailAndCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailAndCodeService implements IMailAndCodeService {
    @Autowired
    private MailAndCodeRepository mailAndCodeRepository;

    @Override
    public List<MailAndCode> findAll() {
        return null;
    }

    @Override
    public MailAndCode add(MailAndCode mailAndCode) {
        return mailAndCodeRepository.save(mailAndCode);
    }

    @Override
    public boolean remove(MailAndCode mailAndCode) {
        return false;
    }

    @Override
    public MailAndCode update(MailAndCode mailAndCode) {
        return null;
    }

    @Override
    public MailAndCode findById(Long id) {
        return null;
    }

    @Override
    public MailAndCode findByEmail(String email) {
        return mailAndCodeRepository.findFirstByEmail(email);
    }
}
