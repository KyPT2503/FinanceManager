package com.example.demo.service.event;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.repository.eventrepository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return null;
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
        return eventRepository.save(event);
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
    public Event save(Event event,String date,String action, String wallet, String user, String money) {
        try {
            event.setWallet(eventRepository.getWalletByFK(wallet));
            event.setMoney(Double.parseDouble(money));
            event.setGroupAction(eventRepository.getActionByFK(action));
            event.setUser(eventRepository.getUserByFK(user));
            event.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            System.out.println(event.getDate());
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventRepository.save(event);
    }
}
