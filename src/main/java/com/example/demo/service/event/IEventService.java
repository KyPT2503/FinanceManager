package com.example.demo.service.event;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.IGeneralService;

import java.sql.Date;
import java.util.List;

public interface IEventService extends IGeneralService<Event> {
    List<Event> search(EventDTO event);
    List<Event> findAllByDateBetween(Date start, Date end);

}
