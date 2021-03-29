package com.example.demo.repository.eventrepository;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, EventCustomRepository{
    List<Event> findAllByDateBetween(Date start, Date end);
}
