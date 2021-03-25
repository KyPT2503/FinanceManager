package com.example.demo.repository.eventrepository;

import com.example.demo.model.Event;
import com.example.demo.dto.EventDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> getEventByCondition(EventDTO event) {
        StringBuilder sql = new StringBuilder();
        sql.append("from Event where 1=1");
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
        System.out.println(" and date between " + event.getDate() + "and" + event.getEndDate());
        return entityManager.createQuery(sql.toString()).getResultList();
    }
}
