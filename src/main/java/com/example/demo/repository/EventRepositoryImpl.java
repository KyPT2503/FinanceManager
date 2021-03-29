package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.Wallet;
import com.example.demo.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AppUserService appUserService;

    @Override
    public List<Event> getEventByCondition(EventDTO event) {
        AppUser appUser = appUserService.getCurrentUser();
        StringBuilder sql = new StringBuilder();
        sql.append("from Event where 1=1 and user_id = " + appUser.getId());
        if (event.getName() != null && !"".equals(event.getName().trim())) {
            sql.append(" and name like '%").append(event.getName()).append("%'");
        }
        if (event.getGroupAction() != null && !"".equals(event.getGroupAction().trim())) {
            sql.append(" and group_action_id = " + event.getGroupAction());
        }
        if (event.getDate() != null && !"".equals(event.getDate().trim())
                && event.getEndDate() != null && !"".equals(event.getEndDate().trim())) {
            sql.append(" and date between " + "'" + event.getDate() + "'" + " and " + "'" + event.getEndDate() + "'");
        }
        return entityManager.createQuery(sql.toString()).getResultList();
    }

    @Override
    public List<Wallet> getWalletByUser() {
        AppUser appUser = appUserService.getCurrentUser();
        StringBuilder sql = new StringBuilder();
        sql.append("from Wallet where app_user_id = " + appUser.getId());
        return entityManager.createQuery(sql.toString()).getResultList();
    }

}
