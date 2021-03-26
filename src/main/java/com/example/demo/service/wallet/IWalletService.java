package com.example.demo.service.wallet;

import com.example.demo.model.Wallet;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IWalletService extends IGeneralService<Wallet> {
    List<Wallet> findByWalletName(String name);

}
