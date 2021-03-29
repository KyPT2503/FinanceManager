package com.example.demo.repository;
import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.Wallet;

import java.util.List;

public interface EventCustomRepository {
    List<Event> getEventByCondition(EventDTO event);
    List<Wallet> getWalletByUser();
    List<Event> getListByUser();
    List<Event> getEventByWallet(String id);
}
