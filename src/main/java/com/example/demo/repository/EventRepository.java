package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventCustomRepository{
    List<Event> findAllByDateBetween(Date start, Date end);
    List<Event> findAllByDateBetweenAndUser(Date start, Date end,AppUser appUser);
    List<Event> findAllByDateAndUser(Date date, AppUser appUser);
}
