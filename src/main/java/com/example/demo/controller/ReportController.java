package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.service.event.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ReportController {
    @Autowired
    private IEventService eventService;

    @GetMapping("/report")
    public ModelAndView getReportPage(){
        ModelAndView modelAndView = new ModelAndView("/report/reportPage5");
        YearMonth yearMonth = YearMonth.now();
        Date start = Date.valueOf(yearMonth.atDay(1));
        Date end = Date.valueOf(yearMonth.atEndOfMonth());
        Map<String, Double> thu = new HashMap<>();
        Map<String, Double> chi = new HashMap<>();

        for (Event e: eventService.findAllByDateBetween(start,end)) {
            if (e.getGroupAction().getId() == 1){
                thu.put(e.getName(), e.getMoney());
            } else if (e.getGroupAction().getId() == 2){
                chi.put(e.getName(), e.getMoney());
            }
        }
        modelAndView.addObject("thu", thu);
        modelAndView.addObject("chi", chi);
        return modelAndView;
    }

    @PostMapping("/report")
    public ModelAndView searchEvent(@RequestParam Date start, Date end){
        ModelAndView modelAndView = new ModelAndView("/report/reportPage5");
        Map<String, Double> thu = new HashMap<>();
        Map<String, Double> chi = new HashMap<>();
        ArrayList<Date> date = new ArrayList();

        for (Event e: eventService.findAllByDateBetween(start, end)) {
            if (e.getGroupAction().getId() == 1){
                thu.put(e.getName(), e.getMoney());
                date.add(e.getDate());
            } else if (e.getGroupAction().getId() == 2){
                chi.put(e.getName(), e.getMoney());
                date.add(e.getDate());
            }
        }
        modelAndView.addObject("thu", thu);
        modelAndView.addObject("chi", chi);
        modelAndView.addObject("date", date);
        return modelAndView;
    }
}
