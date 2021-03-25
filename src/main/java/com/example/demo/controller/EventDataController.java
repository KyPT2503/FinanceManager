package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EventDataController {
    @Autowired
    private EventService eventService;

    @PostMapping("show")
    public List<Event> getEventList(@RequestBody EventDTO event){
        return eventService.search(event);
    }
}
