package com.example.demo.controller;

import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;
import com.example.demo.service.event.EventService;
import com.example.demo.service.groupaction.GroupActionService;
import com.example.demo.service.wallet.WalletService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    private WalletService walletService;

    private GroupActionService groupActionService;

    @Autowired
    public EventController(WalletService walletService, GroupActionService groupActionService, EventService eventService) {
        this.walletService = walletService;
        this.groupActionService = groupActionService;
        this.eventService = eventService;
    }

    @ModelAttribute("listWallet")
    public List<Wallet> getListWallet() {
        return walletService.findAll();
    }

    @ModelAttribute("listAction")
    public List<GroupAction> getListAction() {
        return groupActionService.findAll();
    }

    @GetMapping("event")
    public ModelAndView showListEvent(){
        eventService.writeToFileExcel();
        return new ModelAndView("event/view");
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download1(HttpServletResponse response) throws IOException {
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
