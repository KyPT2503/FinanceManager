package com.example.demo.service.balance;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.service.IGeneralService;

import java.sql.Date;
import java.util.List;

public interface IBalanceService extends IGeneralService<Balance> {
    List<Balance> findAllByUser(AppUser appUser);
}
