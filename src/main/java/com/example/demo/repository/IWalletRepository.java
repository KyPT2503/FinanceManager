package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWalletRepository extends CrudRepository<Wallet,Long> {
    //Tìm kiếm ví theo tên
    @Query(value = "select  * from wallet where wallet.name like ?", nativeQuery = true)
    List<Wallet> findProductName(String name);

    @Query(value = "select  * from wallet where wallet.app_user_id = ?", nativeQuery = true)
    List<Wallet> findWalletByUser(Long appUserId);
}


