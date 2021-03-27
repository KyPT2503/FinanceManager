package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Wallet wallet;
    @ManyToOne
    private GroupAction groupAction;
    private String name;
    private double money;
    private Date date;
    private String note;

    public Event() {
    }

    public Event(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public Event(AppUser user, Wallet wallet, GroupAction groupAction, String name, double money, String note) {
        this.user = user;
        this.wallet = wallet;
        this.groupAction = groupAction;
        this.name = name;
        this.money = money;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public GroupAction getGroupAction() {
        return groupAction;
    }

    public void setGroupAction(GroupAction groupAction) {
        this.groupAction = groupAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
