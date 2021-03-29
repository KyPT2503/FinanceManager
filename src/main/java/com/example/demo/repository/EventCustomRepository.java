package com.example.demo.repository;
import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;

import java.util.List;

public interface EventCustomRepository {
    List<Event> getEventByCondition(EventDTO event);
}
