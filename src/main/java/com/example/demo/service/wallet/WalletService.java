package com.example.demo.service.wallet;

import com.example.demo.model.Wallet;
import com.example.demo.repository.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletService implements IWalletService{
    @Autowired
    private IWalletRepository iWalletRepository;
    @Override
    public List<Wallet> findAll() {
        return (List<Wallet>) iWalletRepository.findAll();
    }

    @Override
    public Wallet add(Wallet wallet) {
        return iWalletRepository.save(wallet);
    }

    @Override
    public boolean remove(Wallet wallet) {
        return false;
    }

    @Override
    public Wallet update(Wallet wallet) {
        return null;
    }

    @Override
    public Wallet findById(Long id) {
        return null;
    }
}
