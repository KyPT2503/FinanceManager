package com.example.demo.repository;

import com.example.demo.model.MailAndCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAndCodeRepository extends JpaRepository<MailAndCode, Long> {
    MailAndCode findFirstByEmail(String email);
}
