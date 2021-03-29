package com.example.demo.repository;

import com.example.demo.model.GroupAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupActionRepository extends JpaRepository<GroupAction, Long> {
}
