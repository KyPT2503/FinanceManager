package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByOwner(AppUser owner);

    List<Share> findAllByCustom(AppUser custom);

    List<Share> findAllByOwnerAndCustom(AppUser owner, AppUser custom);
}
