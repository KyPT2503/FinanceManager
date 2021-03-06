package com.example.demo.service.balance;

import com.example.demo.model.AppUser;
import com.example.demo.model.Balance;
import com.example.demo.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BalanceServiceImpl implements IBalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public Balance add(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public boolean remove(Balance balance) {
        balanceRepository.delete(balance);
        return true;
    }

    @Override
    public Balance update(Balance balance) {
        return null;
    }

    @Override
    public Balance findById(Long id) {
        return null;
    }

    @Override
    public List<Balance> findAllByUser(AppUser appUser) {
        return balanceRepository.findAllByAppUser(appUser);
    }
}
