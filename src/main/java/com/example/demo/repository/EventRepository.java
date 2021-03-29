package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.model.GroupAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventCustomRepository {
    List<Event> findAllByDateBetween(Date start, Date end);

    List<Event> findAllByDateBetweenAndUser(Date start, Date end, AppUser appUser);

    List<Event> findAllByDateAndUser(Date date, AppUser appUser);

    @Query(value = "select sum(e.money) from Event e where e.user =:appUser and e.date between :start and :end and e.groupAction=:groupAction")
    Double getTotal(AppUser appUser, Date start, Date end, GroupAction groupAction);
}
