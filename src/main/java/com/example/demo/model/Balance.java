package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDay;
    private Date endDay;
    private double money;
    @ManyToOne
    private AppUser appUser;

    public Balance() {
    }

    public Balance(Date startDay, Date endDay, double money,AppUser appUser) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.money = money;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
