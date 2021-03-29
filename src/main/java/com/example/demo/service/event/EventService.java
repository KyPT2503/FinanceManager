package com.example.demo.service.event;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.GroupActionRepository;
import com.example.demo.repository.IWalletRepository;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private IWalletRepository walletRepository;

    @PersistenceContext
    private EntityManager entityManager;


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
        eventRepository.delete(event);
        return true;
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public List<Event> search(EventDTO event) {
        return eventRepository.getEventByCondition(event);
    }

    @Override
    public Event save(Event event, String date, String action, String wallet, String money) {
        try {
            event.setWallet(entityManager.find(Wallet.class, Long.parseLong(wallet)));
            event.setMoney(Double.parseDouble(money));
            event.setGroupAction(entityManager.find(GroupAction.class, Long.parseLong(action)));
            event.setUser(event.getWallet().getAppUser());
            event.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event findEventByStringId(String id,String name,String note) {
        Event event = findById(Long.parseLong(id));
        event.setName(name);
        event.setNote(note);
        return event;
    }
}
