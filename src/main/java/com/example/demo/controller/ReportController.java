package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {
    @GetMapping("/report")
    public ModelAndView getReportPage(){
        return new ModelAndView("/report/reportPage5");
    }
}
