package com.example.demo.service.wallet;

import com.example.demo.model.Wallet;
import com.example.demo.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWalletService extends IGeneralService<Wallet> {
    List<Wallet> findByProductName(String name);
}
