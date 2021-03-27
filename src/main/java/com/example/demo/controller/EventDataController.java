package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EventDataController {
    @Autowired
    private EventService eventService;

    @Autowired
    private WalletService walletService;

    @PostMapping("show")
    public List<Event> getEventList(@RequestBody EventDTO event) {
        return eventService.search(event);
    }

    @PostMapping("upload")
    public ResponseEntity uploadEvent(@RequestParam String name, @RequestParam String money, @RequestParam String date, @RequestParam String wallet, @RequestParam String groupAction, @RequestParam String user, @RequestParam String note) {
        eventService.save(new Event(name, note), date, groupAction, wallet, user, money);
        return new ResponseEntity(HttpStatus.OK);
    }
}
