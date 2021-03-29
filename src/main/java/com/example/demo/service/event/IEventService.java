package com.example.demo.service.event;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.GroupAction;
import com.example.demo.service.IGeneralService;

import java.sql.Date;
import java.util.List;

public interface IEventService extends IGeneralService<Event> {
    List<Event> search(EventDTO event);

    Event save(Event event, String date, String action, String wallet, String money);

    void deleteById(Long id);

    Event findEventByStringId(String id, String name, String note);

    List<Event> findAllByDateBetween(Date start, Date end);

    List<Event> findAllByDateBetweenAndUser(Date start, Date end, AppUser appUser);

    List<Event> findAllByDateAndUser(Date date, AppUser appUser);

    Double getTotalByUserAndTimeRange(AppUser appUser, Date start, Date end, GroupAction groupAction);

    List<Event> findByUser(AppUser appuser);
}
