package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.event.EventService;
import com.example.demo.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
    public ResponseEntity uploadEvent(@RequestParam String name, @RequestParam String money, @RequestParam String date, @RequestParam String wallet, @RequestParam String groupAction, @RequestParam String note) {
        eventService.save(new Event(name, note), date, groupAction, wallet, money);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("edit")
    public ResponseEntity editEvent(@RequestParam String id, @RequestParam String name, @RequestParam String money, @RequestParam String date, @RequestParam String wallet, @RequestParam String groupAction, @RequestParam String note) {
        eventService.save(eventService.findEventByStringId(id,name,note), date, groupAction, wallet, money);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity deleteEvent(@RequestParam String id) {
        eventService.deleteById(Long.parseLong(id));
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download1(HttpServletResponse response) {
        try {
            InputStream inputStream = eventService.writeToFileExcel();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=file.xlsx");
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("event/view");
    }
}
