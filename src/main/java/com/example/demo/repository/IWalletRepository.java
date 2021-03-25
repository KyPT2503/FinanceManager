package com.example.demo.repository;



import com.example.demo.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface IWalletRepository extends CrudRepository<Wallet,Long> {
}


