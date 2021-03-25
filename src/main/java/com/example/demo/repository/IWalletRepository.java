package com.example.demo.repository;



import com.example.demo.model.Wallet;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWalletRepository extends CrudRepository<Wallet,Long> {
    //Tìm kiếm sản phẩm theo tên
    @Query(value = "select  * from product where product.name like ?", nativeQuery = true)
    List<Wallet> findProductName(String name);
}


