package com.example.demo.service.event;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.repository.eventrepository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event add(Event event) {
        return null;
    }

    @Override
    public boolean remove(Event event) {
        return false;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> search(EventDTO event) {
        return eventRepository.getEventByCondition(event);
    }

    @Override
    public List<Event> findAllByDateBetween(Date start, Date end) {
        return eventRepository.findAllByDateBetween(start, end);
    }
}
