package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.service.event.IEventService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;


@Controller
public class ReportController {
    @Autowired
    private IEventService eventService;

    @Autowired
    private IAppUserService appUserService;

    @ModelAttribute("user")
    public AppUser getCurrentUser() {
        return appUserService.getCurrentUser();
    }

    @GetMapping("/report")
    public ModelAndView getReportPage() {
        ModelAndView modelAndView = new ModelAndView("/report/reportPage5");
        YearMonth yearMonth = YearMonth.now();
        Date start = Date.valueOf(yearMonth.atDay(1));
        Date end = Date.valueOf(yearMonth.atEndOfMonth());
        Map<String, Double> thu = new HashMap<>();
        Map<String, Double> chi = new HashMap<>();
        Map<Double, Double> thuchi = new HashMap<>();
        Map<Date, Map<Double, Double>> thuchitime = new HashMap<>();
        ArrayList<Date> date = new ArrayList();
        String time = start.toString() + " / " + end.toString();
        Double tongthu = 0.0;
        Double tongchi = 0.0;


        for (Event e : eventService.findAllByDateBetweenAndUser(start, end, getCurrentUser())) {
            if (e.getGroupAction().getId() == 1) {
                thu.put(e.getName(), e.getMoney());
                tongthu += e.getMoney();
            } else if (e.getGroupAction().getId() == 2) {
                chi.put(e.getName(), e.getMoney());
                tongchi += e.getMoney();
            }
        }
        for (Date d = start; d.before(end); d.setDate(d.getDate() + 1)) {
            Double totalThuInDay = 0.0;
            Double totalChiInDay = 0.0;
            for (Event e : eventService.findAllByDateAndUser(d, getCurrentUser())) {
                if (e.getGroupAction().getId() == 1) {
                    totalThuInDay += e.getMoney();
                } else if (e.getGroupAction().getId() == 2) {
                    totalChiInDay += e.getMoney();
                }
            }
            thuchi.put(totalThuInDay, totalChiInDay);
            thuchitime.put(d, thuchi);
        }

        modelAndView.addObject("tongthu", tongthu);
        modelAndView.addObject("tongchi", tongchi);
        modelAndView.addObject("thu", thu);
        modelAndView.addObject("chi", chi);
        modelAndView.addObject("time", time);
        modelAndView.addObject("date", date);
        modelAndView.addObject("thuchitime", thuchitime);
        modelAndView.addObject("thuchi", thuchi);
        return modelAndView;
    }

    @PostMapping("/report")
    public ModelAndView searchEvent(@RequestParam Date start, Date end) {
        start.setDate(start.getDate()-1);
        end.setDate(end.getDate()+1);
        ModelAndView modelAndView = new ModelAndView("/report/reportPage5");
        Map<String, Double> thu = new HashMap<>();
        Map<String, Double> chi = new HashMap<>();
        Map<Double, Double> thuchi = new HashMap<>();
        Map<Date, Double[]> thuchitime = new HashMap<>();
        ArrayList<Date> date = new ArrayList();
        String time = start.toString() + " / " + end.toString();
        Double tongthu = 0.0;
        Double tongchi = 0.0;


        for (Event e : eventService.findAllByDateBetweenAndUser(start, end, getCurrentUser())) {
            if (e.getGroupAction().getId() == 1) {
                thu.put(e.getName(), e.getMoney());
                tongthu += e.getMoney();
            } else if (e.getGroupAction().getId() == 2) {
                chi.put(e.getName(), e.getMoney());
                tongchi += e.getMoney();
            }
        }
        for (Date d = start; d.before(end); d.setDate(d.getDate() + 1)) {
            Double totalThuInDay = 0.0;
            Double totalChiInDay = 0.0;
            for (Event e : eventService.findAllByDateAndUser(d, getCurrentUser())) {
                if (e.getGroupAction().getId() == 1) {
                    totalThuInDay += e.getMoney();
                } else if (e.getGroupAction().getId() == 2) {
                    totalChiInDay += e.getMoney();
                }
            }
            thuchi.put(totalThuInDay, totalChiInDay);
            thuchitime.put(d, new Double[]{totalThuInDay,totalChiInDay});
        }

        modelAndView.addObject("tongthu", tongthu);
        modelAndView.addObject("tongchi", tongchi);
        modelAndView.addObject("thu", thu);
        modelAndView.addObject("chi", chi);
        modelAndView.addObject("time", time);
        modelAndView.addObject("date", date);
        modelAndView.addObject("thuchitime", thuchitime);
        modelAndView.addObject("thuchi", thuchi);
        return modelAndView;
    }
}
