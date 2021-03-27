package com.example.demo.repository.eventrepository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.GroupAction;
import com.example.demo.model.Wallet;

import java.util.List;

public interface EventCustomRepository {
    List<Event> getEventByCondition(EventDTO event);
    Wallet getWalletByFK(String wallet);
    AppUser getUserByFK(String appUser);
    GroupAction getActionByFK(String groupAction);
}
