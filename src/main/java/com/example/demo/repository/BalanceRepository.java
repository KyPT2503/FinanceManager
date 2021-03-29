package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findAllByAppUser(AppUser appUser);
}
